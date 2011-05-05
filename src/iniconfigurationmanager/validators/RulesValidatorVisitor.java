
package iniconfigurationmanager.validators;

import iniconfigurationmanager.rules.ValidationRule;
import iniconfigurationmanager.schema.ConfigData;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigSectionSchema;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class RulesValidatorVisitor implements ValidatorVisitor {

     private Map< String,ConfigItemSchema > schemaItems;

     private ValidationResult result;

    public void visit( ConfigItemData item ) {
      ConfigItemSchema schema = schemaItems.get(item.getCanonicalName());
      List<ValidationRule> itemRules = schema.getValidationRules();
       for (ValidationRule validationRule : itemRules) {
           result.mergeResults( validationRule.validate( item ) );
        }
    }

    public void visit(ConfigItemSchema item) {
        schemaItems.put(item.getCanonicalName(), item);
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
