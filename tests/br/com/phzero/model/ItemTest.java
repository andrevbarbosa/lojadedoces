package br.com.phzero.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.phzero.commerce.model.Item;

public class ItemTest {

	@Test
	public void preco_PrecoGiven_ShouldHaveValidPrecoCalculado() {
		Item item = new Item();
		item.setPreco(1010);
		
		double preco = 10.10d;
		
		Assert.assertTrue(item.getPrecoCalculado()==preco);
	}

	@Test
	public void preco_PrecoNotGiven_ShouldHaveZeroPrecoCalculado() {
		Item item = new Item();
		item.setPreco(null);
		
		double preco = 0d;
		
		Assert.assertTrue(item.getPrecoCalculado()==preco);
	}

}
