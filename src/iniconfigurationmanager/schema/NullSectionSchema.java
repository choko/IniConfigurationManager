package iniconfigurationmanager.schema;

/**
 * NullSectionSchema implements Null object design pattern. It is used to handle
 * special case during the parsing of a configuration input, when no section has
 * not been defined yet.
 */
public class NullSectionSchema
        extends SectionSchema {

    public NullSectionSchema() {
        super();
    }


    @Override
    public boolean hasOption( String name ) {
        return false;
    }
}
