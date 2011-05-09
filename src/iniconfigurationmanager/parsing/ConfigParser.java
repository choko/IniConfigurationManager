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
 * ConfigParser class parses the configuration input and sets value of the
 * configuration data.
 */
public class ConfigParser {

    private ConfigurationSchema schema;

    private ConfigurationData configuration;

    private SectionSchema currentSectionSchema;

    private SectionData currentSectionData;

    private StringBuilder currentComment;


    public ConfigParser( ConfigurationSchema schema,
            ConfigurationData configuration ) {
        this.schema = schema;
        this.configuration = configuration;
        this.configuration.setSchema( schema );
        this.currentSectionData = NullSectionData.getInstance();
        this.currentSectionSchema = new NullSectionSchema();
        this.currentComment = new StringBuilder();
    }


    /**
     * Parses given preprocessed configuration input into configuration data.
     *
     * @param lines
     * @return ConfigurationData configuration data
     * @throws ConfigParserException
     */
    public ConfigurationData parse( List<ConfigLine> lines )
            throws ConfigParserException {
        for ( ConfigLine line : lines ) {
            if ( line.isComment() ) {
                parseComment( line );
            } else if ( line.isSectionHeader() ) {
                parseSectionHeader( line );
            } else if ( line.isOptionDefinition() ) {
                parseOptionDefinition( line );
            } else {
                if ( !line.isEmpty() ) {
                    throw new ConfigParserException(
                            ConfigParserError.UNEXPECTED_LINE, line.getText() );
                }
            }
        }

        addMissingOptionsWithDefaultValue();

        return configuration;
    }


    /**
     * Removes ; symbol and adds comment to the current comment buffer.
     *
     * @param ConfigLine line
     */
    private void parseComment( ConfigLine line ) {
        currentComment.append( line.getText().trim().substring( 1 ) );
        currentComment.append( Format.NEWLINE );
    }


    /**
     * Retrieves a name of the section and adds this section to the
     * configuration data. Also checks if the appropriate schema for this
     * sections exists.
     *
     * @param ConfigLine line
     * @throws ConfigParserException
     */
    private void parseSectionHeader( ConfigLine line )
            throws ConfigParserException {
        String name = getSectionName( line );

        if ( schema.hasSection( name ) ) {
            currentSectionSchema = schema.getSection( name );
        } else {
            currentSectionSchema = new NullSectionSchema();
        }

        currentSectionData = new SectionData();
        currentSectionData.setComment( getCommentForCurrentSection(),
                getComment() );

        configuration.addSection( name, currentSectionData );
    }


    /**
     * Retrieves a section name from the line
     *
     * @param ConfigLine line
     * @return String section name
     * @throws ConfigParserException whether the section name is not valid
     */
    private String getSectionName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        String name = text.substring(
                text.indexOf( Format.SECTION_DEFINITION_START ) + 1,
                text.indexOf( Format.SECTION_DEFINITION_END ) );

