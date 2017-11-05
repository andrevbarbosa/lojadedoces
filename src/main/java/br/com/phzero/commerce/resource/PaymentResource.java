package br.com.phzero.commerce.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.moip.resource.Payment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResource {

	private Payment payment;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
