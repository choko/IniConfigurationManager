package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;


/**
 * The <code>BooleanOptionData</code> class extends OptionData of
 * <code>boolean</code> value type.
 * <p>
 * In addition, this class provides method for
 * parsing a <code>RawValue</code> to a <code>boolean</code>,
 * method for returnig class of <code>BooleanOptionData</code>
 * parsing method and valueToString method that returns <code>string</code>
 */

public class BooleanOptionData
        extends OptionData {

    /**
     * The <code>rawBoolean</code> <code>String</code> holds unparsed value
     * of item for same valueToString outout as imput
     */
    private String rawBoolean;


    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     *
     * @return Boolean.class
     */
    @Override
    protected Class getValueClass() {
        return Boolean.class;
    }


    /**
     * The <code>parseValue</code> provade parsing of <code>RawValue</code>
     * value to the <code>boolean</code> value. It can parse all valid
     * meaning of true or false
     *
     * @return boolean
     */
    @Override
    protected Object parseValue( RawValue value ) {
        rawBoolean = value.getValue();
        try {
            if ( value.getValue().equalsIgnoreCase( ZERO ) ||
                    value.getValue().equalsIgnoreCase( SHORT_FALSE ) ||
                    value.getValue().equalsIgnoreCase( SHORT_NO ) ||
                    value.getValue().equalsIgnoreCase( FALSE ) ||
                    value.getValue().equalsIgnoreCase( OFF ) ||
                    value.getValue().equalsIgnoreCase( NO ) ||
                    value.getValue().equalsIgnoreCase( DISABLED ) ) {
                return false;
            } else {
                if ( value.getValue().equalsIgnoreCase( ONE ) ||
                        value.getValue().equalsIgnoreCase( SHORT_TRUE ) ||
                        value.getValue().equalsIgnoreCase( SHORT_YES ) ||
                        value.getValue().equalsIgnoreCase( TRUE ) ||
                        value.getValue().equalsIgnoreCase( ON ) ||
                        value.getValue().equalsIgnoreCase( YES ) ||
                        value.getValue().equalsIgnoreCase( ENABLED ) ) {
                    return true;
                }
            }
        } catch ( Exception e ) {
            throw new ClassCastException();
        }

        return true;
    }

    /**
     * The <code>valueToString</code> provade oposite of parseValue.
     * Its return <code>String</code> from value in same syntax as original
     * unparsed string-value
     *
     * @return String
     */
    @Override
    protected String valueToString( Object value ) {
        if ( (Boolean) value ) {
            return getFormatedTrue( rawBoolean );
        } else {
            return getFormatedFalse( rawBoolean );
        }
    }

    /**
     * The <code>getFormatedFalse</code> return string that reprezents
     * false value in same syntax as original value
     *
     * @return String
     */
    private String getFormatedFalse( String rawBoolean ) {
        if ( rawBoolean.equalsIgnoreCase( ZERO ) ||
                rawBoolean.equalsIgnoreCase( ONE ) ) {
            return ZERO;
        }

        if ( rawBoolean.equalsIgnoreCase( SHORT_FALSE ) ||
                rawBoolean.equalsIgnoreCase( SHORT_TRUE ) ) {
            return SHORT_FALSE;
        }

        if ( rawBoolean.equalsIgnoreCase( SHORT_NO ) ||
                rawBoolean.equalsIgnoreCase( SHORT_TRUE ) ) {
            return SHORT_NO;
        }

        if ( rawBoolean.equalsIgnoreCase( FALSE ) ||
                rawBoolean.equalsIgnoreCase( TRUE ) ) {
            return FALSE;
        }

        if ( rawBoolean.equalsIgnoreCase( OFF ) ||
                rawBoolean.equalsIgnoreCase( ON ) ) {
            return OFF;
        }

        if ( rawBoolean.equalsIgnoreCase( NO ) ||
                rawBoolean.equalsIgnoreCase( YES ) ) {
            return NO;
        }

        if ( rawBoolean.equalsIgnoreCase( DISABLED ) ||
                rawBoolean.equalsIgnoreCase( ENABLED ) ) {
            return DISABLED;
        }

        return FALSE;
    }

    /**
     * The <code>getFormatedFalse</code> return string that reprezents
     * true value in same syntax as original value
     *
     * @return String
     */
    private String getFormatedTrue( String rawBoolean ) {
        if ( rawBoolean.equalsIgnoreCase( ZERO ) ||
                rawBoolean.equalsIgnoreCase( ONE ) ) {
            return ONE;
        }

        if ( rawBoolean.equalsIgnoreCase( SHORT_FALSE ) ||
                rawBoolean.equalsIgnoreCase( SHORT_TRUE ) ) {
            return SHORT_TRUE;
        }

        if ( rawBoolean.equalsIgnoreCase( SHORT_NO ) ||
                rawBoolean.equalsIgnoreCase( SHORT_TRUE ) ) {
            return SHORT_YES;
        }

        if ( rawBoolean.equalsIgnoreCase( FALSE ) ||
                rawBoolean.equalsIgnoreCase( TRUE ) ) {
            return TRUE;
        }

        if ( rawBoolean.equalsIgnoreCase( OFF ) ||
                rawBoolean.equalsIgnoreCase( ON ) ) {
            return ON;
        }

        if ( rawBoolean.equalsIgnoreCase( NO ) ||
                rawBoolean.equalsIgnoreCase( YES ) ) {
            return YES;
        }

        if ( rawBoolean.equalsIgnoreCase( DISABLED ) ||
                rawBoolean.equalsIgnoreCase( ENABLED ) ) {
            return ENABLED;
        }

        return TRUE;
    }

    /**
     * This constant hold all posible string reprezentation of false string
     */
    private static final String ZERO = "0";

    private static final String SHORT_FALSE = "f";

    private static final String SHORT_NO = "n";

    private static final String FALSE = "false";

    private static final String OFF = "off";

    private static final String NO = "no";

    private static final String DISABLED = "disabled";

     /**
     * This constant hold all posible string reprezentation of false string
     */
    private static final String ONE = "1";

    private static final String SHORT_TRUE = "t";

    private static final String SHORT_YES = "y";

    private static final String TRUE = "true";

    private static final String ON = "on";

    private static final String YES = "yes";

    private static final String ENABLED = "enebled";
}
