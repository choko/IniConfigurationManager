
package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class NullConfigSectionSchema extends ConfigSectionSchema {

    public NullConfigSectionSchema() {
        super();
    }

    @Override
    public boolean hasItem(String name) {
        return false;
    }
    
}
