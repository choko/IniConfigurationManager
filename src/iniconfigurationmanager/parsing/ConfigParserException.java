package iniconfigurationmanager.parsing;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigParserException
        extends Exception {

    private ConfigParserError error;

    private String message;

    private Object[] args;


    public ConfigParserException( String message ) {
        this.message = message;
    }


    public ConfigParserException( ConfigParserError error, Object... args ) {
        this.error = error;
        this.args = args;
    }


    @Override
    public String getMessage() {
        if ( message != null ) {
            return message;
        } else if ( error != null ) {
            return String.format( error.getMessage(), args );
        } else {
            return ConfigParserError.UNDEFINED_PARSER_ERROR.getMessage();
        }
    }
}
