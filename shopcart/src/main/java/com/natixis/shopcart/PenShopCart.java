package com.natixis.shopcart;

public class PenShopCart extends ShopCart {
		
	public PenShopCart() {
		super();
		itemPrice = 1.5;
	}

	@Override
	public void addToCart() {
		Item item = createItem();
		cart.add(item);
	}

	@Override
	public Item createItem() {
		return new Pen(itemPrice);
	}

}
