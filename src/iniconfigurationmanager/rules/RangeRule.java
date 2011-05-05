/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.SignedConfigItem;
import iniconfigurationmanager.schema.ConfigItemData;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class RangeRule implements ValidationRule {

    private int from;

    private int to;

    public RangeRule( int from,int to ){
        this.from = from;
        this.to = to;
    }

    public RangeRule( int to ){
        this.from = Integer.MIN_VALUE;
        this.to = to;
    }

    public RangeRule(List<ConfigItemData> items){

    }

    public boolean isAplicableOn(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String validate(ConfigItemData item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}
