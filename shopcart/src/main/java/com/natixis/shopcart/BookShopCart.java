package com.natixis.shopcart;

public class BookShopCart extends ShopCart {
	
	public BookShopCart() {
		super();
		itemPrice = 8;
	}

	@Override
	public void addToCart() {
		Item item = this.createItem();
		cart.add(item);
	}

	@Override
	public Item createItem() {
		return new Book(itemPrice);
	}

	

}
