
package iniconfigurationmanager.schema;

import iniconfigurationmanager.utils.InvalidOperationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class SectionSchema implements Iterable< OptionSchema > {

    private String name;

    private Boolean required;

    private Map< String, OptionSchema > options;


    public SectionSchema() {
        this.options = new LinkedHashMap<String, OptionSchema>();
        this.required = false;
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


    public boolean isRequired() {
        return this.required;
    }


    public SectionSchema addOption( String name, OptionSchema option ) {
        if( hasOption( name ) ) {
            throw new InvalidOperationException();
        }

        if( option == null ) {
            throw new IllegalArgumentException();
        }

        option.setName( name )
            .setSectionName( this.name );

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
        for( OptionSchema option : options.values() ) {
            option.accept( visitor );
        }

        visitor.visit( this );
    }

}
