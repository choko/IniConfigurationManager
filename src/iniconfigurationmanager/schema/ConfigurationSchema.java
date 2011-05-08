package iniconfigurationmanager.schema;

import iniconfigurationmanager.utils.InvalidOperationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ConfigurationSchema represents configuration schema structure.
 */
public class ConfigurationSchema
        implements Iterable<SectionSchema> {

    private Map<String, SectionSchema> sections;


    public ConfigurationSchema() {
        this.sections = new LinkedHashMap<String, SectionSchema>();
    }


    /**
     * Adds section to the sections list.
     *
     * @param section name
     * @param section
     * @return this instance for fluent interface
     * @throws InvalidOperationException whether the section already exists
     * @throws IllegalArgumentException whether the section is null
     */
    public ConfigurationSchema addSection( String name, SectionSchema section ) {
        if ( hasSection( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_SECTION_SCHEMA.getMessage(), name ) );
        }

        if ( section == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_SECTION_SCHEMA.getMessage(), name ) );
        }

        section.setName( name );
        sections.put( name, section );

        return this;
    }


    /**
     * Determines whether a section with the name exists.
     *
     * @param name section name
     * @return
     */
    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    /**
     * Return section with the given name.
     *
     * @param name section name
     * @return
     * @throws IllegalArgumentException whether section doesn't exist
     */
    public SectionSchema getSection( String name ) {
        if ( !hasSection( name ) ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.UNDEFINED_SECTION_SCHEMA.getMessage(), name ) );
        }

        return sections.get( name );
    }


    /**
     * Removes section from the sections list.
     *
     * @param name section name
     * @return this instance for fluent interface
     */
    public ConfigurationSchema removeSection( String name ) {
        sections.remove( name );

        return this;
    }


    /**
     * Returns iterator for iterating over section's schemas.
     *
     * @return<SectionSchema>
     */
    public Iterator<SectionSchema> iterator() {
        return sections.values().iterator();
    }


    /**
     * Accepts visitors implementing StructureVisitor pattern
     *
     * @param visitor
     * @return this instance for fluent interface
     */
    public ConfigurationSchema accept( StructureVisitor visitor ) {
        for ( SectionSchema section : sections.values() ) {
            section.accept( visitor );
        }

        return this;
    }
}
