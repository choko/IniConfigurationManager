package iniconfigurationmanager.validators;

/**
 * <code>validateStrict</code> Strict validate given data.
 * Strict validation mean ,that every section and every option
 * MUST be define in scheme otherwise makes error and validation
 * doesnt pass

 */
import iniconfigurationmanager.schema.StructureVisitor;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import java.util.HashSet;


public class StrictValidatorVisitor
        implements StructureVisitor {

    private ValidationResult result;

    private HashSet<String> schemaSection;

    private HashSet<String> schemaOption;

    public StrictValidatorVisitor() {
        this.result = new ValidationResult();
        this.schemaOption = new HashSet<String>();
        this.schemaSection = new HashSet<String>();
    }

    /**
     * Method visit option data and try remove its name from HashSet off
     * all  Optiondata thats in the schema
     */
    public void visit( OptionData option ) {
        boolean hasOption = schemaOption.remove( option.getCanonicalName() );
        if ( !hasOption ) {
            result.addErrorMsg( ValidationResult.INVALID_OPTION_ITEM );
        }
        result.addResult( hasOption );
    }

    /**
     * Method visit optionSchema and if its reqiered add its name to HashSet
     */
    public void visit( OptionSchema option ) {
        schemaOption.add( option.getCanonicalName() );
    }

     /**
     *Method that removes section nae from schemaSection
     */
    public void visit( SectionData section ) {
        boolean haveSection = schemaSection.remove( section.getName() );
        if ( !haveSection ) {
            result.addErrorMsg( ValidationResult.INVALID_SCHEMA );
        }
        result.addResult( haveSection );
    }

    /**
     *Method that visiting section data.Name of section is stored
     */
    public void visit( SectionSchema section ) {
        schemaSection.add( section.getName() );
    }

    /**
     * <code>getResult</code> return result of validation
     * @return
     */
    public ValidationResult getResult() {
        return result;
    }


    public void visit( ConfigurationData data ) {
        return;
    }
}
