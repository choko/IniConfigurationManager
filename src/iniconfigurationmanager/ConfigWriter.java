
package iniconfigurationmanager;

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

    public static void writeToFile(
        File file, ConfigurationData data
    ) throws IOException {
        write( new FileWriter( file ), data );
    }

    public static void writeToOutputStream( 
        OutputStream stream, ConfigurationData data
    ) throws IOException {
        write( new OutputStreamWriter( stream ), data );
    }

    private static void write(
        Writer writer, ConfigurationData data
    ) throws IOException {
        writer.write( data.toString() );
        writer.close();
    }

}
