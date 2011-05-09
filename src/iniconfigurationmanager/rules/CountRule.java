package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

/**
 * The <code>CountRule</code> provides rule that check if
 * count of number of element in Option data is specfi number
 * <p>
 * This rule is applicable on evry Data that extends OptionSchema
 */
public class CountRule
        implements ValidationRule {

    private static final String INVALID_COUNT =
            "Option %s has %d values instead of %d.";

    private int requiredValuesCount;


    public CountRule( int count ) {
        this.requiredValuesCount = count;
    }

    /**
     * <Code>isAplicableOn</code> return always true, when
     * as @param came Class that extends OptionScheme Class.
     * Its mean that <code>CountRule</code> is applicable on
     * every posible OptionSchema.
     * @return 
     * @param format
     */
    public boolean isAplicableOn( OptionSchema format ) {
        return true;
    }

    /**
     * <Code>validate</code> check if count of elements in
     * Option data its a same as requed count.
     * If not add Error message to the result.
     * @param option
     * @return
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Object> options = option.getValues( new Object() );

        if ( options.size() != requiredValuesCount ) {
            result.addErrorMessage( String.format( INVALID_COUNT,
                    option.getCanonicalName(),
                    options.size(),
                    requiredValuesCount ) );
        }

        return result;
    }

}
