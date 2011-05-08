package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 * This class provides concatenation of <code>UnsignedOptionSchema</code> and
 * <code>OptionData</code>
 */
public class UnsignedOptionSchema
        extends OptionSchema {

    /**
    * This method returns newed created <code>UnsignedOptionData</code>
    */
    @Override
    public OptionData getOptionData() {
        return new UnsignedOptionData();
    }
}
