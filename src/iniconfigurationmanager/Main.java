package iniconfigurationmanager;

import iniconfigurationmanager.options.StringOptionSchema;
import iniconfigurationmanager.options.UnsignedOptionSchema;
import iniconfigurationmanager.parsing.ConfigParserException;
import iniconfigurationmanager.rules.CountRule;
import iniconfigurationmanager.rules.EnumRule;
import iniconfigurationmanager.rules.OneValueRule;
import iniconfigurationmanager.rules.RangeRule;
import iniconfigurationmanager.schema.ConfigurationData;
import iniconfigurationmanager.schema.ConfigurationSchema;
import iniconfigurationmanager.schema.OptionData;
import iniconfigurationmanager.schema.OptionSchema;
import iniconfigurationmanager.schema.SectionSchema;
import iniconfigurationmanager.validators.ValidationResult;
import iniconfigurationmanager.validators.Validator;
import java.util.LinkedList;
import java.util.List;

/**
 * An example how to use IniConfigurationManager
 */
public class Main {

    public static void main( String[] args )
            throws ConfigParserException {
<<<<<<< HEAD
        OptionSchema id = new SignedOptionSchema().setRequired().setComment(
                "id" );
=======
>>>>>>> f5b7fba034568cd840bcd3d97eadba20ff28b6a0

        List<Object> allowedNames = new LinkedList<Object>();
        allowedNames.add("Petra");
        allowedNames.add("Jana");

        OptionSchema name = new StringOptionSchema()
                .setRequired()
                .setComment( "Name of the person" )
                .addValidationRule( new OneValueRule() )
                .addValidationRule( new EnumRule( allowedNames ));

        OptionSchema surname = new StringOptionSchema()
                .setRequired()
                .setComment( "Surname of the person" )
                .addValidationRule( new CountRule( 2 ) );

        OptionSchema age = new UnsignedOptionSchema()
                .setRequired()
                .setComment( "Age of the person" )
                .addValidationRule( new RangeRule( 18, 70 ));

        SectionSchema personSection = new SectionSchema()
                .setReguired()
                .addOption( "name", name )
                .addOption( "surname", surname )
                .addOption( "age", age);

<<<<<<< HEAD
        String input = "[section]\nid=0b01101001\nname=karel,${section#id}";
=======
        ConfigurationSchema schema = new ConfigurationSchema()
                .addSection( "person", personSection );
>>>>>>> f5b7fba034568cd840bcd3d97eadba20ff28b6a0


        String input = "[person]\nname=Karla,Petra\nsurname=${person#name},Machova\nage=010000000001";
        ConfigurationData data = ConfigurationData.loadFromString( schema, input );

        OptionData personName = data.getSection( "person" ).getOption( "name" );
        System.out.println( (String) personName.getValue() );
        System.out.println( personName.getValue( "" ) );


        OptionData personSurname = data.getSection( "person" ).getOption( "surname" );
        for( Object currentSurname : personSurname.getValues() ) {
            System.out.println( (String) currentSurname );
        }
        for( String currentSurname : personSurname.getValues( "" ) ) {
            System.out.println( currentSurname );
        }

        
        OptionData personAge = data.getSection(  "person" ).getOption( "age" );
        personAge.setValue( 0 );
        
        System.out.println( data );


        ValidationResult structureValidation = Validator.validateStrict( data );
        if( structureValidation.isOk() ) {
            System.out.println( "Configuration data's strucuture is valid " );
        }


        ValidationResult rulesValidation = Validator.validateValidationRules( data );
        if( ! rulesValidation.isOk() ) {
            for( String error : rulesValidation.getErrorMessages() ) {
                System.out.println( error );
            }
        }
    }
}
