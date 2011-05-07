
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import iniconfigurationmanager.schema.ConfigurationData;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public interface ValidatorVisitor {


    public void visit(OptionData option);

    
    public void visit(OptionSchema option);

    
    public void visit(SectionData section);


    public void visit(SectionSchema section);

    public void visit(ConfigurationData data);

    
    public ValidationResult getResult();


}
