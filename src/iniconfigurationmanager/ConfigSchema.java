
package iniconfigurationmanager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSchema {

    private Map< String, ConfigSection > sections;

    public ConfigSchema() {
        this.sections = new LinkedHashMap< String, ConfigSection >();
    }

    public void addSection ( String name, ConfigSection section ) {
        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    public ConfigSection getSection( String name ) {
        return sections.get( name );
    }

}
