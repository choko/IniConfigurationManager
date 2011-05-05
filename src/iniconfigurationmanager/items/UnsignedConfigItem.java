/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.items;

import iniconfigurationmanager.parsing.RawValue;

/**
 *
 * @author KlonK <jurko@bdi.sk>
 */
public class UnsignedConfigItem implements ConfigItemFormatDefinition {

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
