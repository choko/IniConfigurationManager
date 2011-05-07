/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;

/**
 *
 * @author KlonK <jurko@bdi.sk>
 */
public class SignedOptionData  extends OptionData {

    public Class getValueClass() {
        return Long.class;
    }

    public Object parseValue(RawValue value) {
         return Long.decode( value.getValue() );
      
    }

    public String valueToString(Object value) {
        return value.toString();
    }

}
