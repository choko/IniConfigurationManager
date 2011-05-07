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
import java.util.Collection;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class RangeRule implements ValidationRule {

    private Object max;

    private Object min;


    public  RangeRule ( int min,int max ){
        this.min = min;
        this.max = max;

    }

    public RangeRule ( float  min,float max ) {

    }

    public RangeRule ( long min,long max ) {

    }
     


    
    public ValidationResult validate(OptionData option) {
        ValidationResult result = new ValidationResult();
        result.addErrorMsg("Rule applicated on unsuporter Data");
        return result;
    }

    public ValidationResult validate (SignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Integer> optionIntValue = option.getValues(new Integer(0));
        for (Integer integer : optionIntValue) {
            if ( integer > (Integer) this.max ||
                 integer < (Integer) this.min   ) {
            } else {
                if ( integer > (Integer) this.max ) {
                    result.addErrorMsg( "Value too large");
                }
                if ( integer < (Integer) this.min ) {
                    result.addErrorMsg( "Value too low");
                }
            }
        }
        return result;
    }

    public ValidationResult validationResult ( FloatOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Float> optionFloatValue = option.getValues(new Float(0));
        for (Float floatValue : optionFloatValue) {
            if ( floatValue > (Float) this.max ||
                 floatValue < (Float) this.min   ) {
            } else {
                if ( floatValue > (Float) this.max ) {
                    result.addErrorMsg( "Value too large");
                }
                if ( floatValue < (Float) this.min ) {
                    result.addErrorMsg( "Value too low");
                }
            }
        }
        return result;
    }
    
    public ValidationResult validationResult ( UnsignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Long> optionLongValue = option.getValues(new Long(0));
        for (Long longValue : optionLongValue) {
            if ( longValue > (Long) this.max ||
                 longValue < (Long) this.min   ) {
            } else {
                if ( longValue > (Long) this.max ) {
                    result.addErrorMsg( "Value too large");
                }
                if ( longValue < (Long) this.min ) {
                    result.addErrorMsg( "Value too low");
                }
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

}
