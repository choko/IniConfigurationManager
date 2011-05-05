/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.items;

import iniconfigurationmanager.RawValue;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public interface ConfigItemFormatDefinition {

    public Class getValueClass();

    public Object parseValue( RawValue value );

    public String valueToString( Object value );
    
}
