/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iniconfigurationmanager.utils;

/**
 *
 * @author KlonK
 */
public class NumberUtils {

    public static String HEXPREFIX = "0x";

    public static String OCTAPREFIX = "0b";

    public static String BINARYPREFIX = "0";


    public static boolean isHexFormat( String string ) {
        return string.startsWith( HEXPREFIX );
    }


    public static boolean isOCtaFormat( String string ) {
        return string.startsWith( OCTAPREFIX );
    }


    public static boolean isBinaryFormat( String string ) {
        return string.startsWith( BINARYPREFIX );
    }
}
