package br.com.phzero.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.phzero.commerce.model.Cart;
import br.com.phzero.commerce.model.Item;
import br.com.phzero.commerce.model.ItemDePedido;
import br.com.phzero.commerce.model.Pedido;

public class PedidoTest {
	
	@Test
	public void total_ValidDataGiven_ShouldReturnValidTotal() {
		Pedido pedido = new Pedido();
		
		ItemDePedido itemDePedido = new ItemDePedido();
		
		Item item = new Item();
		item.setPreco(1020);
		
		itemDePedido.setItem(item);
		itemDePedido.setQuantidade(2);
		
		Cart cart = new Cart();
		cart.addItemDePedido(itemDePedido);
		
		ItemDePedido novoItemDePedido = new ItemDePedido();
		Item novoItem = new Item();
		novoItem.setPreco(3050);
		
		novoItemDePedido.setItem(novoItem);
		novoItemDePedido.setQuantidade(1);
		
		cart.addItemDePedido(novoItemDePedido);
		
		pedido.setCart(cart);
		
		Assert.assertTrue(pedido.getTotal() == 5090);
		Assert.assertTrue(pedido.getTotalCalculado() == 50.90d);
		Assert.assertTrue(pedido.getTotalLiquido() == 5090);
		
		pedido.setDesconto(1000);
		pedido.calcularTotais();
		Assert.assertTrue(pedido.getTotalCalculado() == 40.90d);
		Assert.assertTrue(pedido.getTotal() == 5090);
		Assert.assertTrue(pedido.getTotalLiquido() == 4090);

		pedido.setAcrescimo(500);
		pedido.calcularTotais();
		Assert.assertTrue(pedido.getTotalCalculado() == 45.90d);
		Assert.assertTrue(pedido.getTotal() == 5090);
		Assert.assertTrue(pedido.getTotalLiquido() == 4590);
	}
	
}






