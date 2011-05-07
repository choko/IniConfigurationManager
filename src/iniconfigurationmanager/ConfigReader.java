
package iniconfigurationmanager;

import iniconfigurationmanager.parsing.ConfigParser;
import iniconfigurationmanager.parsing.ConfigParserException;
import iniconfigurationmanager.schema.ConfigurationData;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigReader {

    private ConfigParser parser;

    public ConfigReader( ConfigParser parser ) {
        this.parser = parser;
    }
    
    public ConfigurationData readFromFile( File file )
            throws FileNotFoundException, ConfigParserException {
        return read( new FileReader( file ) );
    }

    public ConfigurationData readFromInputStream( InputStream stream )
            throws ConfigParserException {
        return read ( new InputStreamReader( stream ) );
    }

    public ConfigurationData readFromString( String string )
            throws ConfigParserException {
        return read ( new CharArrayReader ( string.toCharArray() ) );
    }

    private ConfigurationData read( Reader reader ) throws ConfigParserException {
        BufferedReader bf = new BufferedReader(reader);

        List<ConfigLine> lines = new ArrayList<ConfigLine>();
        try {
            for (
                String line = bf.readLine();
                line != null;
                line = bf.readLine()
            ) {
                lines.add( new ConfigLine( line ) );
            }

            bf.close();
        } catch( IOException e ) {
            //@TODO solve problem with exception handling
        }
        

        return this.parser.parse(lines);
    }
    
}
