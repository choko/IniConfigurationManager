
package iniconfigurationmanager.schema;

import iniconfigurationmanager.utils.InvalidOperationException;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class NullSectionData extends SectionData {

    private static final NullSectionData INSTANCE =
            new NullSectionData();

    
    public static NullSectionData getInstance() {
        return INSTANCE;
    }

    
    private NullSectionData() {
        super();
    }

    
    @Override
    public void addOption(String name, OptionData option) {
        throw new InvalidOperationException();
    }

}
