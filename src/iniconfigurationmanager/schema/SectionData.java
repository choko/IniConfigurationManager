
package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.Format;
import iniconfigurationmanager.utils.InvalidOperationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class SectionData implements Iterable< OptionData > {

    private String name;

    private ConfigurationData configuration;

    private Map< String, OptionData > options;


    public SectionData() {
        this.options = new LinkedHashMap<String, OptionData>();
    }


    protected SectionData setName( String name ) {
        this.name = name;

        return this;
    }
    

    public String getName() {
        return name;
    }


    protected SectionData setConfiguration( ConfigurationData configuration ) {
        this.configuration = configuration;

        return this;
    }


    public SectionData addOption( String name, OptionData option ) {
        if( hasOption( name ) ) {
            throw new InvalidOperationException();
        }

        if( option == null ) {
            throw new IllegalArgumentException();
        }

        option.setName( name )
            .setSectionName( this.name )
            .setConfiguration( this.configuration );

        options.put( name, option );

        return this;
    }
  

    public boolean hasOption( String name ) {
        return options.containsKey( name );
    }


    public SectionData removeOption( String name ) {
        options.remove( name );

        return this;
    }


    public OptionData getOption( String name ) {
        return options.get( name );
    }


    public Iterator<OptionData> iterator() {
        return options.values().iterator();
    }


    public void accept( StructureVisitor visitor ) {
        for( OptionData option : options.values() ) {
            option.accept( visitor );
        }

        visitor.visit( this );
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( Format.SECTION_DEFINITION_START );
        sb.append( name );
        sb.append( Format.SECTION_DEFINITION_END );
        sb.append( Format.NEWLINE );

        return sb.toString();
    }
    
}
