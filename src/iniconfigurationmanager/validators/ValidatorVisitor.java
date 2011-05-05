
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigSectionSchema;


/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public interface ValidatorVisitor {


    public void visit(ConfigItemData item);

    
    public void visit(ConfigItemSchema item);

    
    public void visit(ConfigSectionData section);


    public void visit(ConfigSectionSchema section);

    
    public ValidationResult getResult();


}
