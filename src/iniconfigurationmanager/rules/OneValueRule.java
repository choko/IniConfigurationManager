package iniconfigurationmanager.rules;

/**
 * The <code>ContainRule</code> provides rule OptionData contain
 * only! one element.
 * <p>
 * This rule is applicable on every Data that extends OptionSchema
 */
public class OneValueRule
        extends CountRule {

    public OneValueRule() {
        super( 1 );
    }
    
}
