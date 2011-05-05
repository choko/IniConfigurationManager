/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.items;

import iniconfigurationmanager.parsing.RawValue;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringConfigItem implements ConfigItemFormatDefinition {

    public Class getValueClass() {
        return String.class;
    }

    public Object parseValue(RawValue value) {
        return value.getValue();
    }

    public String valueToString(Object value) {
        return (String) value;
    }


}
