
package iniconfigurationmanager;

import iniconfigurationmanager.options.BooleanOptionSchema;
import iniconfigurationmanager.options.SignedOptionSchema;
import iniconfigurationmanager.options.StringOptionSchema;
import iniconfigurationmanager.parsing.ConfigParserException;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.ConfigurationSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionSchema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConfigParserException {
        OptionSchema id = new BooleanOptionSchema()
            .setRequired()
            .setComment("id");

        OptionSchema name = new StringOptionSchema()
            .setRequired()
            .setComment("name");


        SectionSchema section = new SectionSchema()
            .setReguired()
            .addOption( "id", id )
            .addOption( "name", name );

        ConfigurationSchema schema = new ConfigurationSchema()
            .addSection( "section", section );

        String input = "[section]\nid=false\nname=karel,${section#id}";

        ConfigurationData data = ConfigurationData.loadFromString( schema, input );

        
        System.out.println( data.toString(false) );
    }

}