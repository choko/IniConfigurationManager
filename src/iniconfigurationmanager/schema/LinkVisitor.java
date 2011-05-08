package iniconfigurationmanager.schema;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * LinkVisitor is implementation of ValuesVisitor interface, it is used for
 * retrieving data of a value link.
 */
public class LinkVisitor<T>
        implements ValuesVisitor {

    private HashSet<OptionData> enteredOptions;

    private List<T> values;


    public LinkVisitor() {
        this.enteredOptions = new HashSet<OptionData>();
        this.values = new LinkedList<T>();
    }


    /**
     * Adds the option to the entered options for determining, whether the links
     * make a cycle.
     *
     * @param option
     */
    public void enter( OptionData option ) {
        if ( enteredOptions.contains( option ) ) {
            throw new IllegalStateException( String.format(
                    SchemaError.CYCLIC_LINK.getMessage() ) );
        }

        enteredOptions.add( option );
    }


    /**
     * Adds the value to the values list.
     * 
     * @param value
     */
    public void visit( Object value ) {
        values.add( (T) value );
    }


    /**
     * Removes the option from the entered options.
     *
     * @param option
     */
    public void leave( OptionData option ) {
        enteredOptions.remove( option );
    }


    /**
     * Returns values that are linked by this link
     * 
     * @return<T>
     */
    public List<T> getValues() {
        return values;
    }
}
