
package iniconfigurationmanager.validators;

import iniconfigurationmanager.rules.ValidationRule;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class RulesValidatorVisitor implements ValidatorVisitor {

     private Map< String,OptionSchema > schemaOptions;

     private ValidationResult result;

    public void visit( OptionData option ) {
      OptionSchema schema = schemaOptions.get(option.getCanonicalName());
      List<ValidationRule> optionRules = schema.getValidationRules();
       for (ValidationRule validationRule : optionRules) {
           result.mergeResults( validationRule.validate( option ) );
        }
    }

    public void visit(OptionSchema option) {
        schemaOptions.put(option.getCanonicalName(), option);
    }

    public void visit(SectionData section) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(SectionSchema section) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationResult getResult() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(ConfigurationData data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
