/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.items;

import iniconfigurationmanager.ConfigLine;
import iniconfigurationmanager.ConfigParser;
import iniconfigurationmanager.ConfigVisitor;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author KlonK <jurko@bdi.sk>
 */
public class BooleanConfigItem extends ConfigItem {

    private List< Boolean > values;


    public BooleanConfigItem( String name ) {
        super( name );
        this.values = new LinkedList< Boolean >();
    }

    @Override
    public void setValues( List< String > values ) {

    }

    @Override
    public void accept(ConfigVisitor visitor){
        visitor.visit(this);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( name );
        sb.append( ConfigParser.WHITESPACE );
        sb.append( ConfigLine.EQUALS_SIGN );
        sb.append( ConfigParser.WHITESPACE );

        for( Boolean value : values ) {
            sb.append( value );
            sb.append( ":" );
        }

        sb.deleteCharAt( sb.length() - 1 );
        sb.append( ConfigParser.NEWLINE );

        return sb.toString();
    }

}
