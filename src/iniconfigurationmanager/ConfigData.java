
package iniconfigurationmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigData {

    private ConfigSchema schema;

    private Map< String, ConfigSection > sections;

    
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
        this.sections = new LinkedHashMap< String, ConfigSection >();
    }


    public void setSchema( ConfigSchema schema ) {
        this.schema = schema;
    }


    public ConfigSchema getSchema() {
        return schema;
    }
    

    public void addSection ( String name, ConfigSection section ) {
        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }

    
    public ConfigSection getSection( String name ) {
        return sections.get( name );
    }
}
