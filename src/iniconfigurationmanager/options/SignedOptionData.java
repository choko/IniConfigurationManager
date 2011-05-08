/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.utils.NumberUtils;

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
        try {
            rawStringvalue = value.getValue();
            return Long.decode( value.getValue() );
        } catch (Exception e) {
            throw new ClassCastException();
        }
         
      
    }

    public String valueToString(Object value) {
      Long longValue = (Long) value;
      NumberUtils utils = new NumberUtils();

      if ( utils.isHexFormat( rawStringvalue ) ) {
          return NumberUtils.HEXPREFIX.concat( Long.toHexString( longValue ) );
      }

      if ( utils.isOCtaFormat( rawStringvalue ) ) {
          return NumberUtils.OCTAPREFIX.concat( Long.toOctalString( longValue ) );
      }

      if ( utils.isBinaryFormat( rawStringvalue ) ) {
          return NumberUtils.BINARYPREFIX.concat( Long.toBinaryString( longValue ) );
      }

      return longValue.toString();
    }

}
