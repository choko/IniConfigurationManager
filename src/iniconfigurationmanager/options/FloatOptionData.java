package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;


/**
 * The <code>FloatOptionData</code> class extends OptionData of
 * <code>Float</code> value type.
 * <p>
 * In addition, this class provides method for
 * parsing a <code>RawValue</code> to a <code>float</code>,
 * method for returnig class of <code>FloatOptionData</code>
 * parsing method and valueToString method that returns <code>string</code>
 */
public class FloatOptionData
        extends OptionData {

    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     *
     * @return Float.class
     */
    @Override
    protected Class getValueClass() {
        return Float.class;
    }

    /**
     * The <code>parseValue</code> provade parsing of <code>RawValue</code>
     * value to the <code>Float</code> value. Float value is standart IEEE 754
     * format
     *
     * @return  Float
     */
    @Override
    protected Object parseValue( RawValue value ) {
       
        try {
            return  Float.parseFloat( value.getValue() );

        } catch ( Exception e ) {
            throw new ClassCastException();
        }        
    }
    /**
     * The <code>valueToString</code> provade oposite of parseValue.
     * Its return <code>String</code> from value in standart IEEE 754
     *
     * @return String
     */
    @Override
    protected String valueToString( Object value ) {
        return value.toString();
    }
}
