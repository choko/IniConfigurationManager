package iniconfigurationmanager.rules;

/**
 * The <code>CountRule</code> provides rule that chack if
 * count of number of element in Option data is specfi number
 * <p>
 * This rule is applicable on evry Data that extends OptionSchema
 */
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

public class CountRule
        implements ValidationRule {

     /**
     * <code>int</code> stores how many elements must be in OptionData
      * to pass the rule.
     */
    int count;


    public CountRule( int count ) {
        this.count = count;
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
     * <Code>validate</code> check if count of elements in
     * Option data its a same as requed count.
     * If not add Error message to the result.
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Object> options = option.getValues( new Object() );

        if ( options.size() != count ) {
            result.addErrorMsg( INVALID_COUNT );
        }

        return result;
    }

    /**
     * Constant hold error message that added on result if rule fail
     */
    private final String INVALID_COUNT =
            "Option value doesnt have valid count";
}
