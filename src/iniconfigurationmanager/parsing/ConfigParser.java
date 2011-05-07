
package iniconfigurationmanager.parsing;

import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.ConfigurationSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.options.StringOptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionSchema;
import iniconfigurationmanager.schema.NullSectionData;
import iniconfigurationmanager.schema.NullSectionSchema;
import iniconfigurationmanager.utils.InvalidOperationException;
import iniconfigurationmanager.utils.StringUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigParser {

    private ConfigurationSchema schema;

    private ConfigurationData configuration;

    private SectionSchema currentSectionSchema;

    private SectionData currentSectionData;
    
    private StringBuilder currentComment;
    

    public ConfigParser( ConfigurationSchema schema, ConfigurationData configuration ) {
        this.schema = schema;
        this.configuration = configuration;
        this.configuration.setSchema( schema );
        this.currentSectionData = NullSectionData.getInstance();
        this.currentSectionSchema = new NullSectionSchema();
        this.currentComment = new StringBuilder();
    }

    
    public ConfigurationData parse(List<ConfigLine> lines)
            throws ConfigParserException {
        for (ConfigLine line : lines) {
            if (line.isComment()) {
                parseComment(line);
            } else if (line.isSectionHeader()) {
                parseSectionHeader(line);
            } else if (line.isOptionDefinition()) {
                parseOptionDefinition(line);
            } else {
                if (!line.isEmpty()) {
                    throw new ConfigParserException(
                            ConfigParserError.UNEXPECTED_LINE, line.getText() );
                }
            }
        }

        addMissingOptionsWithDefaultValue();

        return configuration;
    }


    private void parseComment( ConfigLine line ) {
        currentComment.append( line.getText().trim().substring( 1 ) );
        currentComment.append( Format.NEWLINE );
    }

    
    private void parseSectionHeader( ConfigLine line ) 
            throws ConfigParserException {
        String name = getSectionName( line );

        if( schema.hasSection( name )) {
            currentSectionSchema = schema.getSection( name );
        } else {
            currentSectionSchema = new NullSectionSchema();
        }

        currentSectionData = new SectionData();
        currentSectionData.setComment( getCommentForCurrentSection(),
                getComment() );

        configuration.addSection( name, currentSectionData );
    }

    
    private String getSectionName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        String name = text.substring(
            text.indexOf( Format.SECTION_DEFINITION_START ) + 1,
            text.indexOf( Format.SECTION_DEFINITION_END )
        );

        if( ! isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_SECTION_NAME, name);
        }

        return name;
    }
    

    private void parseOptionDefinition( ConfigLine line )
            throws ConfigParserException {
        try {
            String name = getOptionName( line );
            List< Object > values = getOptionValues( line );

            OptionData option = getOptionData( name );
            option.setValues( values );
            option.setComment( getCommentForOption( name ), getComment() );

            currentSectionData.addOption( name, option );
        } catch( InvalidOperationException ex ) {
            throw new ConfigParserException( ex.getMessage() );
        } catch( ClassCastException ex ) {
            throw new ConfigParserException(
                    ConfigParserError.TYPE_PARSING_EXCEPTION, line.getText() );
        }
    }

    
    private String getOptionName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( Format.EQUALS_SIGN );
        String name = StringUtils.trim(
                text.substring(0, equalsSignPosition ) );

        if( ! isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_OPTION_NAME, name);
        }
        
        return name;
    }


    private boolean isValidName( String name ) {
        Matcher m = Format.VALID_NAME_PATTERN.matcher( name );

        return m.matches();
    }

    
    private List< Object > getOptionValues( ConfigLine line ) {
        String[] values = splitValues( getOptionValuesDefinition( line ) );

        List< Object > valuesList = new LinkedList< Object >();
        for( String value : values ) {
            if( isLinkDefinition( value ) ) {
                valuesList.add( new ValueLink( value, configuration ) );
            } else {
                valuesList.add( new RawValue( value ) );
            }
        }

        return valuesList;
    }
    

    private String getOptionValuesDefinition( ConfigLine line ) {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( Format.EQUALS_SIGN );
        
        String rawValues = StringUtils.trimInlineComments(
                    text.substring(equalsSignPosition + 1) );

        return StringUtils.trim( rawValues );
    }


    private OptionData getOptionData( String name ) {
        if( currentSectionSchema.hasOption( name ) ) {
            return currentSectionSchema.getOption( name ).getOptionData();
        } else {
            return new StringOptionData();
        }
    }

    
    private String[] splitValues( String values ) {
        return getDelimiterPattern( values ).split( values );
    }

    
    private Pattern getDelimiterPattern( String values ) {
        if( isDelimitedByComma( values ) ) {
            return Format.COMMA_DELIMITER_PATTERN;
        } else if ( isDelimitedByColon( values ) ) {
            return Format.COLON_DELIMITER_PATTERN;
        } else {
            return Format.NO_DELIMITER_PATTERN;
        }
    }


    private String getComment() {
        String comment = currentComment.toString();
        currentComment = new StringBuilder();

        return comment;
    }


    private String getCommentForOption( String name ) {
        if( currentSectionSchema.hasOption( name ) ) {
            return currentSectionSchema.getOption( name ).getComment();
        } else {
            return "";
        }
    }


    private String getCommentForCurrentSection() {
        return currentSectionSchema.getComment();
    }


    private boolean isLinkDefinition( String value ) {
        return value.startsWith( Format.LINK_DEFINITION_START );
    }
    

    private boolean isDelimitedByComma( String values ) {
        Pattern pattern = Format.COMMA_DELIMITER_PATTERN;

        return findOccurence( pattern, values );
    }


    private boolean isDelimitedByColon( String values ) {
        Pattern pattern = Format.COLON_DELIMITER_PATTERN;

        return findOccurence( pattern, values );
    }

    
    private boolean findOccurence( Pattern pattern, String text ) {
        Matcher m = pattern.matcher( text );
        
        return m.find();
    }

    
    private void addMissingOptionsWithDefaultValue() {
        for( SectionSchema sectionSchema : configuration.getSchema() ) {
            SectionData sectionData = configuration.getSection(
                    sectionSchema.getName() );

            for( OptionSchema optionSchema : sectionSchema ) {
                if( 
                    optionSchema.hasDefaultValue() &&
                    ! sectionData.hasOption( optionSchema.getName() )
                ) {
                    OptionData option = optionSchema.getOptionData();
                    option.setValues( optionSchema.getDefaultValues() );
                    option.setComment( optionSchema.getComment(), "");

                    sectionData.addOption( optionSchema.getName(), option );
                }
            }
        }
    }
    
}
