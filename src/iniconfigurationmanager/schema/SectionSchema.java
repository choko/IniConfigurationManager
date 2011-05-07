
package iniconfigurationmanager.schema;

import iniconfigurationmanager.validators.ValidatorVisitor;
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


    public void addOption( String name, OptionSchema option ) {
        option.setName( name )
            .setSectionName( this.name );

        options.put( name, option );
    }


    public boolean hasOption( String name ) {
        return options.containsKey( name );
    }


    public void removeOption( String name ) {
        options.remove( name );
    }


    public OptionSchema getOption( String name ) {
        return options.get( name );
    }


    public Iterator<OptionSchema> iterator() {
        return options.values().iterator();
    }


    public void accept( ValidatorVisitor visitor ) {
        for( OptionSchema option : options.values() ) {
            option.accept( visitor );
        }

        visitor.visit( this );
    }

}
