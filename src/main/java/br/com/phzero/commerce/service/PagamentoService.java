package br.com.phzero.commerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.phzero.commerce.model.Pagamento;
import br.com.phzero.commerce.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository repository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Pagamento atualizarPagamento(Pagamento pagamento) {
		Pagamento pagamentoSaved = repository.save(pagamento);
		
		return pagamentoSaved;
	}
	
	public Optional<Pagamento> buscarPagamento(Integer id) {
		return repository.findById(id);
	}
	
	public Optional<Pagamento> buscarPagamentoPorMoipId(String moipId) {
		return repository.findByMoipId(moipId);
	}
}
