package br.com.phzero.commerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.Cart;
import br.com.phzero.commerce.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository repository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Cart atualizarCart(Cart cart) {
		cart.atualizarTotal();
		Cart savedCart = repository.save(cart);
		
		return savedCart;
	}
	
	public Optional<Cart> buscarCart(Integer id) {
		return repository.findById(id);
	}
}
