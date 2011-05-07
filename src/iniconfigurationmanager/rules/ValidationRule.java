
package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public interface ValidationRule {

    public boolean isAplicableOn( OptionSchema option );
    
    public ValidationResult validate( OptionData option );

}
