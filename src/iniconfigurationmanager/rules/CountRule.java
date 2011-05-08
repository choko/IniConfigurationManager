/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class CountRule implements ValidationRule {

    int count ;

    public CountRule( int count ) {
        this.count = count;
    }

    public boolean isAplicableOn(OptionSchema format) {
        return true;
    }

    public ValidationResult validate(OptionData option) {
        ValidationResult result = new ValidationResult();
        List<Object> options = option.getValues(new Object());

        if ( options.size() != count ) {
            result.addErrorMsg( INVALID_COUNT );
        }

        return result;
    }

    private final String INVALID_COUNT = 
            "Option value doesnt have valid count";
     
}
