package br.com.phzero.commerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.Pedido;
import br.com.phzero.commerce.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Pedido atualizarPedido(Pedido pedido) {
		Pedido pedidoSaved = repository.save(pedido);
		
		return pedidoSaved;
	}
	
	public Optional<Pedido> buscarPedido(Integer id) {
		return repository.findById(id);
	}
}
