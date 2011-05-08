package iniconfigurationmanager.parsing;

/**
 * RawValue represents raw value retrieved from the configuration input.
 */
public class RawValue {

    private String value;


    public RawValue( String value ) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
