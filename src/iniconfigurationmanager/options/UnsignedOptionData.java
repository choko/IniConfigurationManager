/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.options;

import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.schema.OptionData;
import java.math.BigInteger;

/**
 *
 * @author KlonK <jurko@bdi.sk>
 */
public class UnsignedOptionData extends OptionData {

    public Class getValueClass() {
        return BigInteger.class;
    }

    public Object parseValue(RawValue value) {
        UInt64Test uInt64value = new UInt64Test( value.getValue() );
        return uInt64value.toBigInteger();

    }

    public String valueToString(Object value) {
      return  value.toString();
    }


private class UInt64Test {

    private BigInteger uint64;

    public UInt64Test( String string ) {
        BigInteger rawUint64 = new BigInteger(string);
        if ( rawUint64.equals(rawUint64.min( BigInteger.TEN.not() ) )  ) {
            uint64 = rawUint64;
        } else {
            throw new NumberFormatException();
        }

    }

    public BigInteger toBigInteger() {
      return  uint64;
    }

    private final BigInteger B64 = BigInteger.ZERO.setBit(64);
    public final BigInteger minValue = BigInteger.ZERO;
    public final BigInteger maxValue = BigInteger.ZERO.setBit(64).flipBit(64);

    public String toUnsignedString(long num) {
        if (num >= 0)
            return String.valueOf(num);
        return BigInteger.valueOf(num).add(B64).toString();
    }
}
}
