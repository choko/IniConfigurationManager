/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.ConfigParserError;
import iniconfigurationmanager.parsing.ConfigParserException;
import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;


/**
 *
 * @author KlonK <jurko@bdi.sk>
 */
public class BooleanOptionData extends OptionData {

    String rawBoolean;

    public Class getValueClass() {
       return Boolean.class;
    }

    public Object parseValue(RawValue value) {
        try {
            if (value.getValue().equalsIgnoreCase( ZERO ) ||
                value.getValue().equalsIgnoreCase( SHORT_FALSE ) ||
                value.getValue().equalsIgnoreCase( SHORT_NO ) ||
                value.getValue().equalsIgnoreCase( FALSE ) ||
                value.getValue().equalsIgnoreCase( OFF ) ||
                value.getValue().equalsIgnoreCase( NO ) ||
                value.getValue().equalsIgnoreCase( DISABLED ) ) {
         return false;
        } else {
            if (value.getValue().equalsIgnoreCase( ONE ) ||
                value.getValue().equalsIgnoreCase( SHORT_TRUE ) ||
                value.getValue().equalsIgnoreCase( SHORT_YES ) ||
                value.getValue().equalsIgnoreCase( TRUE ) ||
                value.getValue().equalsIgnoreCase( ON ) ||
                value.getValue().equalsIgnoreCase( YES ) ||
                value.getValue().equalsIgnoreCase( ENABLED ) )
         return true;
        }
        } catch (Exception e) {
            throw new  ClassCastException();
        }

        return true;
    }

    public String valueToString(Object value) {
        if ((Boolean) value  ) {
            return  getFormatedTrue( rawBoolean );
        }
        else
        {
            return  getFormatedFalse( rawBoolean );
        }
    }

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


    static final String ZERO = "0";
    static final String SHORT_FALSE = "f";
    static final String SHORT_NO = "n";
    static final String FALSE = "false";
    static final String OFF = "off";
    static final String NO = "no";
    static final String DISABLED = "disabled";

    static final String ONE = "1";
    static final String SHORT_TRUE = "t";
    static final String SHORT_YES = "y";
    static final String TRUE = "true";
    static final String ON = "on";
    static final String YES = "yes";
    static final String ENABLED = "enebled";

}
