
package iniconfigurationmanager.utils;

import iniconfigurationmanager.schema.ConfigurationData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigWriter {

    public static void writeToFile( File file, ConfigurationData data,
            boolean printDefaults ) throws IOException {
        write( new FileWriter( file ), data, printDefaults );
    }

    public static void writeToOutputStream( OutputStream stream,
            ConfigurationData data, boolean printDefaults ) throws IOException {
        write( new OutputStreamWriter( stream ), data, printDefaults );
    }

    private static void write( Writer writer, ConfigurationData data,
            boolean printDefaults ) throws IOException {
        writer.write( data.toString( printDefaults ) );
        writer.close();
    }

}
