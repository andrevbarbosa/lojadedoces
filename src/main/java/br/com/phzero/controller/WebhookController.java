package br.com.phzero.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.phzero.commerce.model.Pagamento;
import br.com.phzero.commerce.resource.WebhookResource;
import br.com.phzero.commerce.service.PagamentoService;


@RestController
@RequestMapping(value = "/webhook/moip")
public class WebhookController {
	
	@Autowired
	private PagamentoService pagamentoService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json")
	@ResponseStatus(OK)
	public void save(@RequestBody final WebhookResource webhook) {
    	
    	System.out.println(webhook.getResource().getPayment());
    	
    	String moipId = webhook.getResource().getPayment().getId();
    	
    	Pagamento pagamento = pagamentoService.buscarPagamentoPorMoipId(moipId).get();
    	
    	if ("WAITING".equals(pagamento.getStatus()) || 
    		"IN_ANALYSIS".equals(pagamento.getStatus()) ||
   			"PRE_AUTHORIZED".equals(pagamento.getStatus()) ||
   			"CREATED".equals(pagamento.getStatus()) ||
   			pagamento.getStatus() == null) {
    		
    		pagamento.setStatus(webhook.getResource().getPayment().getStatus().name());
    		pagamentoService.atualizarPagamento(pagamento);
    	}
    	else {
    		System.out.println("Status inválido!");
    	}

	}

}
