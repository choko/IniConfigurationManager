
package iniconfigurationmanager.utils;

import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionData;
import iniconfigurationmanager.schema.SectionSchema;
import iniconfigurationmanager.schema.StructureVisitor;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigPrinterVisitor implements StructureVisitor {

    private StringBuilder sb;

    private boolean printDefaults;

    public ConfigPrinterVisitor() {
        this.printDefaults = false;
    }


    public void setPrintDefaults( boolean printDefaults ) {
        this.printDefaults = printDefaults;
    }
    

    public void visit(OptionData option) {
        if( ! this.printDefaults && option.hasOnlyDefaultValues() ) {
            return;
        }

        this.sb.append( option.toString() );
    }


    public void visit(OptionSchema option) {
        return;
    }


    public void visit(SectionData section) {
        this.sb.append( section.toString() );
    }


    public void visit(SectionSchema section) {
        return;
    }

    
    public void visit(ConfigurationData data) {
        this.sb = new StringBuilder();
    }


    public String print() {
        return this.sb.toString();
    }

}
