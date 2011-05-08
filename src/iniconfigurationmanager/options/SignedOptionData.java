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
 * @author KlonK <jurko@bdi.sk>
 */
public class SignedOptionData  extends OptionData {

   private String rawStringvalue;

    public Class getValueClass() {
        return Long.class;
    }

    public Object parseValue(RawValue value) {
        long longValue;
        rawStringvalue = value.getValue();
        try {
            longValue = Long.decode( value.getValue() );
            return  longValue;
        } catch (Exception e) {
            throw new ConfigParserException(
                    ConfigParserError.INPUT_ERROR, null );
        }
         
      
    }

    public String valueToString(Object value) {
      Long longValue = (Long) value;
      if ( rawStringvalue.startsWith("0x") ) {
          return  Long.toHexString( longValue );
      }

      if ( rawStringvalue.startsWith("0b") ) {
          return Long.toOctalString( longValue );
      }

      if ( rawStringvalue.startsWith("0") ) {
          return Long.toBinaryString( longValue );
      }

      return longValue.toString();
    }

}
