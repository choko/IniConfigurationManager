
package iniconfigurationmanager.schema;

import iniconfigurationmanager.validators.ValidationResult;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public interface StructureVisitor {


    public void visit(OptionData option);

    
    public void visit(OptionSchema option);

    
    public void visit(SectionData section);


    public void visit(SectionSchema section);

    
    public void visit(ConfigurationData data);

    
    public ValidationResult getResult();


}
