package iniconfigurationmanager.utils;

/**
 * InvalidOperationException represents errors which can occur by doing
 * unallowed operations to the configuration schema.
 */
public class InvalidOperationException
        extends UnsupportedOperationException {

    /**
    *Method<code>InvalidOperationException</code> returning
    * Expection that is throw when user done Invalid Operation
    *
    * @param message
    */
    public InvalidOperationException( String message ) {
        super( message );
    }
}
