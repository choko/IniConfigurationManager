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
public class ValidationResult {

    private LinkedList<String> errorMsgs;
    private boolean result;

    public ValidationResult() {
        this.errorMsgs = new LinkedList<String>();
    }

    public void addResult( boolean result ) {
        this.result = this.result && result;
    }

    public boolean getResult() {
        return this.result;
    }

    public void addErrorMsg( String errorMsg ) {
        this.errorMsgs.add(errorMsg);
    }

    public List<String> getErrorMsgs() {
        return this.errorMsgs;
    }

    public void mergeResults( ValidationResult result ) {
        this.errorMsgs.addAll( result.getErrorMsgs() );
        this.result = result.getResult();
    }

}
