
package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringOptionSchema extends OptionSchema {

    @Override
    public OptionData getOptionData() {
        return new StringOptionData();
    }

}
