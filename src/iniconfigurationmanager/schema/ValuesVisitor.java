
package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public interface ValuesVisitor {

    public void enter( OptionData option );

    public void visit( Object value );

    public void leave( OptionData option );

}
