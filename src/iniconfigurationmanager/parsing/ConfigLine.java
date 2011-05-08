/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iniconfigurationmanager.parsing;

import iniconfigurationmanager.parsing.Format;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigLine {

    private String text;


    public ConfigLine( String text ) {
        this.text = text;
    }


    public String getText() {
        return text;
    }


    public boolean isSectionHeader() {
        return text.startsWith( Format.SECTION_DEFINITION_START );
    }


    public boolean isComment() {
        String commentStart = "" + Format.COMMENT_START;
        return text.trim().startsWith( commentStart );
    }


    public boolean isOptionDefinition() {
        return text.indexOf( Format.EQUALS_SIGN ) != -1;
    }


    public boolean isEmpty() {
        return text.trim().isEmpty();
    }
}
