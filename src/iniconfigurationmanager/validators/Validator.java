package iniconfigurationmanager.validators;

import iniconfigurationmanager.schema.ConfigurationData;

/**
 * <codeValidator</code> cover diferent validors.
 * It have method for validate given data by
 * StrictValidator,RelaxValidator and RulesValidator
 */
public class Validator {

    /**
     * <code>validateStrict</code> Strict validate given data.
     * Strict validation mean ,that every section and every option
     * MUST be define in scheme otherwise makes error and validation
     * doesnt pass
     *
     * @param data
     * @return ValidationResult
     */
    public static ValidationResult validateStrict( ConfigurationData data ) {
        StrictValidatorVisitor visitor = new StrictValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }

    /**
     * <code>validateRelax</code> Relax validate given data.
     * Relax validation mean ,that only schemas and option that set to requied
     * while creating schema is validate.
     * <p>
     * So if schema or option is reqied and isnt in
     * data it makes error and validation doesn pass.
     * <p>
     * So if schema or option is not reqied validator doesnt care and
     * validation pass
     * 
     * @param data
     * @return ValidationResult
     */
    public static ValidationResult validateRelax( ConfigurationData data ) {
        RelaxValidatorVisitor visitor = new RelaxValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }


    /**
     * <code>validateValidationRules</code> validate Option by its
     * <code>ValidationRule</code>s
     *
     * @param data
     * @return
     */
    public static ValidationResult validateValidationRules(
            ConfigurationData data ) {
        RulesValidatorVisitor visitor = new RulesValidatorVisitor();
        data.accept( visitor );

        return visitor.getResult();
    }
}
