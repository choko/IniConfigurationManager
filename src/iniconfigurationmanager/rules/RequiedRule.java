/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class RequiedRule implements ValidationRule {

    public boolean isAplicableOn(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public ValidationResult validate(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAplicableOn(ConfigItemFormatDefinition item) {
        return false;
    }

    public <T> ValidationResult validate(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
