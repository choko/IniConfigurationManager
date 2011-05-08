package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.StructureVisitor;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author KlonK
 */
public class RelaxValidatorVisitor
        implements StructureVisitor {

    private ValidationResult result;

    private HashSet<String> schemaSection;

    private HashSet<String> schemaOption;


    public void visit( OptionData option ) {
        boolean hasOption = schemaOption.remove( option.getCanonicalName() );
        if ( !hasOption ) {
            result.addErrorMsg( ValidationResult.INVALID_OPTION_ITEM );
        }
        result.addResult( hasOption );
    }


    public void visit( OptionSchema option ) {
        if ( option.isRequired() ) {
            schemaOption.add( option.getCanonicalName() );
        }
    }


    public void visit( SectionData section ) {
        boolean haveSection = schemaSection.remove( section.getName() );
        if ( !haveSection ) {
            result.addErrorMsg( ValidationResult.INVALID_SCHEMA );
        }
        result.addResult( haveSection );
    }


    public void visit( SectionSchema section ) {
        if ( section.isRequired() ) {
            schemaSection.add( section.getName() );
        }
    }


    public ValidationResult getResult() {
        return result;
    }


    public void visit( ConfigurationData data ) {
        return;
    }
}
