/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.items.SignedOptionData;
import iniconfigurationmanager.items.FloatOptionData;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class RangeRule implements ValidationRule {

    private int min;

    private int max;

    public RangeRule( int min,int max ){
        this.min = min;
        this.max = max;
    }

    public boolean isAplicableOn(SignedOptionData item) {
        return true;
    }

    public boolean isAplicableOn(FloatOptionData item) {
        return true;
    }

     public boolean isAplicableOn(ConfigItemFormatDefinition item) {
        return false;
    }


    public ValidationResult validate(int value) {
        ValidationResult result = new ValidationResult();
        if ( ( this.min > value ) || ( this.max < value ) ) {
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

    public <T> ValidationResult validate(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
