/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.options.FloatOptionSchema;
import iniconfigurationmanager.options.SignedOptionData;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.Collection;

/**
 *
 * @author KlonK
 */
public class RangeRule implements ValidationRule {

    private Object max;

    private Object min;

    public <T extends Comparable<T>> RangeRule (T min,T max){
        this.min = min;
        this.max = max;
    }

    public ValidationResult validate(int value) {
        ValidationResult result = new ValidationResult();
        


    public ValidationResult validate(OptionData option) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ValidationResult validate (SignedOptionData option ) {
        ValidationResult result = new ValidationResult();

        if ( ( this.min > option.getValue(int) ) || ( this.max < value ) ) {
            result.addResult(true);
        }
        else {
           result.addResult(false);
           if ( this.min > value ) {
               result.addErrorMsg("Value too low");
           }
           if ( this.max < value ) {
               result.addErrorMsg("Value too large");
           }
        }
        return result;

    }

    public boolean isAplicableOn( OptionSchema option ) {
       return false;
    }

    public boolean isAplicableOn( SignedOptionSchema option ) {
       return true;
    }

    public boolean isAplicableOn( UnsignedOptionSchema option ) {
        return true;
    }

    public boolean isAplicableOn( FloatOptionSchema option ) {
        return true;
    }

}
