
package iniconfigurationmanager;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.ValuesVisitor;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class LinkVisitor<T> implements ValuesVisitor {

    private HashSet< OptionData > enteredConfigOptions;

    private List< T > values;

    public LinkVisitor() {
        this.enteredConfigOptions = new HashSet<OptionData>();
        this.values = new LinkedList< T >();
    }


    public void enter( OptionData option ) {
        if( enteredConfigOptions.contains( option ) ) {
            //@TODO throw new exception
        }

        enteredConfigOptions.add( option );
    }

    
    public void visit( Object value ) {
        values.add( (T) value);
    }

    
    public void leave( OptionData option ) {
        enteredConfigOptions.remove( option );
    }


    public List<T> getValues() {
        return values;
    }

}
