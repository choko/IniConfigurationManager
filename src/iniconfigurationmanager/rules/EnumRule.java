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
public class EnumRule implements ValidationRule {

    private List<Object> enumValue;

    public EnumRule( List<Object> enumValue) {
        this.enumValue = enumValue;
    }

    public boolean isAplicableOn(OptionSchema option) {
        return true;
    }

    public ValidationResult validate(OptionData option) {
        ValidationResult result = new ValidationResult();
        List<Object> optionValues = option.getValues();

        if ( !optionValues.containsAll(enumValue) ) {
            result.addErrorMsg( ValidationResult.ENUM_INVALID_VALUE);
        }

        return result;
    }

}
