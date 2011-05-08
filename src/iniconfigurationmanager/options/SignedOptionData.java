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

   private String rawStringvalue;

    public Class getValueClass() {
        return Long.class;
    }

    public Object parseValue(RawValue value) {
        try {
            return Long.decode( value.getValue() );
        } catch (Exception e) {
            throw new ClassCastException();
        }
         
      
    }

    public String valueToString(Object value) {
      Long longValue = (Long) value;
      if ( isHexFormat( rawStringvalue ) ) {
          return Long.toOctalString( longValue );
      }

      if ( isOCtaFormat( rawStringvalue ) ) {
          return Long.toOctalString( longValue );
      }

      if ( isBinaryFormat( rawStringvalue ) ) {
          return Long.toBinaryString( longValue );
      }

      return longValue.toString();
    }

    private static String HEXPREFIX = "0x";
    private static String OCTAPREFIX = "0b";
    private static String BINARYPREFIX = "0";

    private boolean  isHexFormat(String string) {
       return  rawStringvalue.startsWith(HEXPREFIX);
    }

     private boolean  isOCtaFormat(String string) {
       return  rawStringvalue.startsWith(OCTAPREFIX);
    }

      private boolean  isBinaryFormat(String string) {
       return  rawStringvalue.startsWith(BINARYPREFIX);
    }


}
