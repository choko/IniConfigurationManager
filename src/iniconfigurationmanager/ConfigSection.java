
package iniconfigurationmanager;

import iniconfigurationmanager.items.ConfigItem;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSection implements Iterable< ConfigItem >, Cloneable {

    private String name;

    private Boolean required;  

    private Map< String, ConfigItem > items;
    

    public ConfigSection( String name ) {
        this.name = name;
        this.items = new LinkedHashMap<String, ConfigItem>();
        this.required = false;
    }


    public void setReguired() {
        this.required = true;
    }


    private boolean isRequired() {
        return this.required;
    }


    public void addItem( String name, ConfigItem item ) {
        items.put( name, item );
    }
  

    public boolean hasItem( String name ) {
        return items.containsKey( name );
    }


    public void removeItem( String name ) {
        items.remove( name );
    }


    public ConfigItem getItem( String name ) {
        return items.get( name );
    }


    public Iterator<ConfigItem> iterator() {
        return items.values().iterator();
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

    
    @Override
    public Object clone() {
        ConfigSection sectionClone = new ConfigSection( this.name );
        sectionClone.required = this.required;

        for( String itemName : this.items.keySet() ) {
            ConfigItem oldItem = items.get( itemName );
            ConfigItem item = oldItem.copy();
            item.setValues( oldItem.getValues() ); //@TODO this is a hack
                
            sectionClone.addItem( itemName, item );
        }

        return sectionClone;
    }
    
}
