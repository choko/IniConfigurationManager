
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigData;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;
import iniconfigurationmanager.schema.ConfigSchema;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigSectionSchema;
import java.util.HashSet;
import java.util.Map;
import iniconfigurationmanager.rules.ValidationRule;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class RelaxValidatorVisitor implements ValidatorVisitor {

    private ValidationResult result;

    private Map< String,ConfigItemSchema > schemaItems;

    private HashSet< String > schemaSection;

    private HashSet< String > reqiedSection;



    private ConfigSchema configSchema;

    //private HashSet<String> enteredConfigItemSchema;

    public void visit( ConfigItemData item ) {
       ConfigItemSchema schema = schemaItems.get(item.getCanonicalName());
       List<ValidationRule> itemRules = schema.getValidationRules();
        for (ValidationRule validationRule : itemRules) {
            result.mergeResults( validationRule.validate( item ) );
        }
    }

    public void visit( ConfigItemSchema item ) {
        schemaItems.put(item.getCanonicalName(), item);
    }

    public void visit( ConfigSectionData section ) {
     //podla mna nic, nekontroluje sekcie
    }

    public void visit( ConfigSectionSchema section ) {
       schemaSection.add( section.getName() );
       if ( section.isRequired() ) {
           reqiedSection.add( section.getName() );
       }
    }

    public ValidationResult getResult() {
        return result;
    }

    public void visit( ConfigData data ) {

    }

}
