package iniconfigurationmanager.schema;

import iniconfigurationmanager.utils.InvalidOperationException;

/**
 * NullSectionData implements Null object pattern. It is used for handling
 * special cases during the parsing of a configuration input. Specially when the
 * is no defined section schema.
 */
public class NullSectionData
        extends SectionData {

    private static final NullSectionData INSTANCE = new NullSectionData();


    public static NullSectionData getInstance() {
        return INSTANCE;
    }


    private NullSectionData() {
        super();
    }


    @Override
    public SectionData addOption( String name, OptionData option ) {
        throw new InvalidOperationException( String.format(
                SchemaError.UNALLOWED_ADDING_OPTION.getMessage() ) );
    }
}
