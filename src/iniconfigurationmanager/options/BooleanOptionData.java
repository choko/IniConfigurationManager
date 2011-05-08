package iniconfigurationmanager.options;

/**
 * The <code>BooleanOptionData</code> class extends OptionData of
 * <code>boolean</code> value type.
 * <p>
 * In addition, this class provides method for
 * parsing a <code>RawValue</code> to a <code>boolean</code>,
 * method for returnig class of <code>BooleanOptionData</code>
 * parsning method and valueToString method that returns <code>string</code>
 */

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;


public class BooleanOptionData
        extends OptionData {

    /**
     * The <code>rawBoolean</code> <code>String</code> holds unparsed value
     * of item for same valueToString outout as imput
     */
    String rawBoolean;


    /**
     * The <code>getValueClass</code> method return <code>Class</code> of witch
     * is result of parsing
     */
    public Class getValueClass() {
        return Boolean.class;
    }


    /**
     * The <code>parseValue</code> provade parsing of <code>RawValue</code>
     * value to the <code>boolean</code> value. It can parse all valid
     * meaning of true or false
     */
    public Object parseValue( RawValue value ) {
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
     */
    public String valueToString( Object value ) {
        if ( (Boolean) value ) {
            return getFormatedTrue( rawBoolean );
        } else {
            return getFormatedFalse( rawBoolean );
        }
    }

    /**
     * The <code>getFormatedFalse</code> return string that reprezents
     * false value in same syntax as original value;
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
     * true value in same syntax as original value;
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
    static final String ZERO = "0";

    static final String SHORT_FALSE = "f";

    static final String SHORT_NO = "n";

    static final String FALSE = "false";

    static final String OFF = "off";

    static final String NO = "no";

    static final String DISABLED = "disabled";

     /**
     * This constant hold all posible string reprezentation of false string
     */
    static final String ONE = "1";

    static final String SHORT_TRUE = "t";

    static final String SHORT_YES = "y";

    static final String TRUE = "true";

    static final String ON = "on";

    static final String YES = "yes";

    static final String ENABLED = "enebled";
}
