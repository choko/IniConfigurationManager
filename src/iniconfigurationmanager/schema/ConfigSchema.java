
package iniconfigurationmanager.schema;

import iniconfigurationmanager.schema.ConfigSectionData;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ondrej Klejch <ondrej.klejch@gmail.com>
 */
public class ConfigSchema implements Iterable< ConfigSectionData > {

    private Map< String, ConfigSectionData > sections;

    public ConfigSchema() {
        this.sections = new LinkedHashMap< String, ConfigSectionData >();
    }

    public void addSection ( String name, ConfigSectionData section ) {
        sections.put(name, section);
    }


    public boolean hasSection( String name ) {
        return sections.containsKey( name );
    }


    public ConfigSectionData getSection( String name ) {
        return sections.get( name );
    }

    public Iterator<ConfigSectionData> iterator() {
        return sections.values().iterator();
    }

}