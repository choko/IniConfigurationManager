/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager;

import iniconfigurationmanager.items.ConfigItem;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ValueLink {

    private String sectionName;

    private String itemName;

    private ConfigData configuration;

    
    public ValueLink( String link, ConfigData configuration ) {
        this.sectionName = getSectionName( link );
        this.itemName = getItemName( link );
        this.configuration = configuration;
    }


    private String getSectionName( String link ) {
        return link.substring( 2, link.indexOf("#") );
    }


    private String getItemName( String link ) {
        return link.substring( link.indexOf("#"), link.length() - 1 );
    }
    

    public <T> List< T > getValues( T type ) {
        LinkVisitor< T > visitor = new LinkVisitor< T >( type );
        getLinkedItem().accept( visitor );

        return visitor.getValues();
    }


    public ConfigSection getLinkedSection() {
        if( configuration.hasSection( sectionName ) ) {
            return configuration.getSection( sectionName );
        } else {
            return null;
        }
    }

    public ConfigItem getLinkedItem() {
        ConfigSection section = getLinkedSection();

        if( section.hasItem( itemName ) ) {
            return section.getItem( itemName );
        } else {
            return null;
        }
    }
}
