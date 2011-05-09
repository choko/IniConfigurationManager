package iniconfigurationmanager.rules;

/**
 * The <code>CountRule</code> provides rule that check if
 * all Object stored in Constructor @param is too in OptionData
 * <p>
 * This rule is applicable on evry Data that extends OptionSchema
 */
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

public class EnumRule
        implements ValidationRule {

     /**
     * <code>List<Object></code> all Object , that must by in
      * ObjectData to pass rule.
     */
    private List<Object> enumValue;


    public EnumRule( List<Object> enumValue ) {
        this.enumValue = enumValue;
    }

    /**
     * <Code>isAplicableOn</code> return always true, when
     * as @param came Class that extends OptionScheme Class.
     * Its mean that <code>EnumRule</code> is applicable on
     * every posible OptionSchema.
     */
    public boolean isAplicableOn( OptionSchema option ) {
        return true;
    }

    /**
     * <Code>validate</code> if all Object specified during constructor
     * is in Optioandata.If not add Error message to the result.
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Object> optionValues = option.getValues();

        for( Object value : optionValues ) {
            if ( ! enumValue.contains( value ) ) {
                result.addErrorMessage( String.format( ENUM_INVALID_VALUE,
                        option.getCanonicalName(), value ) );
            }
        }

        return result;
    }

    /**
     * Constant hold error message that added on result if rule fail
     */
    private final String ENUM_INVALID_VALUE =
            "Option %s contains an unallowed value %s.";
}
