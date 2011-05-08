package iniconfigurationmanager.schema;

/**
 * SchemaError unites error messages which can occur by bad schema definition.
 */
public enum SchemaError {

    NULL_SCHEMA( "Configuration schema cannot be null." ),
    NULL_SECTION_DATA( "Section data %s cannot be null." ),
    NULL_SECTION_SCHEMA( "Section schema %s cannot be null." ),
    NULL_OPTION_DATA( "Option data %s cannot be null." ),
    NULL_OPTION_SCHEMA( "Option schema %s cannot be null." ),
    NULL_VALIDATION_RULE( "Validation rule cannot be null." ),
    DUPLICIT_SECTION_DATA( "Section data %s already exists." ),
    DUPLICIT_SECTION_SCHEMA( "Section schmea %s already exists." ),
    DUPLICIT_OPTION_DATA( "Option data %s already exists." ),
    DUPLICIT_OPTION_SCHEMA( "Option schema %s already exists." ),
    UNALLOWED_ADDING_OPTION( "Option cannot be added to undefined section." ),
    UNALLOWED_VALIDATION_RULE( "Validation rule is not aplicable here." ),
    UNDEFINED_SECTION_DATA( "Section data %s doesn't exists." ),
    UNDEFINED_SECTION_SCHEMA( "Section schema %s doesn't exists." ),
    UNDEFINED_OPTION_DATA( "Option data %s doesn't exist." ),
    UNDEFINED_OPTION_SCHEMA( "Option schema %s doesn't exist." ),
    CYCLIC_LINK( "Values link contains cyclic dependency." );

    private final String message;


    private SchemaError( String message ) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
