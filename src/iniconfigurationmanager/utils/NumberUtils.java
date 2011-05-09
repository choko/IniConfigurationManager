package iniconfigurationmanager.utils;

import java.math.BigInteger;

/**
 * NumberUtils unites usefull methods for working with numbers.
 */
public class NumberUtils {

    /**
     * This String constant holds posible prefix of number whit non 10 radix
     */

    public static String HEXPREFIX = "0x";

    public static String OCTAPREFIX = "0";

    public static String BINARYPREFIX = "0b";


    /**
     * Determines whether the given number is in hexadecimal format.
     *
     * @param String number
     * @return boolean
     */
    public static boolean isHexFormat( String number ) {
        return number.startsWith( HEXPREFIX );
    }


    /**
     * Determines whether the given number is in octal format.
     *
     * @param String number
     * @return boolean
     */
    public static boolean isOctaFormat( String number ) {
        return number.startsWith( OCTAPREFIX );
    }


    /**
     * Determines whether the given number is in binary format.
     *
     * @param String number
     * @return boolean
     */
    public static boolean isBinaryFormat( String format ) {
        return format.startsWith( BINARYPREFIX );
    }

    /**
     * Method<code>getRadix</code> retun radix of number given in string
     * 
     * @param value
     * @return
     */

    public static int getRadix( String value ) {
        if ( isBinaryFormat( value ) ) {
            return 2;
        } else if ( isOctaFormat( value ) ) {
            return 8;
        } else if( isHexFormat( value ) ) {
            return 16;
        } else {
            return 10;
        }
    }

    /**
     * Method<code>toLong</code> convert Object given in parameter to long
     *
     * @param value
     * @return long
     */


    public static String trimPrefix( String value ) {
        if( isBinaryFormat( value ) ) {
            return value.substring( BINARYPREFIX.length() );
        } else if ( isOctaFormat( value ) ) {
            return value.substring( OCTAPREFIX.length() );
        } else if ( isHexFormat( value ) ) {
            return value.substring( HEXPREFIX.length() );
        } else {
            return value;
        }
    }

    
    public static long toLong( Object value ) {
        if ( value instanceof Integer ) {
            return ((Integer) value).longValue();
        } else if ( value instanceof Long ) {
            return (Long) value;
        } else {
            throw new ClassCastException();
        }
    }

    /**
     * Method <code>BigInteger<code> convert Object given in parameter to
     * BigInteger
     *
     * @param value
     * @return 
     */

    public static BigInteger toBigInteger( Object value ) {
        if ( value instanceof Integer ) {
            return BigInteger.valueOf( ((Integer) value).longValue() );
        } else if ( value instanceof Long ) {
            return BigInteger.valueOf( (Long) value );
        } else if ( value instanceof BigInteger ) {
            return (BigInteger) value;
        } else {
            throw new ClassCastException();
        }
    }
}