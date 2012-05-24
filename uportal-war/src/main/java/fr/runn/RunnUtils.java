package fr.runn;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class RunnUtils {

	// affiliation can be like employee@unicaen.fr;member@unicaen.fr or simply member@univ-rouen.fr
	static public String getAffiliationSuffixFromHeaderAttributes(
			final HttpServletRequest httpRequest, Map<String, List<Object>> attributes) {
		
		String univKey = null;
		String affiliation = null;
			
		if(attributes == null)
			affiliation = httpRequest.getHeader("affiliation");
		else if(attributes.get("affiliation")!= null && !attributes.get("affiliation").isEmpty())
			affiliation = (String)attributes.get("affiliation").get(0);
		
		
		if (affiliation != null && !affiliation.isEmpty()) {

			// goal is to put unicaen.fr for example like an
			// uPortalTemplateUserName
			Pattern p = Pattern.compile("(.*)@(.*)");
			Matcher m = p.matcher(affiliation);
			if (m.find() == true)
				univKey = m.group(2);
		}
		return univKey;
	}
	
}
