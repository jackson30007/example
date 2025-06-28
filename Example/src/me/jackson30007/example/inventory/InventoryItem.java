package me.jackson30007.example.inventory;

import org.bukkit.inventory.ItemStack;

public class InventoryItem {
	public InventoryItem(ItemStack item) {
		this.item = item;
	}
	
	private ItemStack item;
	private ClickEvent clickEvent;
	
	public ItemStack getItemStack() {
		return item;
	}
	
	public ClickEvent getClickEvent() {
		return clickEvent;
	}
	
	public InventoryItem click(ClickEvent clickEvent) {
		this.clickEvent = clickEvent;
		return this;
	}
}