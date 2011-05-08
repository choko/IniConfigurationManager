package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.Format;
import iniconfigurationmanager.utils.InvalidOperationException;
import iniconfigurationmanager.utils.StringUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SectionData represents section's schema in the configuration data.
 */
public class SectionData
        implements Iterable<OptionData> {

    private String name;

    private String comment;

    private ConfigurationData configuration;

    private Map<String, OptionData> options;


    public SectionData() {
        this.options = new LinkedHashMap<String, OptionData>();
        this.comment = "";
    }


    /**
     * Sets name of this section.
     *
     * @param String name
     * @return SectionData this instance for fluent interface implementation
     */
    protected SectionData setName( String name ) {
        this.name = name;

        return this;
    }


    /**
     * Returns name of this section.
     *
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Sets comment for this section data by merging comment from the section
     * schema and from the configuration input.
     *
     * @param String comment
     * @return SectionData this instance for fluent interface implementation
     */
    public SectionData setComment( String schemaComment, String inputComment ) {
        StringBuilder sb = new StringBuilder();
        sb.append( schemaComment );
        sb.append( Format.NEWLINE );
        sb.append( inputComment );

        this.comment = StringUtils.formatComment( sb.toString() );

        return this;
    }


    /**
     * Determines whether this section has a comment.
     *
     * @return boolean
     */
    public boolean hasComment() {
        return !comment.trim().isEmpty();
    }


    /**
     * Sets configuration that this section belongs to
     * 
     * @param ConfigurationData configuration
     * @return SectionData this instance for fluent interface implementation
     */
    protected SectionData setConfiguration( ConfigurationData configuration ) {
        this.configuration = configuration;

        return this;
    }


    /**
     * Adds option to this options list.
     *
     * @param String name
     * @param String option
     * @return SectionData this instance for fluent interface implementation
     * @throws InvalidOperationException whether the option already exists
     * @throws IllegalArgumentException whether the option is null
     */
    public SectionData addOption( String name, OptionData option ) {
        if ( hasOption( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_OPTION_DATA.getMessage(), name ) );
        }

        if ( option == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_OPTION_DATA.getMessage(), name ) );
        }

        option.setName( name ).setSectionName( this.name ).setConfiguration(
                this.configuration );

        options.put( name, option );

        return this;
    }


    /**
     * Determines whether the option exists.
     *
     * @param String name
     * @return boolean
     */
    public boolean hasOption( String name ) {
        return options.containsKey( name );
    }


    /**
     * Removes option from the options list.
     *
     * @param String name
     * @return SectionData this instance for fluent interface implementation
     */
    public SectionData removeOption( String name ) {
        options.remove( name );

        return this;
    }


    /**
     * Returns option with the name.
     *
     * @param String name
     * @return OptionData
     * @throws IllegalArgumentException whether the option doesn't exist
     */
    public OptionData getOption( String name ) {
        if ( !hasOption( name ) ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.UNDEFINED_OPTION_DATA.getMessage(), name ) );
        }

        return options.get( name );
    }


    /**
     * Returns iterator for iterating over option's schemas
     *
     * @return Iterator<OptionSchema>
     */
    public Iterator<OptionData> iterator() {
        return options.values().iterator();
    }


    /**
     * Accepts visitors implementing StructureVisitor interface.
     *
     * @param visitor
     * @return SectionSchema this instance for fluent interface implementation
     */
    public SectionData accept( StructureVisitor visitor ) {
        visitor.visit( this );

        for ( OptionData option : options.values() ) {
            option.accept( visitor );
        }

        return this;
    }


    /**
     * Prints this section to the string.
     *
     * @return String
     */
    @Override
    public String toString() {
        if ( hasComment() ) {
            return String.format( Format.SECTION_WITH_COMMENT_FORMAT,
                    comment, name );
        } else {
            return String.format( Format.SECTION_FORMAT, name );
        }
    }
}
