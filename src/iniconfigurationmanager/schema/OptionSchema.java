package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.Format;
import iniconfigurationmanager.rules.ValidationRule;
import iniconfigurationmanager.utils.ValidatorUtils;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract OptionData represents option's schema in the configuration schema.
 */
public abstract class OptionSchema {

    private String name;

    private String sectionName;

    private boolean required;

    private String comment;

    private List<ValidationRule> validationRules;

    private List defaultValues;


    public OptionSchema() {
        this.validationRules = new LinkedList<ValidationRule>();
        this.required = false;
        this.comment = "";
    }


    /**
     * Sets name of this option
     *
     * @param String name
     * @return OptionSchema this instance for fluent interface
     */
    protected OptionSchema setName( String name ) {
        this.name = name;

        return this;
    }


    /**
     * Returns name of this option.
     *
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Sets name of section that this option belongs to
     *
     * @param String sectionName
     * @return OptionSchema this instance for fluent interface
     */
    protected OptionSchema setSectionName( String sectionName ) {
        this.sectionName = sectionName;

        return this;
    }


    /**
     * Return name of a section that this option belongs to.
     * 
     * @return String
     */
    public String getSectionName() {
        return sectionName;
    }


    /**
     * Returns canonical name of this option. It's a string in
     * section_name#option_name format.
     *
     * @return String
     */
    public String getCanonicalName() {
        return String.format( Format.OPTION_CANONICAL_NAME_FORMAT,
                sectionName, name );
    }


    /**
     * Sets comment for this option schema.
     * 
     * @param String comment
     * @return OptionSchema this instance for fluent interface
     */
    public OptionSchema setComment( String comment ) {
        this.comment = comment;

        return this;
    }


    /**
     * Returns comment of this option schema.
     * 
     * @return String
     */
    public String getComment() {
        return comment;
    }


    /**
     * Sets this option schema required.
     * 
     * @return OptionSchema this instance for fluent interface
     */
    public OptionSchema setRequired() {
        this.required = true;

        return this;
    }


    /**
     * Determines whether this section is required.
     * 
     * @return boolean
     */
    public boolean isRequired() {
        return this.required;
    }


    /**
     * Adds validation rule to this validation rules list.
     *
     * @param ValidationRule rule
     * @return OptionSchema this instance for fluent interface
     * @throws IllegalArgumentException whether the rule is rule
     * @throws UnsupportedOperationException whether the rule is not aplicable
     */
    public OptionSchema addValidationRule( ValidationRule rule ) {
        if ( rule == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_VALIDATION_RULE.getMessage() ) );
        }

        if ( ! ValidatorUtils.isRuleAplicableOn( rule, this ) ) {
            throw new UnsupportedOperationException( String.format(
                    SchemaError.UNALLOWED_VALIDATION_RULE.getMessage() ) );
        }

        validationRules.add( rule );

        return this;
    }


    /**
     * Returns list of validation rules.
     * 
     * @return List<ValidationRule>
     */
    public List<ValidationRule> getValidationRules() {
        return validationRules;
    }


    /**
     * Sets default values for this option schema.
     * 
     * @param List defaultValues
     * @return OptionSchema this instance for fluent interface
     */
    public OptionSchema setDefaultValues( List defaultValues ) {
        this.defaultValues = defaultValues;

        return this;
    }


    /**
     * Return list of default values.
     * 
     * @return List
     */
    public List getDefaultValues() {
        return defaultValues;
    }


    /**
     * Determines whether this option schema has default value.
     * @return
     */
    public boolean hasDefaultValue() {
        return defaultValues != null && !defaultValues.isEmpty();
    }


    /**
     * Accepts visitors implementing StructureVisitor pattern.
     * 
     * @param StructureVisitor visitor
     * @return OptionSchema this instance for fluent interface
     */
    public OptionSchema accept( StructureVisitor visitor ) {
        visitor.visit( this );

        return this;
    }


    /**
     * Returns appropriate OptionData class instance.
     * 
     * @return OptionData
     */
    public abstract OptionData getOptionData();
}
