package com.natixis.shopcart;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class App {
	
	public static final String PATH = "C:\\temp\\";

	public static void main(String[] args) {
		
		//add items to carts
		
		ShopCart penShopCart = new PenShopCart();
		ShopCart bookShopCart = new BookShopCart();

		Stream.iterate(0, n -> n + 1).limit(3).forEach(x -> penShopCart.addToCart());
		Stream.iterate(0, n -> n + 1).limit(2).forEach(x -> bookShopCart.addToCart());

		List<ShopCart> cartList = Arrays.asList(penShopCart, bookShopCart);
		
		//print result on console
		
		printFromList(cartList);
		
		File file = new File(PATH);
		
		//check if path already exists
		
		if (!file.exists()) {
			createDir(file);
		}
		
		//create json files
		
		try {
			convertListToJson(cartList);
			convertResultToJson(cartList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void printFromList(List<ShopCart> list) {
		String formatter = "| %-16s | %-13s | %-13s | %-12s |";
		//header
		System.out.println("-------------------------------------------------------------------");
		System.out.println(String.format(formatter, "Product", "Quantity", "Price", "Total"));
		//body
		System.out.println("-------------------------------------------------------------------");
		list.stream().forEach(row -> System.out.println(String.format(formatter, row.getItemType(), row.cart.size(),
				row.cart.stream().findFirst().get().price, row.getTotal())));
		System.out.println("-------------------------------------------------------------------");
		System.out.println(String.format(formatter, "", "", "", list.stream().mapToDouble(item -> item.getTotal()).sum()));
		System.out.println("-------------------------------------------------------------------");
	}
	
	private static void createDir(File file) {
		file.mkdirs();
	}
	
	private static void convertListToJson(List<ShopCart> list) throws StreamWriteException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(PATH + "listToJson.json");
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
	}
	
	private static void convertResultToJson(List<ShopCart> list) throws StreamWriteException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(PATH + "resultToJson.json");
		ArrayNode itemList = objectMapper.createArrayNode();
				
		list.stream().forEach(row -> {
			ObjectNode node = itemList.addObject();
			node.put("Product", row.getItemType());
			node.put("Quantity", row.cart.size());
			node.put("Price", row.cart.stream().findFirst().get().price);
			node.put("Total", row.getTotal());
		});
		
		itemList.addObject().put("CartTotal", list.stream().mapToDouble(item -> item.getTotal()).sum());
				
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, itemList);
	}

}
