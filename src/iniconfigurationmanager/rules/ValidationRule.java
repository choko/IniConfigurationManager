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
public interface ValidationRule {

    public boolean isAplicableOn( ConfigItem item );
    
    public String validate( ConfigItem item );

}
