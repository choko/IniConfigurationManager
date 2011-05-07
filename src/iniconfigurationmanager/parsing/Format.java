
package iniconfigurationmanager.parsing;

import java.util.regex.Pattern;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class Format {

    public static final String OPTION_CANONICAL_NAME_FORMAT = "%s#%s";

    public static final String OPTION_FORMAT = "%s=%s\n";

    public static final String OPTION_WITH_COMMENT_FORMAT = ";%s\n%s=%s\n";
    
    public static final String EQUALS_SIGN = "=";

    public static final String VALUES_COMMA_DELIMITER = ",";

    public static final String NEWLINE = System.getProperty( "line.separator" );

    public static final String LINK_DEFINITION_START = "${";

    public static final String LINK_DEFINITION_END = "}";

    public static final String LINK_FORMAT = "${%s}";

    public static final String SECTION_DEFINITION_START = "[";

    public static final String SECTION_DEFINITION_END = "]";

    public static final char COMMENT_START = ';';

    public static final char WHITESPACE = ' ';

    public static final char ESCAPE = '\\';

    public static final Pattern VALID_NAME_PATTERN =
        Pattern.compile("[a-zA-Z.:$][a-zA-Z0-9_~.:$ -]*");

    public static final Pattern COMMA_DELIMITER_PATTERN =
        Pattern.compile("(?<!\\\\),");

    public static final Pattern COLON_DELIMITER_PATTERN =
        Pattern.compile("(?<!\\\\):");

    public static final Pattern NO_DELIMITER_PATTERN = COMMA_DELIMITER_PATTERN;

}
