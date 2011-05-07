
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.StructureVisitor;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import java.util.HashSet;

/**
 *
 * @author KlonK
 */
public class StrictValidatorVisitor implements StructureVisitor {

    private ValidationResult result;

    private HashSet< String > schemaSection;

    private HashSet< String > schemaOption;
 
    public void visit( OptionData option ) {
        boolean hasOption = schemaOption.remove( option.getCanonicalName() );
        if ( !hasOption ) {
            result.addErrorMsg( "missing option " ); //TODO ENUM
        }
        result.addResult( hasOption );
    }

    public void visit( OptionSchema option ) {
      schemaOption.add( option.getCanonicalName() );
    }

    public void visit( SectionData section ) {
      boolean haveSection =  schemaSection.remove( section.getName() );
      if ( !haveSection ) {
        result.addErrorMsg( "missing section "); //TODO ENUM
      }
      result.addResult( haveSection );
    }

    public void visit( SectionSchema section ) {
       schemaSection.add( section.getName() );
      }

    public ValidationResult getResult() {
        return result;
    }

    public void visit(ConfigurationData data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
