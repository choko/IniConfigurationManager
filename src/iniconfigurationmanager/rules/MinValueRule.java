/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.items.SignedOptionData;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.items.SignedOptionData;
import iniconfigurationmanager.items.FloatOptionData;
import iniconfigurationmanager.items.UnsignedOptionData;
import iniconfigurationmanager.items.BooleanOptionData;
import iniconfigurationmanager.items.StringConfigItem;
import java.util.List;
import iniconfigurationmanager.validators.ValidationResult;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author KlonK
 */
public class MinValueRule implements ValidationRule{

    public boolean isAplicableOn(SignedOptionData item) {
        return true;
    }

    public boolean isAplicableOn(FloatOptionData item) {
        return true;
    }

    public boolean isAplicableOn(BooleanOptionData item) {
        return false;
    }

    public boolean isAplicableOn(StringConfigItem item) {
        return false;
    }

    public ValidationResult validate(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAplicableOn(ConfigItemFormatDefinition item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
