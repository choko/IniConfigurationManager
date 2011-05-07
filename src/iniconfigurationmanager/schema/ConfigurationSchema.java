
package iniconfigurationmanager.schema;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigurationSchema implements Iterable< SectionSchema > {

    private Map< String, SectionSchema > sections;

    public ConfigurationSchema() {
        this.sections = new LinkedHashMap< String, SectionSchema >();
    }

    public void addSection ( String name, SectionSchema section ) {
        section.setName( name );

        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    public SectionSchema getSection( String name ) {
        return sections.get( name );
    }


    public Iterator<SectionSchema> iterator() {
        return sections.values().iterator();
    }


    public void accept( StructureVisitor visitor ) {
        for( SectionSchema section : sections.values() ) {
            section.accept( visitor );
        }
    }

}