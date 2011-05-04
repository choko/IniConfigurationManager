/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iniconfigurationmanager.items;

import iniconfigurationmanager.ConfigLine;
import iniconfigurationmanager.ConfigParser;
import iniconfigurationmanager.RawValue;
import iniconfigurationmanager.ValidatorVisitor;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class StringConfigItem extends ConfigItem {

    private List< String > values;

    
    public StringConfigItem( String name ) {
        super( name );
        this.values = new LinkedList< String >();
    }

    @Override
    public void setValues( List< Object > values ) {
        //this.values = values;
    }

    @Override
    public void accept(ValidatorVisitor visitor){
        visitor.visit(this);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( name );
        sb.append( ConfigParser.WHITESPACE );
        sb.append( ConfigLine.EQUALS_SIGN );
        sb.append( ConfigParser.WHITESPACE );

        for( String value : values ) {
            sb.append( value );
            sb.append( ":" );
        }

        sb.deleteCharAt( sb.length() - 1 );
        sb.append( ConfigParser.NEWLINE );
        
        return sb.toString();
    }

    @Override
    protected Class getValueClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Object parseValue(RawValue value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String valueToString(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
