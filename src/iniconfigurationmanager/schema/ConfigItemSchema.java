
package iniconfigurationmanager.schema;

import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.rules.ValidationRule;
import iniconfigurationmanager.validators.ValidatorVisitor;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public abstract class ConfigItemSchema {

    private String name;

    private String sectionName;

    private ConfigItemFormatDefinition formatDefinition;

    private boolean required;

    private String comment;

    private List< ValidationRule > validationRules;

    private List defaultValues;

    
    public ConfigItemSchema() {
        this.required = false;
        this.validationRules = new LinkedList<ValidationRule>();
    }


    protected ConfigItemSchema setName( String name ) {
        this.name = name;

        return this;
    }


    public String getName() {
        return name;
    }


    protected ConfigItemSchema setSectionName( String sectionName ) {
        this.sectionName = sectionName;

        return this;
    }

    public String getSectionName() {
        return sectionName;
    }


    public String getCanonicalName() {
        return String.format("%s#%s", sectionName, name);
    }


    public ConfigItemSchema setComment( String comment ) {
        this.comment = comment;

        return this;
    }


    public String getComment() {
        return comment;
    }

    
    public ConfigItemSchema setRequired() {
        this.required = true;

        return this;
    }


    public boolean isRequired() {
        return this.required;
    }

    
    public ConfigItemFormatDefinition getFormatDefinition() {
        return formatDefinition;
    }


    public ConfigItemSchema addValidationRule( ValidationRule rule ) {
        validationRules.add( rule );

        return this;
    }


    public List< ValidationRule > getValidationRules() {
        return validationRules;
    }


    public ConfigItemSchema setDefaultValues(List defaultValues) {
        this.defaultValues = defaultValues;

        return this;
    }


    public List getDefaultValues() {
        return defaultValues;
    }


    public boolean hasDefaultValue() {
        return ! defaultValues.isEmpty();
    }
    

    public void accept(ValidatorVisitor visitor) {
        visitor.visit( this );
    }


    public abstract ConfigItemData getItemData();
    
}
