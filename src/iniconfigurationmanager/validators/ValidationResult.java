package iniconfigurationmanager.validators;

import java.util.LinkedList;
import java.util.List;

/**
 * <code>ValidationResult</code> handle validating result
 * It contains every Report of error during validation
 */
public class ValidationResult {

    /**
     * errorMsgs contains every Report of error during validation
     */
    private LinkedList<String> errorMessages;

    private boolean result;


    public ValidationResult() {
        this.errorMessages = new LinkedList<String>();
    }

    /**
     * Method <code>addResult</code> merge @param value result
     */
    public void addResult( boolean result ) {
        this.result = this.result && result;
    }


    /**
     * Method returns <code>boolean</code> result.
     * Its return<code>TRUE</code> if none error report.
     * return <code>FALSE</code> if it has any error report.
     */
    public boolean isOk() {
       if ( errorMessages.isEmpty() ) {
           return true;
       } else {
           return false;
       }

    }

    /**
     * Method added error report that trow validator
     */
    public void addErrorMessage( String errorMessage ) {
        this.errorMessages.add( errorMessage );
    }

    /**
     * Method return all error reports
     */
    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    /**
     * Method merge two ValidationResult to one
     */
    public void mergeResults( ValidationResult result ) {
        this.errorMessages.addAll( result.getErrorMessages() );
        this.result = result.isOk();
    }
    
    /**
     * Constant errors report
     */
    public static String INVALID_OPTION_ITEM = "Item is not define in schema";

    public static String INVALID_SCHEMA =
            "Section is not define in schema";

    public static String INVALID_RULE_APPLICATED =
            "Unsuported rule applicated on OptionSchema";
}
