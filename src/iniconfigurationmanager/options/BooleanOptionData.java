/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.ConfigParserError;
import iniconfigurationmanager.parsing.ConfigParserException;
import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;
import sun.security.provider.PolicyParser.ParsingException;


/**
 *
 * @author KlonK <jurko@bdi.sk>
 */
public class BooleanOptionData extends OptionData {

    boolean parsedValue;

    public Class getValueClass() {
       return Boolean.class;
    }

    public Object parseValue(RawValue value) {
        
        if (    value.getValue().equalsIgnoreCase( ZERO ) ||
                value.getValue().equalsIgnoreCase( SHORT_FALSE ) ||
                value.getValue().equalsIgnoreCase( SHORT_NO ) ||
                value.getValue().equalsIgnoreCase( FALSE ) ||
                value.getValue().equalsIgnoreCase( OFF ) ||
                value.getValue().equalsIgnoreCase( NO ) ||
                value.getValue().equalsIgnoreCase( DISABLED ) ) {
         return false;
        } else {
        if (   value.getValue().equalsIgnoreCase( ONE ) ||
                value.getValue().equalsIgnoreCase( SHORT_TRUE ) ||
                value.getValue().equalsIgnoreCase( SHORT_YES ) ||
                value.getValue().equalsIgnoreCase( TRUE ) ||
                value.getValue().equalsIgnoreCase( ON ) ||
                value.getValue().equalsIgnoreCase( YES ) ||
                value.getValue().equalsIgnoreCase( ENABLED ) ) 
         return true;
        }

       throw new ConfigParserException(
                            ConfigParserError.TYPE_PARSING_EXCEPTION,null );

    }

      


    

    public String valueToString(Object value) {
        if ( parsedValue ) {
            return FALSE;
        }
        else
        {
            return TRUE;
        }
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
