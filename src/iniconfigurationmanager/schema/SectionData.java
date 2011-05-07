
package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.Format;
import iniconfigurationmanager.utils.InvalidOperationException;
import iniconfigurationmanager.utils.StringUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class SectionData implements Iterable< OptionData > {

    private String name;

    private String comment;

    private ConfigurationData configuration;

    private Map< String, OptionData > options;


    public SectionData() {
        this.options = new LinkedHashMap<String, OptionData>();
        this.comment = "";
    }


    protected SectionData setName( String name ) {
        this.name = name;

        return this;
    }
    

    public String getName() {
        return name;
    }


    public SectionData setComment( String schemaComment, String inputComment ) {
        StringBuilder sb = new StringBuilder();
        sb.append( schemaComment );
        sb.append( Format.NEWLINE );
        sb.append( inputComment );

        this.comment = StringUtils.formatComment( sb.toString() );

        return this;
    }


    public boolean hasComment() {
        return ! comment.trim().isEmpty();
    }


    protected SectionData setConfiguration( ConfigurationData configuration ) {
        this.configuration = configuration;

        return this;
    }


    public SectionData addOption( String name, OptionData option ) {
        if( hasOption( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_OPTION_DATA.getMessage(), name ) );
        }

        if( option == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_OPTION_DATA.getMessage(), name) );
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
        visitor.visit( this );

        for( OptionData option : options.values() ) {
            option.accept( visitor );
        }
    }
    

    @Override
    public String toString() {
        if( hasComment() ) {
            return String.format( Format.SECTION_WITH_COMMENT_FORMAT,
                    comment, name);
        } else {
            return String.format( Format.SECTION_FORMAT, name);
        }
    }
    
}
