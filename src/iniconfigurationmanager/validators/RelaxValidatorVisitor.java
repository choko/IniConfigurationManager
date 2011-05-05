
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigData;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigSectionSchema;

/**
 *
 * @author KlonK
 */
public class RelaxValidatorVisitor implements ValidatorVisitor {

    public void visit(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(ConfigItemSchema item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(ConfigSectionData section) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(ConfigSectionSchema section) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult getResult() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(ConfigData data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
