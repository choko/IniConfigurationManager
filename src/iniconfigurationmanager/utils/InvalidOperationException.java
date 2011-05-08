package iniconfigurationmanager.utils;

/**
 * InvalidOperationException represents errors which can occur by doing
 * unallowed operations to the configuration schema.
 */
public class InvalidOperationException
        extends UnsupportedOperationException {

    public InvalidOperationException( String message ) {
        super( message );
    }
}
