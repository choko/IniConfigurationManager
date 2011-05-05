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
public interface ValidationRule {

    public boolean isAplicableOn( ConfigItemFormatDefinition item );
    
    public ValidationResult validate( ConfigItemData item );



}
