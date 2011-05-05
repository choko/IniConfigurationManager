
package iniconfigurationmanager.schema;

import iniconfigurationmanager.ConfigParser;
import iniconfigurationmanager.ConfigReader;
import iniconfigurationmanager.ConfigWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import iniconfigurationmanager.validators.ValidatorVisitor;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigData implements Iterable< ConfigSectionData > {

    private ConfigSchema schema;

    private Map< String, ConfigSectionData > sections;

    
    public static ConfigData loadFromString(
        ConfigSchema schema,
        String string
    ) {
        return getReader( schema ).readFromString( string );
    }


    public static ConfigData loadFromFile(
        ConfigSchema schema,
        File file
    ) throws FileNotFoundException {
        return getReader( schema ).readFromFile( file );
    }


    public static ConfigData loadFromInputStream(
        ConfigSchema schema,
        InputStream stream
    ) {
        return getReader( schema ).readFromInputStream( stream );
    }

    
    private static ConfigReader getReader( ConfigSchema schema ) {
        ConfigParser parser = new ConfigParser(schema, new ConfigData());

        return new ConfigReader(parser);
    }

    
    private ConfigData() {
        this.sections = new LinkedHashMap< String, ConfigSectionData >();
    }


    public void saveToFile(String path) throws IOException {
        ConfigWriter.writeToFile( new File( path ), this);
    }


    public void saveToOuputStream(OutputStream stream) throws IOException {
        ConfigWriter.writeToOutputStream( stream , this );
    }
  
    
    public void setSchema( ConfigSchema schema ) {
        this.schema = schema;
    }


    public ConfigSchema getSchema() {
        return schema;
    }
    

    public void addSection ( String name, ConfigSectionData section ) {
        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }

    
    public ConfigSectionData getSection( String name ) {
        return sections.get( name );
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for( ConfigSectionData section : sections.values() ) {
            sb.append( section.toString() );
        }

        return sb.toString();
    }

    public Iterator<ConfigSectionData> iterator() {
        return sections.values().iterator();
    }
    
    public void accept(ValidatorVisitor visitor) {
        schema.accept( visitor );

        for( ConfigSectionData section : sections.values() ) {
            section.accept( visitor );
        }
    }
    
}
