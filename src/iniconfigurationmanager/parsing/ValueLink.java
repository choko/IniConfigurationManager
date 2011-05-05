/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.parsing;

import iniconfigurationmanager.LinkVisitor;
import iniconfigurationmanager.schema.ConfigData;
import iniconfigurationmanager.schema.ConfigSectionData;
import iniconfigurationmanager.schema.ConfigItemData;
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
        LinkVisitor< T > visitor = new LinkVisitor< T >();
        getLinkedItem().accept( visitor );

        return visitor.getValues();
    }


    public ConfigSectionData getLinkedSection() {
        if( configuration.hasSection( sectionName ) ) {
            return configuration.getSection( sectionName );
        } else {
            return null;
        }
    }

    public ConfigItemData getLinkedItem() {
        ConfigSectionData section = getLinkedSection();

        if( section.hasItem( itemName ) ) {
            return section.getItem( itemName );
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format( ConfigFormatDefinition.LINK_FORMAT, 
                getLinkedItem().getCanonicalName() );
    }
   
}
