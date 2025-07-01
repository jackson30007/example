package me.jackson30007.example.game.shop;

import java.util.ArrayList;
import java.util.List;

public class Shop {
	private String name;
	private List<ShopItem> items = new ArrayList<>();
	
	public Shop(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<ShopItem> getItems() {
		return items;
	}
	
	public void addItems(ShopItem... items) {
		for (ShopItem item : items)
			this.items.add(item);
	}
}