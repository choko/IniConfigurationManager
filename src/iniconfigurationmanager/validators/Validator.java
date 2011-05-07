
package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigurationData;

/**
 *
 * @author KlonK
 */
public class Validator {

    public static ValidationResult validateStrict( ConfigurationData data ) {
        StrictValidatorVisitor visitor = new StrictValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


    public static ValidationResult validateRelax( ConfigurationData data ) {
        RelaxValidatorVisitor visitor = new RelaxValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


    public static ValidationResult validateValidationRules( ConfigurationData data ) {
        RulesValidatorVisitor visitor = new RulesValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


}
