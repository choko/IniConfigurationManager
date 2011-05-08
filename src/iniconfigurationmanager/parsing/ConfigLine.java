package iniconfigurationmanager.parsing;

/**
 * ConfigLine class represents line in a configuration input.
 */
public class ConfigLine {

    private String text;


    public ConfigLine( String text ) {
        this.text = text;
    }

    /**
     * Returns text of the line
     *
     * @return String text of the line
     */
    public String getText() {
        return text;
    }


    /**
     *  Determines whether this line is section header
     *
     * @return boolean
     */
    public boolean isSectionHeader() {
        return text.startsWith( Format.SECTION_DEFINITION_START );
    }


    /**
     * Determines whether this line is comment
     *
     * @return boolean
     */
    public boolean isComment() {
        String commentStart = "" + Format.COMMENT_START;
        return text.trim().startsWith( commentStart );
    }


    /**
     * Determines whether this line is option definition
     *
     * @return boolean
     */
    public boolean isOptionDefinition() {
        return text.indexOf( Format.EQUALS_SIGN ) != -1;
    }


    /**
     * Determines whether this line is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return text.trim().isEmpty();
    }
}
