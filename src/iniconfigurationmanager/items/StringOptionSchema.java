
package iniconfigurationmanager.items;

import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringOptionSchema extends ConfigItemSchema {

    @Override
    protected ConfigItemData getItemData() {
        return new StringOptionData();
    }

}
