
package iniconfigurationmanager;

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
    
    public ConfigData readFromFile( File file ) throws FileNotFoundException {
        return read( new FileReader( file ) );
    }

    public ConfigData readFromInputStream( InputStream stream ) {
        return read ( new InputStreamReader( stream ) );
    }

    public ConfigData readFromString( String string ) {
        return read ( new CharArrayReader ( string.toCharArray() ) );
    }

    private ConfigData read( Reader reader ) {
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
