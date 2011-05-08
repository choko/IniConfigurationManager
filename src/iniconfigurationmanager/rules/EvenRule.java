package iniconfigurationmanager.rules;

/**
 * The <code>EvenRule</code> provides rule if numeric data
 * in OptionData is Even
 * <p>
 * This rule is applicable on numeric Optiaon data such as
 * :SignedOptionData,FloatOptionData and FloatOptiaonData *
 */
import iniconfigurationmanager.options.FloatOptionData;
import iniconfigurationmanager.options.FloatOptionSchema;
import iniconfigurationmanager.options.SignedOptionData;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionData;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

public class EvenRule
        implements ValidationRule {

     /**
     * This<Code>validate</code> whit @param <code>OptionData</code>
     * provides that if the Class that is extend of Option data and doesnt
     * have method validate specified for spesify class, return error.
     * That error tells the rule is applicated on wrong Option
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        result.addErrorMsg( ValidationResult.INVALID_RULE_APPLICATED );
        return result;
    }

    /**
     * This<Code>validate</code> with @param <code>SignedOptionData</code>
     * validate if value in @param is Even.If not write error msg in result
     */
    public ValidationResult validate( SignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Integer> optionIntValue = option.getValues( new Integer( 0 ) );

        for ( Integer integer : optionIntValue ) {
            if ( (integer % 2) == 0 ) {
                result.addErrorMsg( NOT_EVEN_VALUE );
            }
        }

        return result;
    }

    /**
     * This<Code>validate</code> with @param <code>FloatOptionData</code>
     * validate if value in @param is Even.If not write error msg in result
     */
    public ValidationResult validationResult( FloatOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Float> optionFloatValue = option.getValues( new Float( 0 ) );

        for ( Float floatValue : optionFloatValue ) {
            if ( (floatValue % 2) == 0 ) {
                result.addErrorMsg( NOT_EVEN_VALUE );
            }
        }

        return result;
    }

    /**
     * This<Code>validate</code> with @param <code>UnsignedOptionData</code>
     * validate if value in @param is Even.If not write error msg in result
     */
    public ValidationResult validationResult( UnsignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Long> optionLongValue = option.getValues( new Long( 0 ) );

        for ( Long longValue : optionLongValue ) {
            if ( (longValue % 2) == 0 ) {
                result.addErrorMsg( NOT_EVEN_VALUE );
            }
        }

        return result;
    }
    /**
     * Constant hold error message that added on result if rule fail
     */
    private final String NOT_EVEN_VALUE =
            "Option value is not a even number";

    /**
     * Methods thts specify on whitch OptionSchema can be rule aplicated
     */
    public boolean isAplicableOn( OptionSchema option ) {
        return false;
    }


    public boolean isAplicableOn( SignedOptionSchema option ) {
        return true;
    }


    public boolean isAplicableOn( UnsignedOptionSchema option ) {
        return true;
    }


    public boolean isAplicableOn( FloatOptionSchema option ) {
        return true;
    }
}
