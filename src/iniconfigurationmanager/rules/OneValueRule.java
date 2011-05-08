/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class OneValueRule
        implements ValidationRule {

    public boolean isAplicableOn( OptionSchema option ) {
        return true;
    }


    public ValidationResult validate( OptionData option ) {
        CountRule countRule = new CountRule( 1 );
        return countRule.validate( option );
    }
}
