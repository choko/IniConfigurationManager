/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

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

/**
 *
 * @author KlonK
 */
public class MaxValueRule implements ValidationRule {

    Object maxValue;

    public MaxValueRule( int minValue ) {
        this.maxValue = minValue;
    }

    public MaxValueRule( float minValue ) {
        this.maxValue = minValue;
    }

    public MaxValueRule( long minValue ) {
        this.maxValue = minValue;
    }

    MaxValueRule(Object min) {
        this.maxValue = min;
    }

    public ValidationResult validate( SignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Integer> optionIntValue = option.getValues( new Integer(0) );
        for (Integer integer : optionIntValue) {
            if ( integer > (Integer) this.maxValue ) {
            } else {
                result.addErrorMsg( ValidationResult.HIGH_VALUE );
                }
            }
        return result;
    }

    public ValidationResult validate( UnsignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Long> optionIntValue = option.getValues( new Long(0) );
        for (Long longValue : optionIntValue) {
            if ( longValue > (Long) this.maxValue ) {
            } else {
                result.addErrorMsg( ValidationResult.HIGH_VALUE );
                }
            }
        return result;
    }

    public ValidationResult validate ( FloatOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Float> optionFloatValue = option.getValues( new Float(0) );
        for (Float floatValue : optionFloatValue) {
            if ( floatValue > (Float) this.maxValue ) {
            } else {
                result.addErrorMsg( ValidationResult.HIGH_VALUE );
                }
            }
        return result;
    }

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

    public ValidationResult validate(OptionData option) {
        ValidationResult result = new ValidationResult();
        result.addErrorMsg( ValidationResult.INVALID_RULE_APPLICATED );
        return result;
    }

}
