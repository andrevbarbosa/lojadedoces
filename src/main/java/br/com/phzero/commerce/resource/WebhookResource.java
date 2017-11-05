package br.com.phzero.commerce.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookResource {

	private String date;
	private String env;
	private String event;
	private PaymentResource resource;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public PaymentResource getResource() {
		return resource;
	}
	public void setResource(PaymentResource resource) {
		this.resource = resource;
	}
	
	
		
		
}
