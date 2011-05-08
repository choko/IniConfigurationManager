package iniconfigurationmanager.rules;

/**
 * The <code>RangeRule</code> provides rule that limited
 * bottom and top value of numeric OptionData
 * <p>
 * This rule is applicable on numeric Optiaon data such as
 * :SignedOptionData,FloatOptionData and FloatOptiaonData *
 */
import iniconfigurationmanager.options.FloatOptionSchema;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;

public class RangeRule
        implements ValidationRule {
    /**
     * <code>Object</code> max and min hold values in whitch range must be
     * validating value to pass validating.
     */
    private Object max;

    private Object min;

     /**
     *<code>RangeRule</code> have diferent constructor to distinguish
     *type of value
     */
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

     /**
     * This<Code>validate</code> with @param <code>option</code>
     * validate if value in @param is in range that value adden in constructor
      * specifi.Use MaxValueRule and MinValueFor it.
       .If not write error msg in result
     */
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
