
package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.ConfigFormatDefinition;
import iniconfigurationmanager.validators.ValidatorVisitor;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSectionData implements Iterable< ConfigItemData > {

    private String name;

    private Map< String, ConfigItemData > items;
    

    public ConfigSectionData( String name ) {
        this.name = name;
        this.items = new LinkedHashMap<String, ConfigItemData>();
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

    public String getName() {
        return this.name;
    }


    public void accept( ValidatorVisitor visitor ) {
        for( ConfigItemData item : items.values() ) {
            item.accept( visitor );
        }

        visitor.visit( this );
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append( ConfigFormatDefinition.SECTION_DEFINITION_START );
        sb.append( name );
        sb.append( ConfigFormatDefinition.SECTION_DEFINITION_END );
        sb.append( ConfigFormatDefinition.NEWLINE );

        for( ConfigItemData item : items.values() ) {
            sb.append( item.toString() );
        }

        sb.append( ConfigFormatDefinition.NEWLINE );

        return sb.toString();
    }
    
}
