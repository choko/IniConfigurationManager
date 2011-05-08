
package iniconfigurationmanager.utils;

import iniconfigurationmanager.parsing.Format;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringUtils {


    public static String trim( String text ) {
        String trimmedText = text.trim();

        if( trimmedText.endsWith( "" + Format.ESCAPE ) ) {
            return trimmedText + Format.WHITESPACE;
        } else {
            return trimmedText;
        }
    }


     public static String trimInlineComments( String line ) {
        int length = line.length();
        char last = ' ';

        for( int index = 0; index < line.length(); index++ ) {
            if(
                line.charAt( index ) == Format.COMMENT_START &&
                last != Format.ESCAPE
            ) {
                length = index;
                break;
            }

            last = line.charAt( index );
        }

        return line.substring(0, length);
    }


    public static String formatComment( String comment ) {
        StringBuilder sb = new StringBuilder();

        for (char ch : comment.trim().toCharArray()) {
            sb.append(ch);

            if (ch == Format.NEWLINE.charAt(0)) {
                sb.append(Format.COMMENT_START);
            }
        }

        return sb.toString();
    }

    public static String addSlashes( String slashedString ) {
       String slashedFreeString = slashedString;
       
       slashedFreeString.replaceAll(",", "\\,");
       slashedFreeString.replaceAll(":", "\\:");
       slashedFreeString.replaceAll(";", "\\;");

       return slashedFreeString;
    }

    public static String removeSlashes( String slashlessString ) {
       String slashedString = slashlessString;

       slashedString.replaceAll( "\\,","," );
       slashedString.replaceAll( "\\:",":" );
       slashedString.replaceAll( "\\;",";" );

       return slashedString;

    }
    
}
