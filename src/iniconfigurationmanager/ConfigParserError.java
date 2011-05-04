/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public enum ConfigParserError {

    UNEXPECTED_LINE ( "Line %s '%s' is unexpected." ),
    INVALID_SECTION_NAME ( "Section name '%s' is not valid." ),
    INVALID_ITEM_NAME ( "Item name '%s' is not valid." );


    private final String message;

    ConfigParserError( String message ) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
