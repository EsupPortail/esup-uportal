package fr.runn.auth.cas;

import java.util.List;

import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

public class RunnTicketValidationFiltersWrapper {

	private String affiliationSuffix;
	
	private List<String> casAddrs;
	
	private Cas20ProxyReceivingTicketValidationFilter ticketValidationFilter;
	
	private String casLoginUrl;
	
	private String casLogoutUrl;

	public String getAffiliationSuffix() {
		return affiliationSuffix;
	}

	public void setAffiliationSuffix(String affiliationSuffix) {
		this.affiliationSuffix = affiliationSuffix;
	}

	public List<String> getCasAddrs() {
		return casAddrs;
	}

	public void setCasAddrs(List<String> casAddrs) {
		this.casAddrs = casAddrs;
	}

	public Cas20ProxyReceivingTicketValidationFilter getTicketValidationFilter() {
		return ticketValidationFilter;
	}

	public void setTicketValidationFilter(
			Cas20ProxyReceivingTicketValidationFilter ticketValidationFilter) {
		this.ticketValidationFilter = ticketValidationFilter;
	}

	public String getCasLoginUrl() {
		return casLoginUrl;
	}

	public void setCasLoginUrl(String casLoginUrl) {
		this.casLoginUrl = casLoginUrl;
	}

	public String getCasLogoutUrl() {
		return casLogoutUrl;
	}

	public void setCasLogoutUrl(String casLogoutUrl) {
		this.casLogoutUrl = casLogoutUrl;
	}
	
}
