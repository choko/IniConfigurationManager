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
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class FloatOptionData extends  OptionData {

    public Class getValueClass() {
       return Float.class;
    }

    public Object parseValue(RawValue value) {

        float floatValue;
        try {
            floatValue = Float.parseFloat( value.getValue() );

        } catch (Exception e) {
            throw new ClassCastException();
        }
        return  Float.parseFloat( value.getValue() );
    }

    public String valueToString(Object value) {
        return value.toString();
    }


}
