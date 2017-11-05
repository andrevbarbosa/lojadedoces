package br.com.phzero.commerce.repository;

import org.springframework.data.repository.Repository;

import br.com.phzero.commerce.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer>{
    Cliente findByEmail(String email);
}
