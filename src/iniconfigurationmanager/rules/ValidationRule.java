/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public interface ValidationRule {

    public boolean isAplicableOn( ConfigItemData item );
    
    public ValidationResult validate( ConfigItemData item );



}
