package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
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
