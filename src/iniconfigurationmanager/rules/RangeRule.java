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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult validate (SignedOptionData option ) {
        ValidationResult result = new ValidationResult();
        Integer optionIntValue = option.getValues(new Integer(0)).get(0);
        if ( optionIntValue > (Integer) this.max ||
             optionIntValue < (Integer) this.min   ) {
            result.addResult( true );
        } else {
            result.addResult( false );
            if ( optionIntValue > (Integer) this.max ) {
                result.addErrorMsg( "Value too large");
            }
            if ( optionIntValue < (Integer) this.min ) {
                result.addErrorMsg( "Value too low");
            }


        }
        return result;

    }

    public ValidationResult validationResult ( FloatOptionData option ) {
        ValidationResult result = new ValidationResult();
        Float optionFloatValue = option.getValues(new Float(0)).get(0);
        if ( optionFloatValue > ( Float ) this.max ||
             optionFloatValue < ( Float ) this.min   ) {
            result.addResult( true );
        } else {
            result.addResult( false );
            if ( optionFloatValue > ( Float ) this.max ) {
                result.addErrorMsg( "Value too large");
            }
            if ( optionFloatValue < ( Float ) this.min ) {
                result.addErrorMsg( "Value too low");
            }
        }
        return result;
    }
    
    public ValidationResult validationResult ( UnsignedOptionData option ) {
             ValidationResult result = new ValidationResult();
        Long optionLongValue = option.getValues(new Long(0)).get(0);
        if ( optionLongValue > ( Long ) this.max ||
             optionLongValue < ( Long ) this.min   ) {
            result.addResult( true );
        } else {
            result.addResult( false );
            if ( optionLongValue > ( Long ) this.max ) {
                result.addErrorMsg( "Value too large");
            }
            if ( optionLongValue < ( Long ) this.min ) {
                result.addErrorMsg( "Value too low");
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
