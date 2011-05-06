
package iniconfigurationmanager.schema;


import iniconfigurationmanager.parsing.ValueLink;
import iniconfigurationmanager.LinkVisitor;
import iniconfigurationmanager.parsing.RawValue;
import iniconfigurationmanager.items.ConfigItemFormatDefinition;
import iniconfigurationmanager.parsing.ConfigFormatDefinition;
import iniconfigurationmanager.validators.ValidatorVisitor;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public abstract class ConfigItemData {

    private String name;

    private String sectionName;

    private String comment;

    private ConfigData configuration;

    private ConfigItemFormatDefinition formatDefinition;

    private List< Object > values;

    
    public ConfigItemData() {
        
    }


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


    public void setComment( String schemaComment, String inputComment ) {
        StringBuilder sb = new StringBuilder();
        sb.append( schemaComment );
        sb.append( ConfigFormatDefinition.NEWLINE );
        sb.append( inputComment );

        this.comment = formatComment( sb.toString() );
    }


    private String formatComment( String comment ) {
        StringBuilder sb = new StringBuilder();

        for( char ch : comment.trim().toCharArray() ) {
            sb.append( ch );

            if( ch == ConfigFormatDefinition.NEWLINE.charAt( 0 ) ) {
                sb.append( ConfigFormatDefinition.COMMENT_START );
            } 
        }

        return sb.toString();
    }


    private boolean hasComment() {
        return ! comment.trim().isEmpty();
    }

    
    public String getName() {
        return name;
    }

    
    public String getSectionName() {
        return sectionName;
    }
    

    public String getCanonicalName() {
        return String.format( ConfigFormatDefinition.ITEM_CANONICAL_NAME_FORMAT,
                sectionName, name);
    }


    public ValueLink getItemLink() {
        return new ValueLink( getCanonicalName(), configuration );
    }
    
    public void clear() {
        values.clear();
    }
    

    public void addValue( Object value ) {
        if( value instanceof ValueLink) {
            values.add( (ValueLink) value );
        } else if ( value instanceof RawValue ) {
            values.add( parseValue( (RawValue) value ) );
        } else {
            values.add( getValueClass().cast( value ) );
        }
    }

    
    public void setValue( Object value ) {
        clear();
        addValue( value );
    }


    public void setValues( List< Object > values ) {
        clear();
        for( Object value : values ) {
            addValue( value );
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
        if( ! typeMatches( type ) ) {
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


    private < T > boolean typeMatches( T type ) {
        return type.getClass().isAssignableFrom( getValueClass() );
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
        if( containsOnlyDefaultValues() ) {
            return "";
        }

        if( hasComment() ) {
            return String.format( ConfigFormatDefinition.ITEM_WITH_COMMENT_FORMAT,
                    comment, name, valuesToString() );
        } else {
            return String.format( ConfigFormatDefinition.ITEM_FORMAT,
                name, valuesToString() );
        }
    }


    private String valuesToString() {
        StringBuilder sb = new StringBuilder();
        for( Object value : values ) {
            if( value instanceof ValueLink ) {
                sb.append( ((ValueLink) value).toString() );
            } else {
                sb.append( valueToString( value ) );
            }

            sb.append( ConfigFormatDefinition.VALUES_COMMA_DELIMITER );
        }

        sb.deleteCharAt( sb.length() - 1 );

        return sb.toString();
    }


    private boolean containsOnlyDefaultValues() {
        ConfigSchema schema = configuration.getSchema();
        if( ! schema.hasSection( sectionName ) ) {
            return false;
        }

        ConfigSectionSchema sectionSchema = schema.getSection( sectionName );
        if( ! sectionSchema.hasItem( name ) ) {
            return false;
        }

        ConfigItemSchema itemSchena = sectionSchema.getItem( name );
        return containsSameValues( itemSchena.getDefaultValues(), values );
    }


    private boolean containsSameValues( 
            List defaultValues, List<Object> values
    ) {
        return defaultValues.containsAll( values ) &&
                values.containsAll( defaultValues );
    }


    protected abstract Class getValueClass();

    protected abstract Object parseValue( RawValue value );

    protected abstract String valueToString( Object value );

}