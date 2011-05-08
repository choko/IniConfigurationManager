package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 * This class provides concatenation of <code>SignedOptionData</code> and
 * <code>OptionData</code>
 */
public class SignedOptionSchema
        extends OptionSchema {

    /**
    * This method returns newed created <code>FloatOptionData</code>
    */
    @Override
    public OptionData getOptionData() {
        return new SignedOptionData();
    }
}
