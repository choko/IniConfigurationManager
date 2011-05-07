
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
        String rawString = value.getValue();
        return removeChar(rawString, '\\');
    }


    private String removeChar(String s, char c) {
        StringBuilder r = new StringBuilder( s.length() );
        r.setLength( s.length() );
        int current = 0;
        for (int i = 0; i < s.length(); i ++) {
            char cur = s.charAt(i);
            if (cur != c) r.setCharAt( current++, cur );
        }
        return r.toString();
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
