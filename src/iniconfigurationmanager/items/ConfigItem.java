
package iniconfigurationmanager.items;


import iniconfigurationmanager.ConfigLine;
import iniconfigurationmanager.ConfigParser;
import iniconfigurationmanager.ValueLink;
import iniconfigurationmanager.LinkVisitor;
import iniconfigurationmanager.RawValue;
import iniconfigurationmanager.ValidatorVisitor;
import iniconfigurationmanager.rules.ValidationRule;
import java.util.LinkedList;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public abstract class ConfigItem {

    protected String name;

    protected boolean required;

    protected List< Object > values;

    protected List< ValidationRule > validationRules;


    protected ConfigItem() {
        this.required = false;
        this.values = new LinkedList< Object >();
        this.validationRules = new LinkedList< ValidationRule >();
    }
    
    
    public ConfigItem( String name ) {
        this();
        this.name = name;
    }

    
    public void setRequired() {
        this.required = true;
    }


    public boolean isRequired() {
        return this.required;
    }


    public void clear() {
        values.clear();
    }


    public void addValue( ValueLink link ) {
        values.add( link );
    }


    public void addValue( RawValue value ) {
        values.add( parseValue( value ) );
    }
    

    public void addValue( Object value ) {
        //@TODO cast check
        values.add( getValueClass().cast( value ) );
    }

    
    public void setValue( Object value ) {
        clear();
        addValue( value.getClass().cast( value ) );
    }


    public void setValues( List< Object > values ) {
        clear();
        for( Object value : values ) {
            addValue( value.getClass().cast( value ) );
        }
    }


    public Object getValue() {
        return values.iterator().next();
    }


    public < T > T getValue( T type ) {
        return (T) getValue();
    }


    public List< Object > getValues() {
        return getValues( new Object() );
    }

    
    public < T > List< T > getValues( T type ) {
        if( ! type.getClass().isAssignableFrom( getValueClass() ) ) {
            throw new ClassCastException();
        }

        List< T > valuesList = new LinkedList< T >();
        for( Object value : values ) {
            if( value instanceof ValueLink ) {
                ValueLink link = (ValueLink) value;
                valuesList.addAll( link.getValues( type ) );
            } else {
                valuesList.add( (T) value );
            }
        }

        return valuesList;
    }


    public void addValidationRule( ValidationRule rule ) {
        validationRules.add( rule );
    }


    public List< ValidationRule > getValidationRules() {
        return validationRules;
    }
    
    
    public ValueLink getLink() {
        throw new NotImplementedException();
    }


    public void accept( ValidatorVisitor visitor ) {
        visitor.visit( this );
    }


    public void accept( LinkVisitor visitor ) {
        visitor.enter( this );

        for( Object value : values ) {
            if( value instanceof ValueLink ) {
                ValueLink link = (ValueLink) value;
                link.getLinkedItem().accept( visitor );
            } else {
                visitor.visit( value );
            }
        }

        visitor.leave( this );
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( name );
        sb.append( ConfigParser.WHITESPACE );
        sb.append( ConfigLine.EQUALS_SIGN );
        sb.append( ConfigParser.WHITESPACE );

        for( Object value : getValues() ) {
            sb.append( valueToString( value ) );
            sb.append( "," );
        }

        sb.deleteCharAt( sb.length() );
        sb.append( ConfigParser.NEWLINE );

        return sb.toString();     
    }


    public ConfigItem copy() {
        try {
            ConfigItem item = this.getClass().newInstance();
            item.name = this.name;
            item.required = this.required;

            for( ValidationRule rule : validationRules ) {
                item.addValidationRule( rule );
            }
            
            return item;
        } catch( InstantiationException e ) {
            throw new InternalError( e.getMessage() );
        } catch( IllegalAccessException e ) {
            throw new InternalError( e.getMessage() );
        }
    }

    
    protected abstract Class getValueClass();


    protected abstract Object parseValue( RawValue value );

    
    protected abstract String valueToString( Object value );

}
