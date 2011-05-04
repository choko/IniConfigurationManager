/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.validators;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class ValidatorResult {

    private LinkedList<String> errorMsgs;

    public ValidatorResult() {
        this.errorMsgs = new LinkedList<String>();
    }

    public void addResult( String errorMsg ) {
        if ( errorMsg != null ) {
            this.errorMsgs.add( errorMsg );
        }
    }

    public boolean getResult() {
        if( this.errorMsgs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addErrorMsg( String errorMsg ) {
        this.errorMsgs.add(errorMsg);
    }

    public List<String> getErrprMsgs() {
        return this.errorMsgs;
    }

}
