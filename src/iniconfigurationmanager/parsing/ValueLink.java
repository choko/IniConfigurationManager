package iniconfigurationmanager.parsing;

import iniconfigurationmanager.schema.LinkVisitor;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.SchemaError;
import java.util.List;

/**
 * ValueLink represents link to the another options values.
 */
public class ValueLink {

    private String sectionName;

    private String optionName;

    private ConfigurationData configuration;


    public ValueLink( String link, ConfigurationData configuration ) {
        this.sectionName = getSectionName( link );
        this.optionName = getOptionName( link );
        this.configuration = configuration;
    }


    /**
     * Retrieves a section name from the given link.
     *
     * @param String link
     * @return String section name
     */
    private String getSectionName( String link ) {
        return link.substring( 2, link.indexOf( "#" ) );
    }


    /**
     * Retrieves a option name from the given link.
     * 
     * @param String link
     * @return String option name
     */
    private String getOptionName( String link ) {
        return link.substring( link.indexOf( "#" ) + 1, link.length() - 1 );
    }


    /**
     * Retrieves values from the linked option
     * 
     * @param <T> a type to which the values has to be casted
     * @param type instance of the type
     * @return List<T> list of values
     */
    public <T> List<T> getValues( T type ) {
        LinkVisitor<T> visitor = new LinkVisitor<T>();
        getLinkedOption().accept( visitor );

        return visitor.getValues();
    }


    /**
     * Returns linked section's data
     *
     * @return SectionData linked section's data
     */
    public SectionData getLinkedSection() {
        if ( configuration.hasSection( sectionName ) ) {
            return configuration.getSection( sectionName );
        } else {
            throw new IllegalStateException( String.format(
                    SchemaError.NULL_SECTION_DATA.getMessage(), sectionName ) );
        }
    }


    /**
     * Returns linked option's data
     * 
     * @return OptionData linked option's data
     */
    public OptionData getLinkedOption() {
        SectionData section = getLinkedSection();

        if ( section.hasOption( optionName ) ) {
            return section.getOption( optionName );
        } else {
            throw new IllegalStateException( String.format(
                    SchemaError.NULL_OPTION_DATA.getMessage(), optionName ) );
        }
    }


    @Override
    public String toString() {
        return String.format( Format.LINK_FORMAT,
                getLinkedOption().getCanonicalName() );
    }
}
