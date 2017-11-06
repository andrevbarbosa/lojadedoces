package br.com.phzero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.phzero.commerce.model.Cart;
import br.com.phzero.commerce.model.Item;
import br.com.phzero.commerce.model.ItemDePedido;
import br.com.phzero.commerce.service.CartService;
import br.com.phzero.commerce.service.ItemDePedidoService;
import br.com.phzero.commerce.service.ItemService;

@Controller
@RequestMapping(path="/cart")
public class CartController {
	
	@Autowired
	private CartService service;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDePedidoService itemDePedidoService;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showCart(@RequestParam(value="cartId", required=true) String cartId) {
		Cart cart = null;
		
		try {
			cart = service.buscarCart(new Integer(cartId)).orElse(new Cart());
		}
		catch (NumberFormatException n) {
			cart = service.atualizarCart(new Cart());
		}
				
		ModelMap m = new ModelMap();
		m.addAttribute("cart", cart);
		return new ModelAndView("cart", m);
	}

	
	@RequestMapping(value = "/additem", method = RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam(value="itemId", required=false) String itemId,
			@RequestParam(value="cartId", required=false) String cartId) {
		Cart cart = null;
				
		if (cartId!=null && cartId.trim().length() > 0) {
			cart = service.buscarCart(new Integer(cartId)).orElse(new Cart());
		}
		else {
			cart = new Cart();
		}
		
		Item item = itemService.buscarItem(new Integer(itemId));
		
		ItemDePedido itemDePedido = cart.getItens().stream()
			.filter(t -> t.getItem().getId().equals(item.getId()))
			.findFirst()
			.orElse(new ItemDePedido());
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(itemDePedido.getQuantidade()+1);
		
		boolean addToCart = false;
		if (itemDePedido.getId()==null) addToCart = true;
		
		ItemDePedido itemDePedidoSaved = itemDePedidoService.atualizarItemDePedido(itemDePedido);
		if (addToCart) cart.addItemDePedido(itemDePedidoSaved);
		
		Cart cartSaved = service.atualizarCart(cart);
		
		ModelMap m = new ModelMap();
		m.addAttribute("cart", cartSaved);
		return new ModelAndView("cart", m);
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ModelAndView checkout(@RequestParam(value="cartId", required=true) Integer cartId) {
		Cart cart = service.buscarCart(cartId).get();
		
		ModelMap m = new ModelMap();
		m.addAttribute("cart", cart);
		return new ModelAndView("checkout", m);
	}
	
}












