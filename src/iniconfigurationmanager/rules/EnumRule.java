package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

/**
 * The <code>CountRule</code> provides rule that check if
 * all Object stored in Constructor @param is too in OptionData
 * <p>
 * This rule is applicable on evry Data that extends OptionSchema
 */
public class EnumRule
        implements ValidationRule {

    private static final String ENUM_INVALID_VALUE =
            "Option %s contains an unallowed value %s.";

    private List<Object> allowedValues;


    public EnumRule( List<Object> allowedValues ) {
        this.allowedValues = allowedValues;
    }

    /**
     * <Code>isAplicableOn</code> return always true, when
     * as @param came Class that extends OptionScheme Class.
     * Its mean that <code>EnumRule</code> is applicable on
     * every posible OptionSchema.
     * @param option 
     * @return
     */
    public boolean isAplicableOn( OptionSchema option ) {
        return true;
    }

    /**
     * <Code>validate</code> if all Object specified during constructor
     * is in Optioandata.If not add Error message to the result.
     * @param option 
     * @return
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Object> optionValues = option.getValues();

        for( Object value : optionValues ) {
            if ( ! allowedValues.contains( value ) ) {
                result.addErrorMessage( String.format( ENUM_INVALID_VALUE,
                        option.getCanonicalName(), value ) );
            }
        }

        return result;
    }

}
