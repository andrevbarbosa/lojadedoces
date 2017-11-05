package br.com.phzero.commerce.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.phzero.commerce.model.Item;

public interface ItemRepository extends Repository<Item, Integer>{
    Item findById(Integer id);
    
    List<Item> findAll();
}
