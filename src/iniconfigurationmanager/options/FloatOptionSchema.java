package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 * This class provides concatenation of <code>FloatOptionSchema</code> and
 * <code>OptionData</code>
 */
public class FloatOptionSchema
        extends OptionSchema {

    /**
    * This method returns newed created <code>FloatOptionData</code>
     *
     * @return FloatOptionData
    */
    @Override
    public OptionData getOptionData() {
        return new FloatOptionData();
    }
}
