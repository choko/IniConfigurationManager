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
public class SignedConfigItem extends ConfigItem {

    private List< Integer > values;


    public SignedConfigItem( String name ) {
        super( name );
        this.values = new LinkedList< Integer >();
    }

    @Override
    public void setValues( List< String > values ) {
        // Neviem preco je setvalue cez string , nemalo byt to byt genericke ?
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

        for( Integer value : values ) {
            sb.append( value );
            sb.append( ":" );
        }

        sb.deleteCharAt( sb.length() - 1 );
        sb.append( ConfigParser.NEWLINE );

        return sb.toString();
    }

}
