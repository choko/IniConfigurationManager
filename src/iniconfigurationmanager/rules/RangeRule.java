/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.SignedConfigItem;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.items.SignedConfigItem;
import iniconfigurationmanager.items.FloatConfigItem;
import iniconfigurationmanager.items.UnsignedConfigItem;
import iniconfigurationmanager.items.BooleanConfigItem;
import iniconfigurationmanager.items.StringConfigItem;
import java.util.List;
import iniconfigurationmanager.validators.ValidationResult;
import javax.naming.spi.DirStateFactory.Result;

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

   public boolean isAplicableOn(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAplicableOn(SignedConfigItem item) {
        return true;
    }

    public boolean isAplicableOn(FloatConfigItem item) {
        return true;
    }

    public boolean isAplicableOn(BooleanConfigItem item) {
        return false;
    }

    public boolean isAplicableOn(StringConfigItem item) {
        return false;
    }


    public ValidationResult validate(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult validate(SignedConfigItem item) {
        ValidationResult result = new ValidationResult();

    }




}