        if ( !isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_SECTION_NAME, name );
        }

        return name;
    }


    /**
     * Retrieves an option name and option values. If appropriate option schema
     * exists, values are parsed with this schema.
     * Otherwise the StringOptionData class is used.
     *
     * @param ConfigLine line
     * @throws ConfigParserException
     */
    private void parseOptionDefinition( ConfigLine line )
            throws ConfigParserException {
        try {
            String name = getOptionName( line );
            List<Object> values = getOptionValues( line );

            OptionData option = getOptionData( name );
            option.setValues( values );
            option.setComment( getCommentForOption( name ), getComment() );

            currentSectionData.addOption( name, option );
        } catch ( InvalidOperationException ex ) {
            throw new ConfigParserException( ex.getMessage() );
        } catch ( ClassCastException ex ) {
            throw new ConfigParserException(
                    ConfigParserError.TYPE_PARSING_EXCEPTION, line.getText() );
        }
    }


    /**
     * Retrieves an option name from the line
     *
     * @param ConfigLine line
     * @return String option name
     * @throws ConfigParserException whether the option name is not valid
     */
    private String getOptionName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( Format.EQUALS_SIGN );
        String name = StringUtils.trim(
                text.substring( 0, equalsSignPosition ) );

        if ( !isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_OPTION_NAME, name );
        }

        return name;
    }


    /**
     * Determines whether the given name is valid - it matches pattern
     * [a-zA-Z.:$][a-zA-Z0-9_~.:$ -]*
     *
     * @param String name
     * @return boolean
     */
    private boolean isValidName( String name ) {
        Matcher m = Format.VALID_NAME_PATTERN.matcher( name );

        return m.matches();
    }


    /**
     * Retrieve list of values from the line
     *
     * @param ConfigLine line
     * @return List<Object> values
     */
    private List<Object> getOptionValues( ConfigLine line ) {
        String[] values = splitValues( getOptionValuesDefinition( line ) );

        List<Object> valuesList = new LinkedList<Object>();
        for ( String value : values ) {
            if ( isLinkDefinition( value ) ) {
                valuesList.add( new ValueLink( value, configuration ) );
            } else {
                valuesList.add( new RawValue( value ) );
            }
        }

        return valuesList;
    }


    /**
     * Retrieve option values as a string from the line
     *
     * @param ConfigLine line
     * @return String values in string separated by , or :
     */
    private String getOptionValuesDefinition( ConfigLine line ) {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( Format.EQUALS_SIGN );

        String rawValues = StringUtils.trimInlineComments(
                text.substring( equalsSignPosition + 1 ) );

        return StringUtils.trim( rawValues );
    }


    /**
     * Retreives OptionData for the option with the name.
     * If option with the name has schema it return appropriate OptionData
     * descendant. Otherwise default StringOptionData class is used.
     *
     * @param String name name of the option
     * @return OptionData
     */
    private OptionData getOptionData( String name ) {
        if ( currentSectionSchema.hasOption( name ) ) {
            return currentSectionSchema.getOption( name ).getOptionData();
        } else {
            return new StringOptionData();
        }
    }


    /**
     * Split list of values in the string using , or : delimiter.
     * 
     * @param String values
     * @return String[]
     */
    private String[] splitValues( String values ) {
        return getDelimiterPattern( values ).split( values );
    }


    /**
     * Returns delimiter pattern which matches the values string.
     *
     * @param values
     * @return Pattern
     */
    private Pattern getDelimiterPattern( String values ) {
        if ( isDelimitedByComma( values ) ) {
            return Format.COMMA_DELIMITER_PATTERN;
        } else if ( isDelimitedByColon( values ) ) {
            return Format.COLON_DELIMITER_PATTERN;
        } else {
            return Format.NO_DELIMITER_PATTERN;
        }
    }


    /**
     * Returns a comment from the comment buffer and resets the buffer.
     *
     * @return String comment
     */
    private String getComment() {
        String comment = currentComment.toString();
        currentComment = new StringBuilder();

        return comment;
    }


    /**
     * Returns a comment from the appropriate option schema definition.
     *
     * @param String name Option name
     * @return String comment in option schema
     */
    private String getCommentForOption( String name ) {
        if ( currentSectionSchema.hasOption( name ) ) {
            return currentSectionSchema.getOption( name ).getComment();
        } else {
            return "";
        }
    }


    /**
     * Returns a comment from the current section schema
     *
     * @return String comment in section schema
     */
    private String getCommentForCurrentSection() {
        return currentSectionSchema.getComment();
    }


    /**
     *  Determines whether the given value is a link definition
     * 
     * @param String value
     * @return boolean
     */
    private boolean isLinkDefinition( String value ) {
        return value.startsWith( Format.LINK_DEFINITION_START );
    }


    /**
     * Determines whether the values in the given string are delimited by comma
     *
     * @param String values
     * @return boolean
     */
    private boolean isDelimitedByComma( String values ) {
        Pattern pattern = Format.COMMA_DELIMITER_PATTERN;

        return findOccurence( pattern, values );
    }


    /**
     * Determines whether the values in the given string are delimited by colon
     *
     * @param String values
     * @return boolean
     */
    private boolean isDelimitedByColon( String values ) {
        Pattern pattern = Format.COLON_DELIMITER_PATTERN;

        return findOccurence( pattern, values );
    }


    /**
     * Determine whether the text contains occurence of the pattern
     * 
     * @param Pattern pattern
     * @param String text
     * @return boolean
     */
    private boolean findOccurence( Pattern pattern, String text ) {
        Matcher m = pattern.matcher( text );

        return m.find();
    }


    /**
     * Add options which has not been set during parsing input and has default
     * values to the configuration data.
     */
    private void addMissingOptionsWithDefaultValue() {
        for ( SectionSchema sectionSchema : configuration.getSchema() ) {
            if( ! configuration.hasSection( sectionSchema.getName() ) ) {
                configuration.addSection( sectionSchema.getName(),
                        new SectionData() );
            }

            SectionData sectionData = configuration.getSection(
                    sectionSchema.getName() );

            for ( OptionSchema optionSchema : sectionSchema ) {
                if ( optionSchema.hasDefaultValue() &&
                        !sectionData.hasOption( optionSchema.getName() ) ) {
                    OptionData option = optionSchema.getOptionData();
                    option.setValues( optionSchema.getDefaultValues() );
                    option.setComment( optionSchema.getComment(), "" );

                    sectionData.addOption( optionSchema.getName(), option );
                }
            }
        }
    }
}
