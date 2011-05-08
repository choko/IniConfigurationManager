package iniconfigurationmanager.validators;

/**
* <code>validateRelax</code> Relax validate given data.
* Relax validation mean ,that only schemas and option that set to requied
* while creating schema is validate.
* <p>
* So if schema or option is reqied and isnt in
* data it makes error and validation doesn pass.
* <p>
* So if schema or option is not reqied validator doesnt care and
* validation pass
*/
import iniconfigurationmanager.schema.StructureVisitor;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import java.util.HashSet;

public class RelaxValidatorVisitor
        implements StructureVisitor {
 
    private HashSet<String> schemaSection;

    private HashSet<String> schemaOption;

    public RelaxValidatorVisitor() {
        this.schemaOption = new HashSet<String>();
        this.schemaSection = new HashSet<String>();
    }

    /**
     * Method visit option data and try remove its name from HashSet off
     * all requed Optiondata
     */
    public void visit( OptionData option ) {
      schemaOption.remove( option.getCanonicalName() );
          }

    /**
     * Method visit optionSchema and if its reqiered add its name to HashSet
     */
    public void visit( OptionSchema option ) {
        if ( option.isRequired() ) {
            schemaOption.add( option.getCanonicalName() );
        }
    }


    /**
     *Method that visiting section data.Name of section is stored
     */
    public void visit( SectionData section ) {
       schemaSection.remove( section.getName() );        
    }

    /**
     *Method that visiting section data.Name of section is stored
     */
    public void visit( SectionSchema section ) {
        if ( section.isRequired() ) {
            schemaSection.add( section.getName() );
      }
    }

    /**
     * <code>getResult</code> return result of validation
     * @return
     */
    public ValidationResult getResult() {
        ValidationResult result = new ValidationResult();

        if ( !schemaOption.isEmpty() ) {
           result.addErrorMessage( ValidationResult.INVALID_OPTION_ITEM );
       }
       if ( !schemaSection.isEmpty() ) {
           result.addErrorMessage( ValidationResult.INVALID_SCHEMA );
       }

       return result;
    }


    public void visit( ConfigurationData data ) {
        return;
    }
}
