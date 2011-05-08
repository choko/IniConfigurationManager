package iniconfigurationmanager.schema;

/**
 * ConfigPrinterVisitor is used for printing configuration data into a string.
 *
 * Implements Visitor design pattern.
 */
public class ConfigPrinterVisitor
        implements StructureVisitor {

    private StringBuilder sb;

    private boolean printDefaults;


    public ConfigPrinterVisitor() {
        this.printDefaults = false;
    }


    /**
     * Sets whether the option with defaults values has to be printed
     *
     * @param boolean printDefaults
     */
    public void setPrintDefaults( boolean printDefaults ) {
        this.printDefaults = printDefaults;
    }


    /**
     * Adds option's definition in a string to the string buffer.
     * 
     * @param OptionData option
     */
    public void visit( OptionData option ) {
        if ( !this.printDefaults && option.hasOnlyDefaultValues() ) {
            return;
        }

        this.sb.append( option.toString() );
    }


    /**
     * Does nothing. It is not necessary when writing a configuration data.
     *
     * @param OptionSchema option
     */
    public void visit( OptionSchema option ) {
        return;
    }


    /**
     * Adds section's header in a string to the string buffer.
     *
     * @param SectionData section
     */
    public void visit( SectionData section ) {
        this.sb.append( section.toString() );
    }


    /**
     * Does nothing. It is not necessary when writing a configuration data.
     *
     * @param SectionSchema section
     */
    public void visit( SectionSchema section ) {
        return;
    }


    /**
     * Initializes string buffer for the given configuration data.
     *
     * @param ConfigurationData data
     */
    public void visit( ConfigurationData data ) {
        this.sb = new StringBuilder();
    }


    /**
     * Returns visited configuration data in a string.
     *
     * @return String
     */
    public String print() {
        return this.sb.toString();
    }
}
