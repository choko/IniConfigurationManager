
package iniconfigurationmanager.schema;

import iniconfigurationmanager.ConfigLine;
import iniconfigurationmanager.ConfigParser;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSectionData implements Iterable< ConfigItemData > {

    private String name;

    private Boolean required;  

    private Map< String, ConfigItemData > items;
    

    public ConfigSectionData( String name ) {
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

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append( ConfigLine.SECTION_DEFINITION_START );
        sb.append( name );
        sb.append( ConfigLine.SECTION_DEFINITION_END );
        sb.append( ConfigParser.NEWLINE );

        for( ConfigItemData item : items.values() ) {
            sb.append( item.toString() );
        }

        sb.append( ConfigParser.NEWLINE );

        return sb.toString();
    }

    
    @Override
    public Object clone() {
        ConfigSectionData sectionClone = new ConfigSectionData( this.name );
        sectionClone.required = this.required;

        for( String itemName : this.items.keySet() ) {
            ConfigItemData oldItem = items.get( itemName );
            ConfigItemData item = oldItem.copy();
            item.setValues( oldItem.getValues() ); //@TODO this is a hack
                
            sectionClone.addItem( itemName, item );
        }

        return sectionClone;
    }
    
}
