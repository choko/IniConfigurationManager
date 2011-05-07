
package iniconfigurationmanager.schema;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public enum SchemaError {

    NULL_SCHEMA ( "Configuration schema cannot be null." ),
    NULL_SECTION_DATA( "Section data %s cannot be null." ),
    NULL_SECTION_SCHEMA( "Section schema %s cannot be null." ),
    NULL_OPTION_DATA( "Option data %s cannot be null." ),
    NULL_OPTION_SCHEMA( "Option schema %s cannot be null." ),
    DUPLICIT_SECTION_DATA ( "Section data %s already exists." ),
    DUPLICIT_SECTION_SCHEMA ( "Section schmea %s already exists." ),
    DUPLICIT_OPTION_DATA ( "Option data %s already exists." ),
    DUPLICIT_OPTION_SCHEMA ( "Option schema %s already exists." ),
    UNALLOWED_ADDING_OPTION ( "Option cannot be added to undefined section." );


    private final String message;

    private SchemaError( String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }

}
