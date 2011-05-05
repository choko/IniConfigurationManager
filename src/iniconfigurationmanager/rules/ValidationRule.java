/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.schema.ConfigItemData;

/**
 *
 * @author KlonK
 */
public interface ValidationRule {

    public boolean isAplicableOn( ConfigItemData item );
    
    public String validate( ConfigItemData item );

}
