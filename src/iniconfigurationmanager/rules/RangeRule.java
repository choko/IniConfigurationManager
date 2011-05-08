/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iniconfigurationmanager.rules;

import iniconfigurationmanager.options.FloatOptionSchema;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class RangeRule
        implements ValidationRule {

    private Object max;

    private Object min;


    public RangeRule( int min, int max ) {
        this.min = min;
        this.max = max;
        if ( min > max ) {
            throw new NumberFormatException();
        }

    }


    public RangeRule( float min, float max ) {
        this.min = min;
        this.max = max;
        if ( min > max ) {
            throw new NumberFormatException();
        }
    }


    public RangeRule( long min, long max ) {
        this.min = min;
        this.max = max;
        if ( min > max ) {
            throw new NumberFormatException();
        }
    }


    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();

        MinValueRule minRule = new MinValueRule( this.min );
        result.mergeResults( minRule.validate( option ) );

        MaxValueRule maxRule = new MaxValueRule( this.max );
        result.mergeResults( maxRule.validate( option ) );

        return result;
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


    public boolean isAplicableOn( OptionSchema option ) {
        return false;
    }
}
