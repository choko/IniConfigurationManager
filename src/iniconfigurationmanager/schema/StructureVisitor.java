package iniconfigurationmanager.schema;

/**
 * StructureVisitor is an interface implementing Visitor design pattern for
 * traversing configuration structure.
 */
public interface StructureVisitor {

    public void visit( OptionData option );


    public void visit( OptionSchema option );


    public void visit( SectionData section );


    public void visit( SectionSchema section );


    public void visit( ConfigurationData data );
}
