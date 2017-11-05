package br.com.phzero.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phzero.commerce.model.Cliente;
import br.com.phzero.commerce.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public Cliente buscarClientePorEmail(String email) {
		return repository.findByEmail(email);
	}
}
