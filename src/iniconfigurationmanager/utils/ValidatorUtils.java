package iniconfigurationmanager.utils;

import iniconfigurationmanager.rules.ValidationRule;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.lang.reflect.Method;

/**
 * ValidatorUtils unites method for providing polymorphism in validation rules.
 */
public class ValidatorUtils {

    private static final String IS_APLICABLE_ON_METHOD_NAME = "isAplicableOn";

    private static final String VALIDATE_METHOD_NAME = "validate";


    /**
     * Calls the most specific isAplicableOn method for the option from the rule
     * class.
     *
     * @param rule
     * @param option
     * @return
     */
    public static boolean isRuleAplicableOn( ValidationRule rule,
            OptionSchema option) {
        try {
            Method method = rule.getClass().getMethod(
                    IS_APLICABLE_ON_METHOD_NAME, option.getClass() );

            return (Boolean) method.invoke( rule, option );
        } catch ( Exception ex ) {
            return rule.isAplicableOn( option );
        }
    }


    /**
     * Calls the most specific validate method for the option from the rule
     * class.
     *
     * @param rule
     * @param option
     * @return
     */
    public static ValidationResult validate( ValidationRule rule,
            OptionData option ) {
        try {
            Method method = rule.getClass().getMethod(
                    VALIDATE_METHOD_NAME, option.getClass() );

            return (ValidationResult) method.invoke( rule, option );
        } catch( Exception ex ) {
            return rule.validate( option );
        }
    }
    
}
