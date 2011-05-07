
package iniconfigurationmanager.schema;

import iniconfigurationmanager.utils.InvalidOperationException;
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

    public ConfigurationSchema addSection ( String name, SectionSchema section ) {
        if( hasSection( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_SECTION_SCHEMA.getMessage(), name ) );
        }

        if( section == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_SECTION_SCHEMA.getMessage(), name ) );
        }

        section.setName( name );
        sections.put(name, section);

        return this;
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    public SectionSchema getSection( String name ) {
        return sections.get( name );
    }


    public ConfigurationSchema removeSection( String name ) {
        sections.remove( name );

        return this;
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