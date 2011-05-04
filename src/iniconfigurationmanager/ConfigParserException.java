/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
class ConfigParserException extends Exception {

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
