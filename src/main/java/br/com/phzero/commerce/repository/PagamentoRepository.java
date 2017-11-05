package br.com.phzero.commerce.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.Pagamento;

public interface PagamentoRepository extends Repository<Pagamento, Integer>{
	@Transactional(propagation = Propagation.MANDATORY)
	Pagamento save(Pagamento pagamento);
	
    Optional<Pagamento> findById(Integer id);
    Optional<Pagamento> findByMoipId(String moipId);
}
