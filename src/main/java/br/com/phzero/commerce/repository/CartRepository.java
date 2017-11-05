package br.com.phzero.commerce.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.Cart;

public interface CartRepository extends Repository<Cart, Integer>{
	@Transactional(propagation = Propagation.MANDATORY)
	Cart save(Cart cart);
	
    Optional<Cart> findById(Integer id);
}
