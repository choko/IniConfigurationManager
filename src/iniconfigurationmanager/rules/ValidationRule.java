package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;

/**
 * The <code>ValidationRule</code> is interface for Validation rule.
 * Its requed two methods.
 * <p>
 * <code>isAplicableOn</code> return bool of what whitch OptionSchema is posible
 * applicate validation rule.
 * <p>
 * <code>validate</code> provides validating of data includet in Option data.
 * Depends on @param what policy of validating is used
 *
 */
public interface ValidationRule {

    public boolean isAplicableOn( OptionSchema option );


    public ValidationResult validate( OptionData option );
}
