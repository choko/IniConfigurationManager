
package iniconfigurationmanager.schema;

import iniconfigurationmanager.validators.ValidatorVisitor;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSectionSchema implements Iterable< ConfigItemSchema > {

    private String name;

    private Boolean required;

    private Map< String, ConfigItemSchema > items;


    public ConfigSectionSchema( String name ) {
        this.name = name;
        this.items = new LinkedHashMap<String, ConfigItemSchema>();
        this.required = false;
    }


    public String getName() {
        return name;
    }
    
    
    public void setReguired() {
        this.required = true;
    }


    public boolean isRequired() {
        return this.required;
    }


    public void addItem( String name, ConfigItemSchema item ) {
        items.put( name, item );
    }


    public boolean hasItem( String name ) {
        return items.containsKey( name );
    }


    public void removeItem( String name ) {
        items.remove( name );
    }


    public ConfigItemSchema getItem( String name ) {
        return items.get( name );
    }


    public Iterator<ConfigItemSchema> iterator() {
        return items.values().iterator();
    }


    public void accept( ValidatorVisitor visitor ) {
        for( ConfigItemSchema item : items.values() ) {
            item.accept( visitor );
        }

        visitor.visit( this );
    }

}