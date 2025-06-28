package me.jackson30007.example.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
 * This class handles all Bukkit inventory listeners
 */
public class InventoryListeners implements Listener {
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) return;
		Player player = (Player) event.getWhoClicked();
		// make sure the player has an open inventory menu
		OpenMenu open = InventoryMenu.getOpenMenu(player);
		if (open == null) return;
		open.menu.handleClick(player, event);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		// clear players menu to free up memory
		InventoryMenu.loggedOut(e.getPlayer());
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		if (!(event.getPlayer() instanceof Player)) return;
		Player player = (Player) event.getPlayer();
		OpenMenu open = InventoryMenu.getOpenMenu(player);
		// check if player has open inventory
		if (open == null) return;
		
		// make sure the closed inventory is the current inventory
		if (open.bukkitInventory.equals(event.getInventory())) {
			open.menu.handleCloseEvent(player, open);
			// clear player's menu since its closed
			InventoryMenu.clearOpenMenu(player);
			return;
		}
	}
}