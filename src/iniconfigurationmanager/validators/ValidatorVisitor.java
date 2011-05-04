
package iniconfigurationmanager.validators;

import iniconfigurationmanager.items.ConfigItem;
import iniconfigurationmanager.validators.ValidatorResult;
import iniconfigurationmanager.rules.ValidationRule;
import java.util.HashSet;


/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ValidatorVisitor {


    private ValidatorResult validationResult;

    private HashSet< ConfigItem > enteredConfigItems;

    public void visit( ConfigItem item ) {

        for (ValidationRule rule : item.getValidationRules()) {
            validationResult.addResult(rule.validate(item));
        }
   }

   public ValidatorResult getResult() {
       return this.validationResult;
   }


}
