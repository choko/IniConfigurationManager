package iniconfigurationmanager.rules;

/**
 * The <code>MaxValueRule</code> provides rule that limited
 * top value of numeric OptionData
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


public class MaxValueRule
        implements ValidationRule {

     /**
     * <code>int</code> maxValue maximum value whitch can have ruled value to
     * pass
     */
    Object maxValue;

     /**
     *<code>MaxValueRule</code> have diferent constructor to distinguish
     *type of value
     */
    public MaxValueRule( int minValue ) {
        this.maxValue = minValue;
    }


    public MaxValueRule( float minValue ) {
        this.maxValue = minValue;
    }


    public MaxValueRule( long minValue ) {
        this.maxValue = minValue;
    }


    MaxValueRule( Object min ) {
        this.maxValue = min;
    }


     /**
     * This<Code>validate</code> with @param <code>SignedOptionData</code>
     * validate if value in @param is bellow value adden in constructor
     * .If not write error msg in result
     */
    public ValidationResult validate( SignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Integer> optionIntValue = option.getValues( new Integer( 0 ) );
        for ( Integer integer : optionIntValue ) {
            if ( integer > (Integer) this.maxValue ) {
            } else {
                result.addErrorMsg( ValidationResult.HIGH_VALUE );
            }
        }
        return result;
    }

    /**
     * This<Code>validate</code> with @param <code>UnsignedOptionData</code>
     * validate if value in @param is bellow value adden in constructor
     * .If not write error msg in result
     */
    public ValidationResult validate( UnsignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Long> optionIntValue = option.getValues( new Long( 0 ) );
        for ( Long longValue : optionIntValue ) {
            if ( longValue > (Long) this.maxValue ) {
            } else {
                result.addErrorMsg( HIGH_VALUE );
            }
        }
        return result;
    }

    /**
     * This<Code>validate</code> with @param <code>FloatOptionData</code>
     * validate if value in @param is bellow value adden in constructor
     * .If not write error msg in result
     */
    public ValidationResult validate( FloatOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Float> optionFloatValue = option.getValues( new Float( 0 ) );
        for ( Float floatValue : optionFloatValue ) {
            if ( floatValue > (Float) this.maxValue ) {
            } else {
                result.addErrorMsg( HIGH_VALUE );
            }
        }
        return result;
    }

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
     * Constant hold error message that added on result if rule fail
     */
    private static String HIGH_VALUE =
            "Option value is higher that rule";
}
