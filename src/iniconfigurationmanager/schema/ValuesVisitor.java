package iniconfigurationmanager.schema;

/**
 * ValuesVisitor is an interface implementing Visitor design pattern for
 * traversing values of the options.
 */
public interface ValuesVisitor {

    public void enter( OptionData option );


    public void visit( Object value );


    public void leave( OptionData option );
}
