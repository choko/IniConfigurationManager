
package iniconfigurationmanager.parsing;

import iniconfigurationmanager.ConfigLine;
import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.schema.ConfigData;
import iniconfigurationmanager.schema.ConfigSchema;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.items.StringConfigItem;
import iniconfigurationmanager.schema.ConfigItemSchema;
import iniconfigurationmanager.schema.ConfigSectionSchema;
import iniconfigurationmanager.schema.NullConfigSectionData;
import iniconfigurationmanager.schema.NullConfigSectionSchema;
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

    private ConfigSchema schema;

    private ConfigData configuration;

    private ConfigSectionSchema currentSectionSchema;

    private ConfigSectionData currentSectionData;
    
    private StringBuilder currentComment;
    

    public ConfigParser( ConfigSchema schema, ConfigData configuration ) {
        this.schema = schema;
        this.configuration = configuration;
        this.configuration.setSchema( schema );
        this.currentSectionData = NullConfigSectionData.getInstance();
        this.currentSectionSchema = new NullConfigSectionSchema( "" );
        this.currentComment = new StringBuilder();
    }

    
    public ConfigData parse(List<ConfigLine> lines)
            throws ConfigParserException {
        for (ConfigLine line : lines) {
            if (line.isComment()) {
                parseComment(line);
            } else if (line.isSectionHeader()) {
                parseSectionHeader(line);
            } else if (line.isItemDefinition()) {
                parseItemDefinition(line);
            } else {
                if (!line.isEmpty()) {
                    throw new ConfigParserException(
                            ConfigParserError.UNEXPECTED_LINE, line.getText() );
                }
            }
        }

        addMissingItemsWithDefaultValue();

        return configuration;
    }


    private void parseComment( ConfigLine line ) {
        currentComment.append( line.getText().trim().substring( 1 ) );
        currentComment.append( ConfigFormatDefinition.NEWLINE );
    }

    
    private void parseSectionHeader( ConfigLine line ) 
            throws ConfigParserException {
        String name = getSectionName( line );

        if( schema.hasSection( name )) {
            currentSectionSchema = schema.getSection( name );
        } else {
            currentSectionSchema = new NullConfigSectionSchema( name );
        }

        currentSectionData = new ConfigSectionData( name );

        configuration.addSection( name, currentSectionData );
    }

    
    private String getSectionName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        String name = text.substring(
            text.indexOf( ConfigFormatDefinition.SECTION_DEFINITION_START ) + 1,
            text.indexOf( ConfigFormatDefinition.SECTION_DEFINITION_END )
        );

        if( ! isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_SECTION_NAME, name);
        }

        return name;
    }
    

    private void parseItemDefinition( ConfigLine line )
            throws ConfigParserException {
        try {
            String name = getItemName( line );
            List< Object > values = getItemValues( line );

            ConfigItemData item = new ConfigItemData(
                    name, getCurrentSectionName(),
                    configuration, getFormatDefinition( name ));

            item.setValues( values );
            item.setComment( getCommentForItem( name ), getComment() );

            System.out.println("Adding " + name + " to " + currentSectionSchema.getName());
            currentSectionData.addItem( name, item );
        } catch( InvalidOperationException ex ) {
            throw new ConfigParserException(
                    ConfigParserError.UNDEFINED_SECTION, line.getText() );
        } catch( ClassCastException ex ) {
            throw new ConfigParserException(
                    ConfigParserError.TYPE_PARSER_EXCEPTION, line.getText() );
        }
    }

    
    private String getItemName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( ConfigFormatDefinition.EQUALS_SIGN );
        String name = StringUtils.trim(
                text.substring(0, equalsSignPosition ) );

        if( ! isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_ITEM_NAME, name);
        }
        
        return name;
    }


    private boolean isValidName( String name ) {
        Matcher m = ConfigFormatDefinition.VALID_NAME_PATTERN.matcher( name );

        return m.matches();
    }

    
    private List< Object > getItemValues( ConfigLine line ) {
        String[] values = splitValues( getItemValuesDefinition( line ) );

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
    

    private String getItemValuesDefinition( ConfigLine line ) {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( ConfigFormatDefinition.EQUALS_SIGN );
        
        String rawValues = StringUtils.trimInlineComments(
                    text.substring(equalsSignPosition + 1) );

        return StringUtils.trim( rawValues );
    }


    private String getCurrentSectionName() {
        return currentSectionSchema.getName();
    }
    

    private ConfigItemFormatDefinition getFormatDefinition( String name ) {
        if( currentSectionSchema.hasItem( name ) ) {
            ConfigItemSchema itemSchema = currentSectionSchema.getItem( name );
            return itemSchema.getFormatDefinition();
        } else {
            return new StringConfigItem();
        }
    }

    
    private String[] splitValues( String values ) {
        return getDelimiterPattern( values ).split( values );
    }

    
    private Pattern getDelimiterPattern( String values ) {
        if( isDelimitedByComma( values ) ) {
            return ConfigFormatDefinition.COMMA_DELIMITER_PATTERN;
        } else if ( isDelimitedByColon( values ) ) {
            return ConfigFormatDefinition.COLON_DELIMITER_PATTERN;
        } else {
            return ConfigFormatDefinition.NO_DELIMITER_PATTERN;
        }
    }


    private String getComment() {
        String comment = currentComment.toString();
        currentComment = new StringBuilder();

        return comment;
    }


    private String getCommentForItem( String name ) {
        if( currentSectionSchema.hasItem( name ) ) {
            return currentSectionSchema.getItem( name ).getComment();
        } else {
            return "";
        }
    }


    private boolean isLinkDefinition( String value ) {
        return value.startsWith( ConfigFormatDefinition.LINK_DEFINITION_START );
    }
    

    private boolean isDelimitedByComma( String values ) {
        Pattern pattern = ConfigFormatDefinition.COMMA_DELIMITER_PATTERN;

        return findOccurence( pattern, values );
    }


    private boolean isDelimitedByColon( String values ) {
        Pattern pattern = ConfigFormatDefinition.COLON_DELIMITER_PATTERN;

        return findOccurence( pattern, values );
    }

    
    private boolean findOccurence( Pattern pattern, String text ) {
        Matcher m = pattern.matcher( text );
        
        return m.find();
    }

    
    private void addMissingItemsWithDefaultValue() {
        for( ConfigSectionSchema sectionSchema : configuration.getSchema() ) {
            ConfigSectionData sectionData = configuration.getSection(
                    sectionSchema.getName() );

            for( ConfigItemSchema itemSchema : sectionSchema ) {
                if( 
                    itemSchema.hasDefaultValue() &&
                    ! sectionData.hasItem( itemSchema.getName() )
                ) {
                    ConfigItemData item = new ConfigItemData(
                            itemSchema.getName(),
                            sectionSchema.getName(),
                            configuration,
                            itemSchema.getFormatDefinition());

                    item.setValues( itemSchema.getDefaultValues() );
                    item.setComment( itemSchema.getComment(), "");

                    sectionData.addItem( itemSchema.getName(), item );
                }
            }
        }
    }
    
}
