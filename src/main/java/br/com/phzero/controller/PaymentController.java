package br.com.phzero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.phzero.commerce.model.Cart;
import br.com.phzero.commerce.model.Cliente;
import br.com.phzero.commerce.model.Pagamento;
import br.com.phzero.commerce.model.Pedido;
import br.com.phzero.commerce.service.CartService;
import br.com.phzero.commerce.service.ClienteService;
import br.com.phzero.commerce.service.PagamentoService;
import br.com.phzero.commerce.service.PedidoService;

@Controller
@RequestMapping(path="/payment")
public class PaymentController {
	
	@Autowired
	private PagamentoService service;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPaymentData(@RequestParam(value="cartId", required=true) Integer cartId,
			@RequestParam(value="hashCC", required=true) String hashCC,
			@RequestParam(value="parcelas", required=true) Integer parcelas,
			@RequestParam(value="cupom", required=false) String cupom) {
		

		Cart cart = cartService.buscarCart(cartId).get();
		Cliente cliente = clienteService.buscarClientePorEmail("cliente@email.com");
		
		
		// criar e salvar pedido model
		Pedido pedido = new Pedido();
		pedido.setCart(cart);
		pedido.setCupomDesconto(cupom);
		pedido.setStatus("CREATED");
		pedido.setCliente(cliente);
		
		pedido.setDesconto(0);
		if (cupom!=null && cupom.trim().length() > 0) {
			pedido.setDesconto((int)(pedido.getTotal() * 0.05)); //5% desconto com cupom
		} 
		
		// 2,5% acrescimo (após desconto, se houver) em mais de 1 parcela
		pedido.setAcrescimo(0);
		if (parcelas > 1) {
			int total = pedido.getTotal();
			total = total - pedido.getDesconto();
			pedido.setAcrescimo((int)(total * 0.025));
		} 
		
		Pedido pedidoSaved = pedidoService.atualizarPedido(pedido);
		pedidoSaved.calcularTotais();
		
		// criar e salvar pagamento model
		Pagamento pagamento = new Pagamento();
		pagamento.setPedido(pedidoSaved);
		pagamento.setFormaPagamento("CARTAO");
		pagamento.setNumeroParcelas(parcelas);
		pagamento.setHashCartao(hashCC);
		pagamento.setStatus("CREATED");
		
		Pagamento pagamentoSaved = service.atualizarPagamento(pagamento);
		
		// devolver pedido para confirmacao
		
		ModelMap m = new ModelMap();
		m.addAttribute("pagamento", pagamentoSaved);
		m.addAttribute("pedido", pedidoSaved);
		
		return new ModelAndView("confirmacao", m);
	}
}












