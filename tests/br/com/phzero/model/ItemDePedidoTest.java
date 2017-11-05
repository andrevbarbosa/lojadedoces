package br.com.phzero.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.phzero.commerce.model.Item;
import br.com.phzero.commerce.model.ItemDePedido;

public class ItemDePedidoTest {

	@Test
	public void subTotal_ValidItemAndQuantidadeGiven_ShouldHaveValidSubtotal() {
		ItemDePedido itemDePedido = new ItemDePedido();
		
		Item item = new Item();
		item.setPreco(1000);
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(2);
		
		int subtotal = 2000;
		
		Assert.assertTrue(itemDePedido.getSubTotal()==subtotal);
	}

	@Test
	public void subTotal_ValidItemAndQuantidadeGiven_ShouldHaveValidSubtotalCalculado() {
		ItemDePedido itemDePedido = new ItemDePedido();
		
		Item item = new Item();
		item.setPreco(1020);
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(2);
		
		double subtotal = 20.40d;
		
		Assert.assertTrue(itemDePedido.getSubTotalCalculado()==subtotal);
	}
	
	@Test
	public void subTotal_ValidNewQuantidadeGiven_ShouldHaveValidSubtotalCalculado() {
		ItemDePedido itemDePedido = new ItemDePedido();
		
		Item item = new Item();
		item.setPreco(1020);
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(2);
		
		double subtotal = 20.40d;
		
		Assert.assertTrue(itemDePedido.getSubTotalCalculado()==subtotal);
		
		itemDePedido.setQuantidade(3);
		
		subtotal = 30.60d;
		
		Assert.assertTrue(itemDePedido.getSubTotalCalculado()==subtotal);
	}
}
