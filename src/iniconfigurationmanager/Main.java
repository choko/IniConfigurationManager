
package iniconfigurationmanager;

import iniconfigurationmanager.options.StringOptionSchema;
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
    public static void main(String[] args) {
        // TODO code application logic here
          ConfigurationSchema pokus = new ConfigurationSchema();
          SectionSchema po = new  SectionSchema();
          po.addOption("ddd", new  OptionSchema() {

            @Override
            public OptionData getOptionData() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        })
          pokus.addSection("pokus", SectionSchema pokus = new SectionSchema();
    :, null)


    }

}
