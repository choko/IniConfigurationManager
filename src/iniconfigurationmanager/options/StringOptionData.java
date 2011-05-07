
package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringOptionData extends OptionData {

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
       String returnString = value.toString();
       returnString.replaceAll(",", "\\,");
       returnString.replaceAll(":", "\\:");
       returnString.replaceAll(";", "\\;");
       
       return returnString;

    }


}
