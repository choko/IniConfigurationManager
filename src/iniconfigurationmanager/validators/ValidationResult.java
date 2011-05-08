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

    // Rules error msgs
    public static String MISSING_ITEM = " ";
    public static String LOW_VALUE = "Option value is lower that rule";
    public static String HIGH_VALUE = "Option value is higher that rule";
    public static String INVALID_COUNT = 
            "Option value doesnt have valid count";
    public static String NOT_ODD_VALUE = "Option value is not a odd number";
    public static String NOT_EVEN_VALUE = "Option value is not a even number";
    public static String NOT_CONTAIN_VALUE =
            "Option doesnt containt ruled value";
    public static String ENUM_INVALID_VALUE= 
            "Option doesnt have all ruled value";


    public static String INVALID_RULE_APPLICATED = 
            "Unsuported rule applicated on OptionSchema";

}
