package iniconfigurationmanager.utils;

/**
 * NumberUtils unites usefull methods for working with numbers.
 */
public class NumberUtils {

    public static String HEXPREFIX = "0x";

    public static String OCTAPREFIX = "0b";

    public static String BINARYPREFIX = "0";


    /**
     * Determines whether the given number is in hexadecimal format.
     *
     * @param String number
     * @return boolean
     */
    public boolean isHexFormat( String number ) {
        return number.startsWith( HEXPREFIX );
    }


    /**
     * Determines whether the given number is in octal format.
     *
     * @param String number
     * @return boolean
     */
    public boolean isOctaFormat( String number ) {
        return number.startsWith( OCTAPREFIX );
    }


    /**
     * Determines whether the given number is in binary format.
     *
     * @param String number
     * @return boolean
     */
    public boolean isBinaryFormat( String format ) {
        return format.startsWith( BINARYPREFIX )
               && !format.equalsIgnoreCase(ZERO);
    }

    private static String ZERO = "0";
}
