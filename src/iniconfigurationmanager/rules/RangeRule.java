/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.items.SignedOptionData;
import iniconfigurationmanager.items.FloatOptionData;
import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author KlonK
 */
public class RangeRule implements ValidationRule {

    private int from;

    private int to;

    public RangeRule( int from,int to ){
        this.from = from;
        this.to = to;
    }

    public boolean isAplicableOn(SignedOptionData item) {
        return true;
    }

    public boolean isAplicableOn(FloatOptionData item) {
        return true;
    }

     public boolean isAplicableOn(ConfigItemFormatDefinition item) {
        return false;
    }


    public ValidationResult validate(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult validate(SignedOptionData item) {
        

    }

}
