package br.com.phzero.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phzero.commerce.model.Item;
import br.com.phzero.commerce.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository repository;
	
	public Item buscarItem(Integer id) {
		return repository.findById(id);
	}
	
	public List<Item> buscarTodosItems() {
		return repository.findAll();
	}
}
