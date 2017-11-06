package br.com.phzero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.phzero.commerce.model.Pagamento;
import br.com.phzero.commerce.model.Pedido;
import br.com.phzero.commerce.service.CheckoutService;
import br.com.phzero.commerce.service.PagamentoService;
import br.com.phzero.commerce.service.PedidoService;

@Controller
@RequestMapping(path="/checkout")
public class CheckoutController {
	
	@Autowired
	private CheckoutService service;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addPaymentData(@RequestParam(value="pedidoId", required=true) Integer pedidoId,
			@RequestParam(value="pagamentoId", required=true) Integer pagamentoId) {
		
		Pedido pedido = pedidoService.buscarPedido(pedidoId).get();
		pedido = service.criarPedido(pedido);
		pedidoService.atualizarPedido(pedido);
		
		Pagamento pagamento = pagamentoService.buscarPagamento(pagamentoId).get();
		pagamento = service.criarPagamento(pagamento);
		pagamento = pagamentoService.atualizarPagamento(pagamento);
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	@ResponseBody
	public String verify(@RequestParam(value="pagamentoId", required=true) Integer pagamentoId) {
		
		Pagamento pagamento = pagamentoService.buscarPagamento(pagamentoId).get();
		
		return "CREATED".equals(pagamento.getStatus())?"GERADO":
				"IN_ANALYSIS".equals(pagamento.getStatus())?"EM ANALISE":
			"WAITING".equals(pagamento.getStatus())?"AGUARDANDO":
				"AUTHORIZED".equals(pagamento.getStatus())?"AUTORIZADO - " + pagamento.getMoipId():
					pagamento.getStatus();
	}
}












