package iniconfigurationmanager.options;

/**
 * The <code>FloatOptionData</code> class extends OptionData of
 * <code>Float</code> value type.
 * <p>
 * In addition, this class provides method for
 * parsing a <code>RawValue</code> to a <code>float</code>,
 * method for returnig class of <code>FloatOptionData</code>
 * parsing method and valueToString method that returns <code>string</code>
 */

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;


public class FloatOptionData
        extends OptionData {

    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     */
    @Override
    public Class getValueClass() {
        return Float.class;
    }

    /**
     * The <code>parseValue</code> provade parsing of <code>RawValue</code>
     * value to the <code>Float</code> value. Float value is standart IEEE 754
     * format.
     */
    @Override
    public Object parseValue( RawValue value ) {
       
        try {
            return  Float.parseFloat( value.getValue() );

        } catch ( Exception e ) {
            throw new ClassCastException();
        }        
    }
    /**
     * The <code>valueToString</code> provade oposite of parseValue.
     * Its return <code>String</code> from value in standart IEEE 754
     */
    @Override
    public String valueToString( Object value ) {
        return value.toString();
    }
}
