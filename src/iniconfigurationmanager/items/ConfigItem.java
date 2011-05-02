
package iniconfigurationmanager.items;


import java.util.List;
import iniconfigurationmanager.ConfigVisitor;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public abstract class ConfigItem {

    protected String name;

    private boolean required;
    
    public ConfigItem( String name ) {
        this.name = name;
        this.required = false;
    }

    public void setRequired() {
        this.required = true;
    }

    public void unsetRequired() {
        this.required = false;
    }

    public boolean isRequired() {
        return this.required;
    }

    public abstract void setValues( List< String > values );

    public abstract void accept(ConfigVisitor visitor);

    
}
