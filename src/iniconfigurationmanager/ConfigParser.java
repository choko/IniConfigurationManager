
package iniconfigurationmanager;

import iniconfigurationmanager.items.ConfigItem;
import iniconfigurationmanager.items.StringConfigItem;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigParser {

    public static final char WHITESPACE = ' ';

    public static final char ESCAPE = '\\';
    
    public static final String NEWLINE = System.getProperty("line.separator");

    private static final Pattern VALID_NAME_PATTERN =
        Pattern.compile("[a-zA-Z.:$][a-zA-Z0-9_~.:$ -]*");

    private static final Pattern COMMA_DELIMITER_PATTERN =
        Pattern.compile("(?<!\\\\),");

    private static final Pattern COLON_DELIMITER_PATTERN =
        Pattern.compile("(?<!\\\\):");

    private static final Pattern NO_DELIMITER_PATTERN = COMMA_DELIMITER_PATTERN;
    
    private ConfigSchema schema;

    private ConfigData configuration;

    private ConfigSection currentSection;
    
    private StringBuilder currentComment;
    

    public ConfigParser( ConfigSchema schema, ConfigData configuration ) {
        this.schema = schema;
        this.configuration = configuration;
        this.configuration.setSchema( schema );
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
                            ConfigParserError.UNEXPECTED_LINE, line);
                }
            }
        }

        return configuration;
    }


    private void parseComment( ConfigLine line ) {
        currentComment.append( line.getText() );
        currentComment.append( NEWLINE );
    }

    
    private void parseSectionHeader( ConfigLine line ) 
            throws ConfigParserException {
        String name = getSectionName( line );

        if( schema.hasSection( name )) {
            currentSection = (ConfigSection) schema.getSection( name ).clone();
        } else {
            currentSection = new ConfigSection( name );
        }

        configuration.addSection( name, currentSection );
    }

    
    private String getSectionName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        String name = text.substring(
            text.indexOf( ConfigLine.SECTION_DEFINITION_START ) + 1,
            text.indexOf( ConfigLine.SECTION_DEFINITION_END )
        );

        if( ! isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_SECTION_NAME, name);
        }

        return name;
    }
    

    private void parseItemDefinition( ConfigLine line )
            throws ConfigParserException {
        String name = getItemName( line );
        String comment = getComment();
        List< Object > values = getItemValues( line );

        if( ! currentSection.hasItem( name ) ) {
            currentSection.addItem( name, new StringConfigItem( name ) );
        }

        ConfigItem item = currentSection.getItem( name );
        item.setValues( values );
        item.setComment( comment );
    }

    
    private String getItemName( ConfigLine line )
            throws ConfigParserException {
        String text = line.getText();
        int equalsSignPosition = text.indexOf( ConfigLine.EQUALS_SIGN );
        String name = trim( text.substring(0, equalsSignPosition ) );

        if( ! isValidName( name ) ) {
            throw new ConfigParserException(
                    ConfigParserError.INVALID_ITEM_NAME, name);
        }
        
        return name;
    }


    private boolean isValidName( String name ) {
        Matcher m = VALID_NAME_PATTERN.matcher( name );

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
        int equalsSignPosition = text.indexOf( ConfigLine.EQUALS_SIGN );

        return trim( text.substring(equalsSignPosition + 1) );
    }


    private String[] splitValues( String values ) {
        return getDelimiterPattern( values ).split( values );
    }

    
    private Pattern getDelimiterPattern( String values ) {
        if( isDelimitedByComma( values ) ) {
            return COMMA_DELIMITER_PATTERN;
        } else if ( isDelimitedByColon( values ) ) {
            return COLON_DELIMITER_PATTERN;
        } else {
            return NO_DELIMITER_PATTERN;
        }
    }


    private String getComment() {
        String comment = currentComment.toString();
        currentComment = new StringBuilder();

        return comment;
    }


    private boolean isLinkDefinition( String value ) {
        return value.startsWith("${");
    }
    

    private boolean isDelimitedByComma( String values ) {
        return findOccurence( COMMA_DELIMITER_PATTERN, values );
    }


    private boolean isDelimitedByColon( String values ) {
        return findOccurence( COLON_DELIMITER_PATTERN, values );
    }

    
    private boolean findOccurence( Pattern pattern, String text ) {
        Matcher m = pattern.matcher( text );
        
        return m.find();
    }
    
    
    
    private String trim( String text ) {
        int start = 0;
        while(
            start < text.length() &&
            text.charAt(start) == WHITESPACE
        ) {
            start++;
        }

        int end = text.length() - 1;
        while(
            end > start + 1 &&
            text.charAt( end ) == WHITESPACE &&
            text.charAt( end - 1 ) != ESCAPE
        ) {
            end--;
        }

        return text.substring( start, end + 1 );
    }
    
}
