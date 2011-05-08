package iniconfigurationmanager.schema;

import iniconfigurationmanager.parsing.ConfigLine;
import iniconfigurationmanager.parsing.ConfigParser;
import iniconfigurationmanager.parsing.ConfigParserError;
import iniconfigurationmanager.parsing.ConfigParserException;
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
 * ConfigReader is used to reading configuration input from the file, input
 * stream or string.
 */
public class ConfigReader {

    private ConfigParser parser;


    public ConfigReader( ConfigParser parser ) {
        this.parser = parser;
    }


    /**
     * Reads a configuration data from the file.
     *
     * @param File file input file
     * @return ConfigurationData configuration data
     * @throws FileNotFoundException whether the given file doesn't exist
     * @throws ConfigParserException whether the error occured during parsing
     */
    public ConfigurationData readFromFile( File file )
            throws FileNotFoundException, ConfigParserException {
        return read( new FileReader( file ) );
    }


    /**
     * Reads a configuration data from the input stream.
     * 
     * @param InputStream stream input stream
     * @return ConfigurationData configuration data
     * @throws ConfigParserException whether the error occured during parsing
     */
    public ConfigurationData readFromInputStream( InputStream stream )
            throws ConfigParserException {
        return read( new InputStreamReader( stream ) );
    }


    /**
     * Reads a configuration data from the string
     * 
     * @param String string input string
     * @return ConfigurationData configuration data
     * @throws ConfigParserException whether the error occured during parsing
     */
    public ConfigurationData readFromString( String string )
            throws ConfigParserException {
        return read( new CharArrayReader( string.toCharArray() ) );
    }


    /**
     * Reads all lines from the given reader. Creates list of lines represented
     * by ConfigLine and parse it by using ConfigParser.
     * 
     * @param Reader reader input reader
     * @return ConfigurationData configuration data
     * @throws ConfigParserException whether the error occured during parsing
     */
    private ConfigurationData read( Reader reader )
            throws ConfigParserException {
        BufferedReader bf = new BufferedReader( reader );

        List<ConfigLine> lines = new ArrayList<ConfigLine>();
        try {
            for ( String line = bf.readLine();
                    line != null;
                    line = bf.readLine() ) {
                lines.add( new ConfigLine( line ) );
            }

            bf.close();
        } catch ( IOException e ) {
            throw new ConfigParserException( ConfigParserError.INPUT_ERROR,
                    e.getMessage() );
        }


        return this.parser.parse( lines );
    }
}
