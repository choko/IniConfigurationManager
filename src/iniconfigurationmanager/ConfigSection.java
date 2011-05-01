
package iniconfigurationmanager;

import iniconfigurationmanager.items.ConfigItem;
import java.util.LinkedHashMap;
import java.util.Map;
import iniconfigurationmanager.items.StringConfigItem;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSection {

    private String name;

    private Boolean required;

    private ConfigItem defaultItem;

    private Map< String, ConfigItem > items;
    
    public ConfigSection( String name ) {
        this.name = name;
        this.items = new LinkedHashMap<String, ConfigItem>();
        this.required = false;
    }

    public void setReguired() {
        this.required = true;
    }

    public void UnsetRequired() {
        this.required = false;
    }

    private boolean getRequired() {
        return this.required;
    }


    public void addItem( String name, ConfigItem item ) {
        items.put( name, item );
    }

    public void addStringItem( String name ) {
        addItem(name,new StringConfigItem(name));
    }

    public void addSignedItem( String name ) {

    }

    public void addUnsignedItem( String name ){

    }

    public void addEnumItem( String name ) {

    }

    public void addBoolItem( String name ) {

    }

    public void addFloatItem( String name) {

    }

    public void addRefItem( String name ) {

    }


    public boolean hasItem( String name ) {
        return items.containsKey( name );
    }

    public ConfigItem getItem( String name ) {
        return items.get( name );
    }

    public void  setDefaultItem (ConfigItem item) {
        this.defaultItem = item;
    }

    public boolean getRequidedItem ( String name ) {
        return items.get( name ).getRequided();
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append( ConfigLine.SECTION_DEFINITION_START );
        sb.append( name );
        sb.append( ConfigLine.SECTION_DEFINITION_END );
        sb.append( ConfigParser.NEWLINE );

        for( ConfigItem item : items.values() ) {
            sb.append( item.toString() );
        }

        sb.append( ConfigParser.NEWLINE );

        return sb.toString();
    }
    
}
