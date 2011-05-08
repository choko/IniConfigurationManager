
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
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigurationData implements Iterable< SectionData > {

    private ConfigurationSchema schema;

    private Map< String, SectionData > sections;

    
    public static ConfigurationData loadFromString(
        ConfigurationSchema schema,
        String string
    ) throws ConfigParserException {
        return getReader( schema ).readFromString( string );
    }


    public static ConfigurationData loadFromFile(
        ConfigurationSchema schema,
        File file
    ) throws FileNotFoundException, ConfigParserException {
        return getReader( schema ).readFromFile( file );
    }


    public static ConfigurationData loadFromInputStream(
        ConfigurationSchema schema,
        InputStream stream
    ) throws ConfigParserException {
        return getReader( schema ).readFromInputStream( stream );
    }

    
    private static ConfigReader getReader( ConfigurationSchema schema ) {
        ConfigParser parser = new ConfigParser(schema, new ConfigurationData());

        return new ConfigReader(parser);
    }

    
    private ConfigurationData() {
        this.sections = new LinkedHashMap< String, SectionData >();
    }


    public void saveToFile( String path, boolean printDefaults )
            throws IOException {
        ConfigWriter.writeToFile( new File( path ), this, printDefaults );
    }


    public void saveToOuputStream(OutputStream stream, boolean printDefaults)
            throws IOException {
        ConfigWriter.writeToOutputStream( stream , this, printDefaults );
    }
  
    
    public ConfigurationData setSchema( ConfigurationSchema schema ) {
        if( this.schema != null ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.NULL_SCHEMA.getMessage()) );
        }

        this.schema = schema;

        return this;
    }


    public ConfigurationSchema getSchema() {
        return schema;
    }
    

    public ConfigurationData addSection ( String name, SectionData section ) {
        if( hasSection( name ) ) {
            throw new InvalidOperationException( String.format(
                    SchemaError.DUPLICIT_SECTION_DATA.getMessage(), name ) );
        }

        if( section == null ) {
            throw new IllegalArgumentException( String.format(
                    SchemaError.NULL_SECTION_DATA.getMessage(), name ) );
        }

        section.setName( name )
            .setConfiguration( this );

        sections.put(name, section);

        return this;
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }

    
    public SectionData getSection( String name ) {
        return sections.get( name );
    }


    public ConfigurationData removeSection( String name ) {
        sections.remove( name );

        return this;
    }


    public String toString( boolean printDefaults ) {
        ConfigPrinterVisitor visitor = new ConfigPrinterVisitor();
        visitor.setPrintDefaults( printDefaults );
        this.accept( visitor );

        return visitor.print();
    }
    

    public Iterator<SectionData> iterator() {
        return sections.values().iterator();
    }

    
    public void accept(StructureVisitor visitor) {
        schema.accept( visitor );
        visitor.visit( this );

        for( SectionData section : sections.values() ) {
            section.accept( visitor );
        }
    }
    
}
