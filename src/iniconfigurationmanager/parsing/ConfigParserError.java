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

    UNEXPECTED_LINE ( "Line %s '%s' is unexpected." ),
    INVALID_SECTION_NAME ( "Section name '%s' is not valid." ),
    INVALID_ITEM_NAME ( "Item name '%s' is not valid." ),
    UNDEFINED_SECTION ( "Section has to be defined before line '%s'." );

    private final String message;

    ConfigParserError( String message ) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
