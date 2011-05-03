/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.rules;

import iniconfigurationmanager.items.SignedConfigItem;
import iniconfigurationmanager.items.ConfigItem;
import java.util.List;

/**
 *
 * @author KlonK
 */
public class rangeRule extends  rule {

    private int from;

    private int to;

    public rangeRule( int from,int to ){
        this.from = from;
        this.to = to;
    }

    public rangeRule( int to ){
        this.from = Integer.MIN_VALUE;
        this.to = to;
    }

    public rangeRule(List<ConfigItem> items){

    }


    @Override
    public boolean validate(SignedConfigItem item){
        return false;
    }



}
