package br.com.phzero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.phzero.commerce.model.Cart;
import br.com.phzero.commerce.model.Cliente;
import br.com.phzero.commerce.model.Item;
import br.com.phzero.commerce.model.ItemDePedido;
import br.com.phzero.commerce.service.CartService;
import br.com.phzero.commerce.service.ClienteService;
import br.com.phzero.commerce.service.ItemService;

@Controller
@RequestMapping(path="/")
public class MainController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="email", required=true) String email) {
		System.out.println(email);
		Cliente cliente = clienteService.buscarClientePorEmail(email);
		
		List<Item> itens = itemService.buscarTodosItems();
		
		ModelMap m = new ModelMap();
		m.addAttribute("idCliente", cliente.getId());
		m.addAttribute("itens", itens);
		m.addAttribute("qtdItensCart", "vazio");
		//m.addAttribute("cartId", "1");

		return new ModelAndView("produtos", m);
	}

	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	public ModelAndView produtos(@RequestParam(value="cartId", required=true) Integer cartId) {
		Cliente cliente = clienteService.buscarClientePorEmail("cliente@email.com");
		
		List<Item> itens = itemService.buscarTodosItems();
		Cart cart = cartService.buscarCart(new Integer(cartId)).orElse(new Cart());
		
		Integer qtd = cart.getItens().stream().mapToInt(ItemDePedido::getQuantidade).sum();
		
		ModelMap m = new ModelMap();
		m.addAttribute("idCliente", cliente.getId());
		m.addAttribute("itens", itens);
		m.addAttribute("cartId", cartId);
		m.addAttribute("qtdItensCart", qtd==0?"vazio":qtd==1?"1 item":qtd + " itens");

		return new ModelAndView("produtos", m);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start() {
		ModelMap m = new ModelMap();
		m.addAttribute("email", "cliente@email.com");

		return new ModelAndView("login", m);
	}
	
	
}












