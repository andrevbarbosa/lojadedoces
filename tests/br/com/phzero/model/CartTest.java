package br.com.phzero.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.phzero.commerce.model.Cart;
import br.com.phzero.commerce.model.Item;
import br.com.phzero.commerce.model.ItemDePedido;

public class CartTest {
	
	@Test
	public void total_ValidItemGiven_ShouldHaveValidTotal() {
		ItemDePedido itemDePedido = new ItemDePedido();
		
		Item item = new Item();
		item.setPreco(1020);
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(2);
		
		Cart cart = new Cart();
		cart.addItemDePedido(itemDePedido);
		
		int total = 2040;
		
		Assert.assertTrue(cart.getTotal() == total);
	}
	
	@Test
	public void total_NewItemGiven_ShouldUpdateValidTotal() {
		ItemDePedido itemDePedido = new ItemDePedido();
		
		Item item = new Item();
		item.setPreco(1020);
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(2);
		
		Cart cart = new Cart();
		cart.addItemDePedido(itemDePedido);
		
		int total = 2040;
		
		Assert.assertTrue(cart.getTotal() == total);
		
		ItemDePedido novoItemDePedido = new ItemDePedido();
		Item novoItem = new Item();
		novoItem.setPreco(3050);
		
		novoItemDePedido.setItem(novoItem);
		novoItemDePedido.setQuantidade(1);
		
		cart.addItemDePedido(novoItemDePedido);
		
		total = 5090;
		Assert.assertTrue(cart.getTotal() == total);
	}
	
}






