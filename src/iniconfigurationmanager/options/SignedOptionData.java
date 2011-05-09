package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.utils.NumberUtils;

/**
 * The <code>SignedOptionData</code> class extends OptionData of
 * <code>Long</code> value type.
 * <p>
 * In addition, this class provides method for
 * parsing a <code>RawValue</code> to a <code>Long</code>,
 * method for returnig class of <code>SignedOptionData</code>
 * parsing method and valueToString method that returns <code>string</code>
 */
public class SignedOptionData
        extends OptionData {

    /**
     * The <code>rawStringvalue</code> <code>String</code> holds unparsed value
     * of item for same valueToString outout format as imput
     */
    private String rawStringvalue;


    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     *
     * @return Long.class
     */
    @Override
    protected Class getValueClass() {
        return Long.class;
    }

    /**
     * The <code>parseValue</code> provade parsing of <code>RawValue</code>
     * value to the <code>Long</code> value. RawValue string can be in
     *  hexadecima, octadecimal, binary or standart format.
     *
     * @return long
     */
    @Override
    protected Object parseValue( RawValue value ) {
        try {
            rawStringvalue = value.getValue();
            return Long.decode( value.getValue() );
        } catch ( Exception e ) {
            throw new ClassCastException();
        }

    }

    /**
     * The <code>valueToString</code> provade oposite of parseValue.
     * Its return <code>String</code> from value in same numeral system
     * with a radix 16,10,8,2 whit corresponding prefix
     *
     * @return String
     */
    @Override
    protected String valueToString( Object value ) {
        Long longValue = NumberUtils.toLong( value );

        if( longValue == 0) {
            return Integer.toString( 0 );
        }

        if ( NumberUtils.isBinaryFormat( rawStringvalue ) ) {
            return NumberUtils.BINARYPREFIX.concat( Long.toBinaryString(
                    longValue ) );
        } else if ( NumberUtils.isOctaFormat( rawStringvalue ) ) {
            return NumberUtils.OCTAPREFIX.concat(
                    Long.toOctalString( longValue ) );
        } else if ( NumberUtils.isHexFormat( rawStringvalue ) ) {
            return NumberUtils.HEXPREFIX.concat( Long.toHexString( longValue ) );
        } else {
            return longValue.toString();
        }
    }

}
