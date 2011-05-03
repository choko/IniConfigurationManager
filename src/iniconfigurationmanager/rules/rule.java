/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.ConfigItem;

/**
 *
 * @author KlonK
 */
interface rule {

    public abstract Boolean validate(ConfigItem item);

}
