
package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class NullConfigSectionSchema extends ConfigSectionSchema {

    public NullConfigSectionSchema( String name ) {
        super( name );
    }

    @Override
    public boolean hasItem(String name) {
        return false;
    }
    
}
