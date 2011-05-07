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
        return int.class;
    }

    public Object parseValue(RawValue value) {
       return Integer.decode( value.getValue() );
    }

    public String valueToString(Object value) {
        return value.toString();
    }

}
