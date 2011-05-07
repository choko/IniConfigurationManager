/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class MaxValueRule implements ValidationRule {

    int maxValue;

    public MaxValueRule( int maxValue ) {
        this.maxValue = maxValue;
    }

    public boolean isAplicableOn(ConfigItemFormatDefinition format) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> ValidationResult validate(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult validate(int value) {
        ValidationResult result = new ValidationResult();
        if ( ( this.maxValue < value ) ) {
            result.addResult(true);
        }
        else {
           result.addResult(false);        
          result.addErrorMsg("Value too large");
           }
        
        return result;
    }

}
