
package iniconfigurationmanager;

import iniconfigurationmanager.items.ConfigItem;
import java.util.LinkedHashMap;
import java.util.Map;
import iniconfigurationmanager.items.StringConfigItem;
import iniconfigurationmanager.items.BooleanConfigItem;
import iniconfigurationmanager.items.SignedConfigItem;
import iniconfigurationmanager.items.FloatConfigItem;
import iniconfigurationmanager.items.UnsignedConfigItem;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSection {

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

    public void unsetRequired() {
        this.required = false;
    }

    private boolean isRequired() {
        return this.required;
    }


    public void addItem( String name, ConfigItem item ) {
        items.put( name, item );
    }

    public StringConfigItem addStringItem( String name ) {
        StringConfigItem item = new StringConfigItem( name );
        this.addItem( name,item );
        return item;

    }

    public SignedConfigItem addSignedItem( String name ) {
        SignedConfigItem item = new SignedConfigItem( name );
        this.addItem( name,item );
        return item;
    }

    public UnsignedConfigItem addUnsignedItem( String name ){
        UnsignedConfigItem item = new UnsignedConfigItem( name );
        this.addItem( name,item);
        return item;

    }

    public void addEnumItem( String name ) {

    }

    public BooleanConfigItem addBooleanItem( String name ) {
        BooleanConfigItem item = new BooleanConfigItem( name);
        this.addItem(name,item);
        return item;
    }

    public FloatConfigItem addFloatItem( String name) {
        FloatConfigItem item = new FloatConfigItem( name);
        this.addItem(name,item);
        return item;
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
