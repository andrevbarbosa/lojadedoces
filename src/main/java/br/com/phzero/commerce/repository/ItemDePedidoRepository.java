package br.com.phzero.commerce.repository;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.ItemDePedido;

public interface ItemDePedidoRepository extends Repository<ItemDePedido, Integer>{
	ItemDePedido findById(Integer id);
	
	@Transactional(propagation = Propagation.MANDATORY)
	ItemDePedido save(ItemDePedido itemDePedido);
}
