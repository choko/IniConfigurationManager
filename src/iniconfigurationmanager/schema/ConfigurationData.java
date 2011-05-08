package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.ConfigParser;
import iniconfigurationmanager.parsing.ConfigParserException;
import iniconfigurationmanager.utils.InvalidOperationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ConfigurationData represents configuration data structure.
 */
public class ConfigurationData
        implements Iterable<SectionData> {

    private ConfigurationSchema schema;

    private Map<String, SectionData> sections;


    /**
     * Loads configuration from the string by parsing it against the schema.
     *
     * @param schema
     * @param string input string
     * @return configuration data
     * @throws ConfigParserException whether the error occured during parsing
     */
    public static ConfigurationData loadFromString( ConfigurationSchema schema,
            String string )
            throws ConfigParserException {
        return getReader( schema ).readFromString( string );
    }


    /**
     * Loads configuration from the file by parsing it against the schema.
     *
     * @param
     * @param
     * @return configuration data
     * @throws FileNotFoundException whether the file doesn't exist
     * @throws ConfigParserException whether the error occured during parsing
     */
    public static ConfigurationData loadFromFile( ConfigurationSchema schema,
            File file )
            throws FileNotFoundException, ConfigParserException {
        return getReader( schema ).readFromFile( file );
    }


    /**
     * Loads configuration from the input stream by parsing it against the
     * schema.
     *
     * @param schema
     * @param string input string
     * @return configuration data
     * @throws ConfigParserException whether the error occured during parsing
     */
    public static ConfigurationData loadFromInputStream(
            ConfigurationSchema schema, InputStream stream )
            throws ConfigParserException {
        return getReader( schema ).readFromInputStream( stream );
    }


    /**
     * Returns reader for the configuration schema
     *
     * @param schema
     * @return
     */
    private static ConfigReader getReader( ConfigurationSchema schema ) {
        ConfigParser parser = new ConfigParser(
                schema, new ConfigurationData() );

        return new ConfigReader( parser );
    }


    private ConfigurationData() {
        this.sections = new LinkedHashMap<String, SectionData>();
    }


    /**
     * Saves configuration data to the file
     *
     * @param path output file path
     * @param
     * @throws IOException
     */
    public void saveToFile( String path, boolean printDefaults )
            throws IOException {
        ConfigWriter.writeToFile( new File( path ), this, printDefaults );
    }


    /**
     * Saves configuration data to the output stream
     *
     * @param stream
     * @param
     * @throws IOException
     */
    public void saveToOuputStream( OutputStream stream, boolean printDefaults )
            throws IOException {
        ConfigWriter.writeToOutputStream( stream, this, printDefaults );
    }


    /**
     * Set schema for this instance. Schema can be set only once. It is set
     * during parsing.
     *
     * @param schema
     * @return
     * @throws InvalidOperationException whether the schema is already set
     */
    public ConfigurationData setSchema( ConfigurationSchema schema ) {
        if ( this.schema != null ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.NULL_SCHEMA.getMessage() ) );
        }

        this.schema = schema;

        return this;
    }


    /**
     * Returns configuration schema of this instance.
     * 
     * @return
     */
    public ConfigurationSchema getSchema() {
        return schema;
    }


    /**
     * Adds section to the sections list.
     * 
     * @param name section name
     * @param section
     * @return this instance for fluent interface
     * @throws InvalidOperationException whether the section already exists
     * @throws IllegalArgumentException whether the section is null
     */
    public ConfigurationData addSection( String name, SectionData section ) {
        if ( hasSection( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_SECTION_DATA.getMessage(), name ) );
        }

        if ( section == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_SECTION_DATA.getMessage(), name ) );
        }

        section.setName( name ).setConfiguration( this );

        sections.put( name, section );

        return this;
    }


    /**
     * Determines whether a section with the name exists.
     *
     * @param name section name
     * @return
     */
    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    /**
     * Return section with the given name.
     * 
     * @param name section name
     * @return
     * @throws IllegalArgumentException whether section doesn't exist
     */
    public SectionData getSection( String name ) {
        if ( !hasSection( name ) ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.UNDEFINED_SECTION_DATA.getMessage(), name ) );
        }

        return sections.get( name );
    }


    /**
     * Removes section from the sections list.
     *
     * @param name section name
     * @return this instance for fluent interface
     */
    public ConfigurationData removeSection( String name ) {
        sections.remove( name );

        return this;
    }


    /**
     * Prints this instance into a string by using ConfigPrinterVisitor.
     * 
     * @param printDefaults
     * @return
     */
    public String toString( boolean printDefaults ) {
        ConfigPrinterVisitor visitor = new ConfigPrinterVisitor();
        visitor.setPrintDefaults( printDefaults );
        this.accept( visitor );

        return visitor.print();
    }


    /**
     * Returns iterator for iterating over section's datas.
     *
     * @return<SectionData>
     */
    public Iterator<SectionData> iterator() {
        return sections.values().iterator();
    }


    /**
     * Accepts visitors implementing StructureVisitor pattern.
     *
     * @param visitor
     * @return this instance for fluent interface
     */
    public ConfigurationData accept( StructureVisitor visitor ) {
        schema.accept( visitor );
        visitor.visit( this );

        for ( SectionData section : sections.values() ) {
            section.accept( visitor );
        }

        return this;
    }
}
