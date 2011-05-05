
package iniconfigurationmanager.schema;


import iniconfigurationmanager.ConfigLine;
import iniconfigurationmanager.ConfigParser;
import iniconfigurationmanager.ValueLink;
import iniconfigurationmanager.LinkVisitor;
import iniconfigurationmanager.RawValue;
import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.validators.ValidatorVisitor;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public final class ConfigItemData {

    private String name;

    private String sectionName;

    private ConfigData configuration;

    private ConfigItemFormatDefinition formatDefinition;

    private List< Object > values;


    public ConfigItemData(
            String name,
            String sectionName,
            ConfigData configuration,
            ConfigItemFormatDefinition formatDefinition
    ) {
        this.name = name;
        this.sectionName = sectionName;
        this.configuration = configuration;
        this.formatDefinition = formatDefinition;
        this.values = new LinkedList< Object >();
    }


    public String getName() {
        return name;
    }

    
    public String getSectionName() {
        return sectionName;
    }
    

    public String getCanonicalName() {
        return String.format("%s#%s", sectionName, name);
    }


    public ValueLink getItemLink() {
        return new ValueLink( getCanonicalName(), configuration );
    }
    
    public void clear() {
        values.clear();
    }


    public void addValue( ValueLink link ) {
        values.add( link );
    }


    public void addValue( RawValue value ) {
        values.add( formatDefinition.parseValue( value ) );
    }
    

    public void addValue( Object value ) {
        //@TODO cast check
        values.add( formatDefinition.getValueClass().cast( value ) );
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
        if( ! type.getClass().isAssignableFrom( formatDefinition.getValueClass() ) ) {
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
            sb.append( formatDefinition.valueToString( value ) );
            sb.append( "," );
        }

        sb.deleteCharAt( sb.length() );
        sb.append( ConfigParser.NEWLINE );

        return sb.toString();     
    }

}
