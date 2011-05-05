
package iniconfigurationmanager.utils;

import iniconfigurationmanager.parsing.ConfigFormatDefinition;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringUtils {


    public static String trim( String text ) {
        String trimmedText = text.trim();

        if( trimmedText.endsWith( "" + ConfigFormatDefinition.ESCAPE ) ) {
            return trimmedText + ConfigFormatDefinition.WHITESPACE;
        } else {
            return trimmedText;
        }
    }


     public static String trimInlineComments( String line ) {
        int length = line.length();
        char last = ' ';

        for( int index = 0; index < line.length(); index++ ) {
            if(
                line.charAt( index ) == ConfigFormatDefinition.COMMENT_START &&
                last != ConfigFormatDefinition.ESCAPE
            ) {
                length = index;
                break;
            }

            last = line.charAt( index );
        }

        return line.substring(0, length);
    }
    
}
