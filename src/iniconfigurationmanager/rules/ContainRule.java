/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.validators.ValidationResult;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class ContainRule implements  ValidationRule {

    String subString;
    Object subObject;
    public ContainRule( String subString ) {
        this.subString = subString;
    }

    public ContainRule( Object subOject ) {
        this.subObject = subOject;
    }

    public boolean isAplicableOn(ConfigItemFormatDefinition format) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> ValidationResult validate(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult validationResult( String value ) {
        ValidationResult result = new ValidationResult();
        if (value.contains( subString ) ) {
            result.addResult( true );
        } else {
            result.addResult( false );
            result.addErrorMsg("Substring missing");
        }

        return result;
    }

    public<T> ValidationResult validate( List<T> value) {
        ValidationResult result = new ValidationResult();
        if (value.contains( subObject ) ) {
            result.addResult( true );
        } else {
            result.addResult( false );
            result.addErrorMsg("Item missing");
        }

        return result;
    }
}
