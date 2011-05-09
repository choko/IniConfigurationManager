package iniconfigurationmanager.rules;

/**
 * The <code>ContainRule</code> provides rule OptionData contain
 * only! one element.
 * <p>
 * This rule is applicable on evry Data that extends OptionSchema
 */
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.utils.ValidatorUtils;
import iniconfigurationmanager.validators.ValidationResult;

public class OneValueRule
        implements ValidationRule {
    /**
     * <Code>isAplicableOn</code> return always true, when
     * as @param came Class that extends OptionScheme Class.
     * Its mean that <code>ContainRule</code> is applicable on
     * every posible OptionSchema.
     */
    public boolean isAplicableOn( OptionSchema option ) {
        return true;
    }


    /**
     * <Code>validate</code> check if count of elements in
     * Option data is one!.Method simply use CountRule whit @param 1
     * If not add Error message to the result.
     */
    public ValidationResult validate( OptionData option ) {
        CountRule countRule = new CountRule( 1 );
        return ValidatorUtils.validate( countRule, option );
    }
}
