
package iniconfigurationmanager.schema;

import iniconfigurationmanager.validators.ValidatorVisitor;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSchema implements Iterable< ConfigSectionSchema > {

    private Map< String, ConfigSectionSchema > sections;

    public ConfigSchema() {
        this.sections = new LinkedHashMap< String, ConfigSectionSchema >();
    }

    public void addSection ( String name, ConfigSectionSchema section ) {
        section.setName( name );

        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    public ConfigSectionSchema getSection( String name ) {
        return sections.get( name );
    }


    public Iterator<ConfigSectionSchema> iterator() {
        return sections.values().iterator();
    }


    public void accept( ValidatorVisitor visitor ) {
        for( ConfigSectionSchema section : sections.values() ) {
            section.accept( visitor );
        }
    }

}