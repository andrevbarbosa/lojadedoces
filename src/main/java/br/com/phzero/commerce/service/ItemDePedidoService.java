package br.com.phzero.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.ItemDePedido;
import br.com.phzero.commerce.repository.ItemDePedidoRepository;

@Service
public class ItemDePedidoService {
	
	@Autowired
	private ItemDePedidoRepository repository;
	
	public ItemDePedido buscarItemDePedido(Integer id) {
		return repository.findById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ItemDePedido atualizarItemDePedido(ItemDePedido itemDePedido) {
		ItemDePedido savedItemDePedido = repository.save(itemDePedido);
		
		return savedItemDePedido;
	}
}
