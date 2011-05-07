/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.options;

import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;

/**
 *
 * @author KlonK
 */
public class UnsignedOptionSchema extends OptionSchema{

    @Override
    public OptionData getOptionData() {
       return new UnsignedOptionData();
    }

}
