package iniconfigurationmanager.rules;

import iniconfigurationmanager.options.FloatOptionSchema;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.utils.ValidatorUtils;
import iniconfigurationmanager.validators.ValidationResult;


/**
 * The <code>RangeRule</code> provides rule that limited
 * bottom and top value of numeric OptionData
 * <p>
 * This rule is applicable on numeric Optiaon data such as
 * :SignedOptionData,FloatOptionData and FloatOptiaonData *
 */
public class RangeRule
        implements ValidationRule {

    private Comparable max;

    private Comparable min;

    
    public RangeRule( Comparable min, Comparable max ) {
        this.min = min;
        this.max = max;
        if ( min.compareTo( max ) > 0 ) {
            throw new NumberFormatException();
        }
    }

    /**
     * This<Code>validate</code> with @param <code>option</code>
     * validate if value in @param is in range that value adden in constructor
     * specifi.Use MaxValueRule and MinValueFor it.
     * If not write error msg in result
     */
    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();

        MinValueRule minRule = new MinValueRule( this.min );
        result.mergeResults( ValidatorUtils.validate( minRule, option ) );

        MaxValueRule maxRule = new MaxValueRule( this.max );
        result.mergeResults( ValidatorUtils.validate( maxRule, option ) );

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
