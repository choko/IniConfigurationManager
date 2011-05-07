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
public class UnsignedOptionData extends OptionData {

    public Class getValueClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object parseValue(RawValue value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String valueToString(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
