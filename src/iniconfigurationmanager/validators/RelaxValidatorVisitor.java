
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.StructureVisitor;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.ConfigurationSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import java.util.HashSet;
import java.util.Map;
import iniconfigurationmanager.rules.ValidationRule;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class RelaxValidatorVisitor implements StructureVisitor {

    private ValidationResult result;

    private Map< String,OptionSchema > schemaOptions;

    private HashSet< String > schemaSection;

    private HashSet< String > reqiedSection;



    private ConfigurationSchema configSchema;

    //private HashSet<String> enteredConfigOptionSchema;

    public void visit( OptionData option ) {
       OptionSchema schema = schemaOptions.get(option.getCanonicalName());
       List<ValidationRule> optionRules = schema.getValidationRules();
        for (ValidationRule validationRule : optionRules) {
            result.mergeResults( validationRule.validate( option ) );
        }
    }

    public void visit( OptionSchema option ) {
        schemaOptions.put(option.getCanonicalName(), option);
    }

    public void visit( SectionData section ) {
     //podla mna nic, nekontroluje sekcie
    }

    public void visit( SectionSchema section ) {
       schemaSection.add( section.getName() );
       if ( section.isRequired() ) {
           reqiedSection.add( section.getName() );
       }
    }

    public ValidationResult getResult() {
        return result;
    }

    public void visit( ConfigurationData data ) {

    }

}
