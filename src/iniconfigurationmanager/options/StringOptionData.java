package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.utils.StringUtils;

/**
 * The <code>StringOptionData</code> class extends OptionData of
 * <code>String</code> value type.
 */
public class StringOptionData
        extends OptionData {

    public StringOptionData() {
    }

    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     */
    @Override
    protected Class getValueClass() {
        return String.class;
    }

    /**
     * The <code>parseValue</code> return string value of <code>RawValue</code>
     * that have removed slashes
     *
     * @return String
     */
    @Override
    protected Object parseValue( RawValue value ) {
        return StringUtils.removeSlashes( value.getValue() );
    }

    /**
     * The <code>valueToString</code> return string that have added slasses
     *
     * @return String
     */
    @Override
    protected String valueToString( Object value ) {
        return StringUtils.addSlashes( value.toString() );
    }
}
