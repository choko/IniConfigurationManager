
package iniconfigurationmanager.utils;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringUtils {


     public static String trim( String text ) {
        int start = 0;
        while(
            start < text.length() &&
            text.charAt(start) == ' '
        ) {
            start++;
        }

        int end = text.length() - 1;
        while(
            end > start + 1 &&
            text.charAt( end ) == ' ' &&
            text.charAt( end - 1 ) != '\\'
        ) {
            end--;
        }

        return text.substring( start, end + 1 );
    }

    
}
