package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.utils.NumberUtils;
import java.math.BigInteger;

/**
 * The <code>UnsignedOptionData</code> class extends OptionData of
 * <code>BigInteger</code> value type.
 * <p>
 * Becouse java doesnt have 64bit unsigned class, it is reprezents in
 * <code>BigInteger</code> but using only positive range of number
 * In addition, this class provides method for
 * parsing a <code>RawValue</code> to a <code>UnsignedOptionData</code>,
 * method for returnig class of <code>BooleanOptionData</code>
 * parsing method and valueToString method that returns <code>string</code>
 * whit radix same as parsed string
 */
public class UnsignedOptionData
        extends OptionData {

    private String rawUnsigned;


    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     */
    @Override
    protected Class getValueClass() {
        return BigInteger.class;
    }

    /**
     * The <code>parseValue</code> return string value of <code>RawValue</code>
     * @param value
     */
    @Override
    protected Object parseValue( RawValue value ) {
        rawUnsigned = value.getValue();
        UnsignedInt64 number = new UnsignedInt64( value.getValue() );

        return number.getValue();

    }

    /**
     * The <code>parseValue</code> provade parsing of <code>RawValue</code>
     * value to the <code>BigInteger</code> value. RawValue string can be in
     *  hexadecima, octadecimal, binary or standart format.
     * @param value
     */
    @Override
    protected String valueToString( Object value ) {
        BigInteger number = NumberUtils.toBigInteger( value );

        if( number.equals( BigInteger.ZERO ) ) {
            return Integer.toString( 0 );
        }

        if ( NumberUtils.isBinaryFormat( rawUnsigned ) ) {
            return NumberUtils.BINARYPREFIX.concat( number.toString( 2 ));
        } else if ( NumberUtils.isOctaFormat( rawUnsigned ) ) {
            return NumberUtils.OCTAPREFIX.concat( number.toString( 8 ));
        } else if ( NumberUtils.isHexFormat( rawUnsigned ) ) {
            return NumberUtils.HEXPREFIX.concat( number.toString( 16 ));
        } else {
            return value.toString();
        }
    }


     /**
      * Private class <code>UnsignedItem64</code> provide unsigned 64bit long
      * number.It have minimum posible value, maximum posible value and methods
      * to parsing from and to <code>String</code>
     */
    private class UnsignedInt64 {

        private BigInteger uint64;

        private final BigInteger B64 = BigInteger.ZERO.setBit( 64 );

        private final BigInteger minValue = BigInteger.ZERO;

        private final BigInteger maxValue =
                BigInteger.ZERO.setBit( 64 ).flipBit( 64 );

        
        public UnsignedInt64( String value ) {
            BigInteger rawUint64 = new BigInteger(
                    NumberUtils.trimPrefix( value ),
                    NumberUtils.getRadix( value ) );

            if (
                    rawUint64.compareTo( minValue ) >= 0  &&
                    rawUint64.compareTo( maxValue ) >= 0
            ) {
                uint64 = rawUint64;
            } else {
                throw new ClassCastException();
            }
        }


        public BigInteger getValue() {
            return uint64;
        }
    }
}
