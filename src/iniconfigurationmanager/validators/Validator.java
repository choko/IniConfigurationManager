
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigData;

/**
 *
 * @author KlonK
 */
public class Validator {

    public static ValidationResult validateStrict( ConfigData data ) {
        StrictValidatorVisitor visitor = new StrictValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


    public static ValidationResult validateRelax( ConfigData data ) {
        RelaxValidatorVisitor visitor = new RelaxValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


    public static ValidationResult validateValidationRules( ConfigData data ) {
        RulesValidatorVisitor visitor = new RulesValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


}
