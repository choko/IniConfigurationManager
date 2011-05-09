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

/**
 * The <code>MaxValueRule</code> provides rule that limites
 * bottom value of numeric OptionData
 * <p>
 * This rule is applicable on numeric Optiaon data such as
 * :SignedOptionData,FloatOptionData and FloatOptiaonData *
 */
public class MinValueRule
        implements ValidationRule {

    private static final String LOW_VALUE =
            "Option %s value is lower than rule allows.";

    private Comparable minValue;


    MinValueRule( Comparable min ) {
        this.minValue = min;
    }


    /**
     * This<Code>validate</code> with @param <code>SignedOptionData</code>
     * validate if value in @param is larger that value adden in constructor
     * .If not write error msg in result
     */
    public ValidationResult validate( SignedOptionData option ) {
        return _validate( option );
    }


    /**
     * This<Code>validate</code> with @param <code>UnsignedOptionData</code>
     * validate if value in @param is larger that value adden in constructor
     * .If not write error msg in result
     */
    public ValidationResult validate( UnsignedOptionData option ) {
        return _validate( option );
    }


    /**
     * This<Code>validate</code> with @param <code>FloatOptionData</code>
     * validate if value in @param is larger that value adden in constructor
     * .If not write error msg in result
     */
    public ValidationResult validate( FloatOptionData option ) {
        return _validate( option );
    }


    private ValidationResult _validate( OptionData option ) {
        ValidationResult result = new ValidationResult();

        for ( Object value : option.getValues() ) {
            if( ((Comparable) value).compareTo( minValue ) <= 0 ) {
                result.addErrorMessage( String.format( LOW_VALUE,
                        option.getCanonicalName() ) );
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
        result.addErrorMessage( ValidationResult.INVALID_RULE_APPLICATED );
        return result;
    }
}
