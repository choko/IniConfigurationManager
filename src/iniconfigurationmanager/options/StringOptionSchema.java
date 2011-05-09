package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 * This class provides concatenation of <code>StringOptionSchema</code> and
 * <code>OptionData</code>
 */
public class StringOptionSchema
        extends OptionSchema {

    /**
    * This method returns newed created <code>StringOptionData</code>
     *
     * @return StringOptionData
    */
    @Override
    public OptionData getOptionData() {
        return new StringOptionData();
    }
}
