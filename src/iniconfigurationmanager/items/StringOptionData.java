
package iniconfigurationmanager.items;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.ConfigItemData;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringOptionData extends ConfigItemData {

    public StringOptionData() {
        
    }

    @Override
    protected Class getValueClass() {
        return String.class;
    }

    @Override
    protected Object parseValue(RawValue value) {
        return value.getValue();
    }

    @Override
    protected String valueToString(Object value) {
        return (String) value;
    }


}
