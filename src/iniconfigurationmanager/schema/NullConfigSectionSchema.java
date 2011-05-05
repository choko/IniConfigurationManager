
package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class NullConfigSectionSchema extends ConfigSectionSchema {

    private static final NullConfigSectionSchema INSTANCE =
            new NullConfigSectionSchema();


    public static NullConfigSectionSchema getInstance() {
        return INSTANCE;
    }


    private NullConfigSectionSchema() {
        super("");
    }

    @Override
    public boolean hasItem(String name) {
        return false;
    }
    
}
