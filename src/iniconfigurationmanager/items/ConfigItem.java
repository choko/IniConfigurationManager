
package iniconfigurationmanager.items;

import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public abstract class ConfigItem {

    private String name;
    
    public ConfigItem( String name ) {
        this.name = name;
    }

    public void setValues( List< String > values ) {
        
    }
    
}
