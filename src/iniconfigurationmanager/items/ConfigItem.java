
package iniconfigurationmanager.items;

import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public abstract class ConfigItem {

    protected String name;

    private boolean requided;
    
    public ConfigItem( String name ) {
        this.name = name;
    }

    public void setRequided() {
        this.requided = true;
    }

    public void UnsetRequided() {
        this.requided = false;
    }

    public boolean getRequided() {
        return this.requided;
    }

    public abstract void setValues( List< String > values );
    
}
