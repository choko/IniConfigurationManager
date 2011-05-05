
package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class NullConfigSectionData extends ConfigSectionData {

    private static final NullConfigSectionData INSTANCE =
            new NullConfigSectionData();

    
    public static NullConfigSectionData getInstance() {
        return INSTANCE;
    }

    
    private NullConfigSectionData() {
        super("");
    }

    
    @Override
    public void addItem(String name, ConfigItemData item) {
        throw new UnsupportedOperationException();
    }

    
}
