package com.natixis.shopcart;

import java.util.Set;
import java.util.HashSet;

public abstract class ShopCart {
	protected double itemPrice;
	protected Set<Item> cart;
	
	public ShopCart() {
		this.cart = new HashSet<Item>();
	}
	
	public Set<Item> getCart() {
		return cart;
	}

	public abstract void addToCart();
	public abstract Item createItem();
	
	public double getTotal() {
		return this.cart.stream().mapToDouble(item -> item.price).sum();
	}
	
	public String getItemType() {
		return cart.stream().findFirst().get().getClass().getSimpleName();
	}
}
