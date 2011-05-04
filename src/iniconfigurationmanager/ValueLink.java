/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ValueLink {


    public ValueLink( String link, ConfigData configuration ) {
        
    }

    public <T> List< T > getValues( T type ) {
        return new LinkedList< T >();
    }
}
