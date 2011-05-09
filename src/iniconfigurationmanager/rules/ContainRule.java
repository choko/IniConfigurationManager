package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;


/**
 * The <code>ContainRule</code> provides rule OptionData contain
 * element that is specify in Rule constructor.
 * <p>
 * This rule is applicable on evry Data that extends OptionSchema
 */
public class ContainRule
        implements ValidationRule {


    private static final String NOT_CONTAIN_VALUE =
            "Option %s doesn't contain required value %s.";

    private Object requiredValue;


    public ContainRule( Object contain ) {
        this.requiredValue = contain;
    }

    /**
     * <Code>isAplicableOn</code> return always true, when
     * as @param came Class that extends OptionScheme Class.
     * Its mean that <code>ContainRule</code> is applicable on
     * every posible OptionSchema.
     */
    public boolean isAplicableOn( OptionSchema format ) {
        return true;
    }

    /**
     * <Code>validate</code> check if object contain in
     * Option data. If not add Error message to the result.
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Object> options = option.getValues( new Object() );

        if ( !options.contains( requiredValue ) ) {
            result.addErrorMessage( String.format( NOT_CONTAIN_VALUE,
                    option.getCanonicalName(), requiredValue ) );
        }

        return result;
    }

}
