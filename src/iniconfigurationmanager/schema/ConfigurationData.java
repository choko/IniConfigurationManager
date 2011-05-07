
package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.ConfigParser;
import iniconfigurationmanager.ConfigReader;
import iniconfigurationmanager.ConfigWriter;
import iniconfigurationmanager.parsing.ConfigParserException;
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


    public void saveToFile(String path) throws IOException {
        ConfigWriter.writeToFile( new File( path ), this);
    }


    public void saveToOuputStream(OutputStream stream) throws IOException {
        ConfigWriter.writeToOutputStream( stream , this );
    }
  
    
    public void setSchema( ConfigurationSchema schema ) {
        this.schema = schema;
    }


    public ConfigurationSchema getSchema() {
        return schema;
    }
    

    public void addSection ( String name, SectionData section ) {
        section.setName( name )
            .setConfiguration( this );

        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }

    
    public SectionData getSection( String name ) {
        return sections.get( name );
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for( SectionData section : sections.values() ) {
            sb.append( section.toString() );
        }

        return sb.toString().trim();
    }

    public Iterator<SectionData> iterator() {
        return sections.values().iterator();
    }
    
    public void accept(StructureVisitor visitor) {
        schema.accept( visitor );

        for( SectionData section : sections.values() ) {
            section.accept( visitor );
        }
    }
    
}
