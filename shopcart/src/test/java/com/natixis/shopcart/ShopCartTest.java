package com.natixis.shopcart;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class ShopCartTest {

	@Test
	public void testAddToCart() {
		ShopCart penCart = new PenShopCart();
		penCart.addToCart();
		assertEquals("Item was not added to Cart", 1, penCart.cart.size());
	}
	
	@Test
	public void testTotalAmount() {
		ShopCart bookCart = new BookShopCart();
		Stream.iterate(0, n -> n + 1).limit(2).forEach(x -> bookCart.addToCart());
		assertEquals("Wrong amount" , 16.0 , bookCart.getTotal(), 8);
	}

}
