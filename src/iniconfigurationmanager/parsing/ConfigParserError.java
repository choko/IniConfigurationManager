/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iniconfigurationmanager.parsing;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public enum ConfigParserError {

    UNEXPECTED_LINE( "Line %s '%s' is unexpected." ),
    INVALID_SECTION_NAME( "Section name '%s' is not valid." ),
    INVALID_OPTION_NAME( "Option name '%s' is not valid." ),
    TYPE_PARSING_EXCEPTION( "Value cannot be parsed at line '%s'." ),
    INPUT_ERROR( "Error occurred during reading the configuration. %s" ),
    UNDEFINED_PARSER_ERROR( "Undefined error occurred during parsing." );

    private final String message;


    ConfigParserError( String message ) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
