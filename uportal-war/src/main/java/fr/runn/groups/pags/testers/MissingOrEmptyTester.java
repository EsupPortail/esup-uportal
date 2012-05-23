package fr.runn.groups.pags.testers;

import org.jasig.portal.groups.pags.testers.BaseAttributeTester;
import org.jasig.portal.security.IPerson;

/**
 * Tests whether the attribute is null (or empty)
 */
public class MissingOrEmptyTester extends BaseAttributeTester {

    public MissingOrEmptyTester(String attribute, String test) {
        super(attribute, test);
    }

    public boolean test(IPerson person) {
        // Get the list of values for the attribute
        Object[] vals = person.getAttributeValues(getAttributeName());

        // No values, test passed
        if (vals == null || vals.length == 0 || (vals.length == 1 && vals[0].toString().equals(""))) {
            return true;
        } else {
	    return false;
        }
    }
}
