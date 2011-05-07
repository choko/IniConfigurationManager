/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.items;

import iniconfigurationmanager.schema.ConfigItemData;
import iniconfigurationmanager.schema.ConfigItemSchema;

/**
 *
 * @author KlonK
 */
public class BooleanOptionSchema extends ConfigItemSchema {

    @Override
    public ConfigItemData getItemData() {
        return new BooleanOptionData();
    }

}
