package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 * This class provides concatenation of <code>BooleanOptionSchema</code> and
 * <code>OptionData</code>
 */
public class BooleanOptionSchema
        extends OptionSchema {

    /**
    * This method returns newed created <code>BooleanOptionData</code>
    */
    @Override
    public OptionData getOptionData() {
        return new BooleanOptionData();
    }
}
