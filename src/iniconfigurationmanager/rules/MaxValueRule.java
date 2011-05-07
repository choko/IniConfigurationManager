/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.options.FloatOptionSchema;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class MaxValueRule implements ValidationRule {

    int minValue;

    public MaxValueRule( int maxValue ) {
        this.minValue = maxValue;
    }

    public ValidationResult validate(int value) {
        ValidationResult result = new ValidationResult();
        if ( ( this.minValue > value ) ) {
            result.addResult(true);
        }
        else {
           result.addResult(false);
          result.addErrorMsg("Value too low");
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

    public ValidationResult validate(OptionData option) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
