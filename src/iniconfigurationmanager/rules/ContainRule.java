/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class ContainRule
        implements ValidationRule {

    Object contain;


    public ContainRule( Object contain ) {
        this.contain = contain;
    }


    public boolean isAplicableOn( OptionSchema format ) {
        return true;
    }


    public ValidationResult validate( OptionData option ) {
        ValidationResult result = new ValidationResult();
        List<Object> options = option.getValues( new Object() );

        if ( !options.contains( contain ) ) {
            result.addErrorMsg( NOT_CONTAIN_VALUE );
        }

        return result;
    }

    private final String NOT_CONTAIN_VALUE =
            "Option doesnt containt ruled value";
}
