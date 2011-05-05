
package iniconfigurationmanager.schema;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSectionSchema implements Iterable< ConfigItemData > {

    private String name;

    private Boolean required;

    private Map< String, ConfigItemData > items;


    public ConfigSectionSchema( String name ) {
        this.name = name;
        this.items = new LinkedHashMap<String, ConfigItemData>();
        this.required = false;
    }


    public void setReguired() {
        this.required = true;
    }


    private boolean isRequired() {
        return this.required;
    }


    public void addItem( String name, ConfigItemData item ) {
        items.put( name, item );
    }


    public boolean hasItem( String name ) {
        return items.containsKey( name );
    }


    public void removeItem( String name ) {
        items.remove( name );
    }


    public ConfigItemData getItem( String name ) {
        return items.get( name );
    }


    public Iterator<ConfigItemData> iterator() {
        return items.values().iterator();
    }

}
