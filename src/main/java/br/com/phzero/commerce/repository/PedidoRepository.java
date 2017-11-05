package br.com.phzero.commerce.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.Pedido;

public interface PedidoRepository extends Repository<Pedido, Integer>{
	@Transactional(propagation = Propagation.MANDATORY)
	Pedido save(Pedido pedido);
	
    Optional<Pedido> findById(Integer id);
}
