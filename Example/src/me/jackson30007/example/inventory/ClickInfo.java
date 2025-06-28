package me.jackson30007.example.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickInfo {
	public Player player; // player who clicked
	public InventoryClickEvent bukkitEvent; // bukkit event
	public boolean selfInventory; // when true, the player clicked their own inventory, not the menu
}