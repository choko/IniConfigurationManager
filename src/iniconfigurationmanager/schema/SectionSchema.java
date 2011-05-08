package iniconfigurationmanager.schema;

import iniconfigurationmanager.utils.InvalidOperationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class SectionSchema
        implements Iterable<OptionSchema> {

    private String name;

    private String comment;

    private Boolean required;

    private Map<String, OptionSchema> options;


    public SectionSchema() {
        this.options = new LinkedHashMap<String, OptionSchema>();
        this.required = false;
        this.comment = "";
    }


    protected SectionSchema setName( String name ) {
        this.name = name;

        return this;
    }


    public String getName() {
        return name;
    }


    public SectionSchema setReguired() {
        this.required = true;

        return this;
    }


    public SectionSchema setComment( String comment ) {
        this.comment = comment;

        return this;
    }


    public String getComment() {
        return this.comment;
    }


    public boolean isRequired() {
        return this.required;
    }


    public SectionSchema addOption( String name, OptionSchema option ) {
        if ( hasOption( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_OPTION_SCHEMA.getMessage(), name ) );
        }

        if ( option == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_OPTION_SCHEMA.getMessage(), name ) );
        }

        option.setName( name ).setSectionName( this.name );

        options.put( name, option );

        return this;
    }


    public boolean hasOption( String name ) {
        return options.containsKey( name );
    }


    public SectionSchema removeOption( String name ) {
        options.remove( name );

        return this;
    }


    public OptionSchema getOption( String name ) {
        return options.get( name );
    }


    public Iterator<OptionSchema> iterator() {
        return options.values().iterator();
    }


    public void accept( StructureVisitor visitor ) {
        visitor.visit( this );

        for ( OptionSchema option : options.values() ) {
            option.accept( visitor );
        }
    }
}
