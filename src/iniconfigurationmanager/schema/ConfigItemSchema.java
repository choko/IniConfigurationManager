
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
public final class ConfigItemSchema {

    private final String name;

    private final String sectionName;

    private final ConfigItemFormatDefinition formatDefinition;

    private boolean required;

    private String comment;

    private List< ValidationRule > validationRules;

    private List defaultValues;


    public ConfigItemSchema( String name, String section, ConfigItemFormatDefinition formatDefinition ) {
        this.name = name;
        this.sectionName = section;
        this.formatDefinition = formatDefinition;
        this.required = false;
        this.validationRules = new LinkedList<ValidationRule>();
    }


    public String getName() {
        return name;
    }


    public String getSectionName() {
        return sectionName;
    }


    public String getCanonicalName() {
        return String.format("%s#%s", sectionName, name);
    }


    public void setComment( String comment ) {
        this.comment = comment;
    }


    public String getComment() {
        return comment;
    }

    
    public void setRequired() {
        this.required = true;
    }


    public boolean isRequired() {
        return this.required;
    }

    
    public ConfigItemFormatDefinition getFormatDefinition() {
        return formatDefinition;
    }


    public void addValidationRule( ValidationRule rule ) {
        validationRules.add( rule );
    }


    public List< ValidationRule > getValidationRules() {
        return validationRules;
    }


    public void setDefaultValues(List defaultValues) {
        this.defaultValues = defaultValues;
    }


    public List getDefaultValues() {
        return defaultValues;
    }
    

    public void accept(ValidatorVisitor visitor) {
        visitor.visit( this );
    }
    
}
