
package iniconfigurationmanager.parsing;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigParserException extends Exception {

    private ConfigParserError error;

    private Object[] args;
    
    public ConfigParserException( ConfigParserError error, Object... args ) {
        this.error = error;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return String.format( error.getMessage(), args);
    }

}
