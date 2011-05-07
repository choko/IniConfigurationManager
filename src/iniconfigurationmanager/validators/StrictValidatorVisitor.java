
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigData;
import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigSectionSchema;
import java.util.HashSet;

/**
 *
 * @author KlonK
 */
public class StrictValidatorVisitor implements ValidatorVisitor {

    private ValidationResult result;

    private HashSet< String > schemaSection;

    private HashSet< String > schemaItem;
 
    public void visit( ConfigItemData item ) {
        boolean haveItem = schemaItem.remove( item.getCanonicalName() );
        if ( !haveItem ) {
            result.addErrorMsg( "missing item " ); //TODO ENUM
        }
        result.addResult( haveItem );
    }

    public void visit( ConfigItemSchema item ) {
      schemaItem.add( item.getCanonicalName() );
    }

    public void visit( ConfigSectionData section ) {
      boolean haveSection =  schemaSection.remove( section.getName() );
      if ( !haveSection ) {
        result.addErrorMsg( "missing section "); //TODO ENUM
      }
      result.addResult( haveSection );
    }

    public void visit( ConfigSectionSchema section ) {
       schemaSection.add( section.getName() );
      }

    public ValidationResult getResult() {
        return result;
    }

    public void visit(ConfigData data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
