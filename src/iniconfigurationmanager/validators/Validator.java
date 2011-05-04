/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.validators;

import iniconfigurationmanager.validators.ValidatorResult;
import iniconfigurationmanager.ConfigData;
import java.util.HashSet;
import iniconfigurationmanager.ConfigSection;
/**
 *
 * @author KlonK
 */
public abstract class Validator {

    private ValidatorResult result;

    private HashSet<ConfigSection> enteredConfigSection;

    public abstract ValidatorResult visit(ConfigData data);

    private void addSectionToHash(ConfigSection section) {
        enteredConfigSection.add(section);
    }


}
