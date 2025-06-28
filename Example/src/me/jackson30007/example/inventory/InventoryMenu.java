package me.jackson30007.example.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.jackson30007.example.Main;
import net.kyori.adventure.text.Component;

/*
 * This class handles creating & listening for all custom inventory menu interactions
 */
public class InventoryMenu {
	private static Map<Player, OpenMenu> CURRENT_MENU;
	private static InventoryListeners listeners;
	
	public static OpenMenu getOpenMenu(Player player) {
		if (CURRENT_MENU == null) return null;
		return CURRENT_MENU.get(player);
	}
	
	public static void loggedOut(Player player) {
		if (CURRENT_MENU.containsKey(player))
			clearOpenMenu(player);
	}
	
	protected static void setOpenMenu(Player player, OpenMenu menu) {
		if (CURRENT_MENU == null) {
			// register listeners to handle player clicks
			listeners = new InventoryListeners();
			Bukkit.getPluginManager().registerEvents(listeners, Main.instance());
			CURRENT_MENU = new HashMap<>();
		}
		CURRENT_MENU.put(player, menu);
	}
	
	protected static void clearOpenMenu(Player player) {
		if (CURRENT_MENU == null) return;
		CURRENT_MENU.remove(player);
		if (CURRENT_MENU.isEmpty()) {
			// unregister listeners since there are no open menus
			CURRENT_MENU = null;
			HandlerList.unregisterAll(listeners);
			listeners = null;
		}
	}
	
	private InventoryType invType; // if null, size will be used
	private int size;
	private Component title;
	private Map<Integer, InventoryItem> items = new HashMap<>();
	private List<ClickEvent> clickEvents;
	private List<CloseEvent> closeEvents;
	
	public InventoryMenu() {
		this(InventoryType.CHEST);
	}
	
	public InventoryMenu(int size) {
		this.size = size;
	}
	
	public InventoryMenu(InventoryType invType) {
		this.invType = invType;
	}
	
	public void setTitle(Component title) {
		this.title = title;
	}
	
	public void open(Player player) {
		// if there is an open menu, handle close event before updating current inventory
		OpenMenu currentOpen = getOpenMenu(player);
		if (currentOpen != null)
			currentOpen.menu.handleCloseEvent(player, currentOpen);
		
		// create open menu
		OpenMenu open = new OpenMenu();
		open.menu = this;
		open.bukkitInventory = createInventory(player);
		
		// populate inventory with items
		fillInventory(open.bukkitInventory);
		
		// update player's open menu
		setOpenMenu(player, open);
		
		// display inventory to player
		player.openInventory(open.bukkitInventory);
	}
	
	/*
	 * Creates a Bukkit inventory
	 */
	private Inventory createInventory(Player player) {
		if (invType != null) {
			if (title != null)
				return Bukkit.createInventory(player, invType, title);
			return Bukkit.createInventory(player, invType);
		}else{
			if (title != null)
				return Bukkit.createInventory(player, size, title);
			return Bukkit.createInventory(player, size);
		}
	}
	
	/*
	 * Fills a Bukkit inventory with items added to this menu
	 */
	private void fillInventory(Inventory inventory) {
		for (Entry<Integer, InventoryItem> entry : items.entrySet()) {
			inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
		}
	}
	
	public void handleClick(Player player, InventoryClickEvent event) {
		// call click events
		ClickInfo info = new ClickInfo();
		info.player = player;
		info.bukkitEvent = event;
		info.selfInventory = event.getClickedInventory().equals(player.getInventory());
		
		// try to call click event for clicked item
		if (!info.selfInventory && items.containsKey(event.getSlot())) {
			InventoryItem clickedItem = items.get(event.getSlot());
			if (clickedItem.getClickEvent() != null)
				clickedItem.getClickEvent().onClick(player, info);
		}
		
		// try to call global click events
		if (this.clickEvents != null) {
			for (ClickEvent clickEvent : this.clickEvents)
				clickEvent.onClick(player, info);
		}
	}
	
	public void handleCloseEvent(Player player, OpenMenu open) {
		// try to call all close events
		if (this.closeEvents == null) return;
		for (CloseEvent event : this.closeEvents)
			event.onClose(player, open);
	}
	
	public InventoryMenu setItem(int slot, ItemStack item) {
		this.items.put(slot, new InventoryItem(item));
		return this;
	}
	
	public InventoryMenu setItem(int slot, ItemStack item, ClickEvent clickEvent) {
		this.items.put(slot, new InventoryItem(item).click(clickEvent));
		return this;
	}
	
	public InventoryMenu setItem(int slot, InventoryItem item) {
		this.items.put(slot, item);
		return this;
	}
	
	/*
	 * Adds a click event that is called on every click
	 */
	public InventoryMenu clickEvent(ClickEvent event) {
		if (this.clickEvents == null)
			this.clickEvents = new ArrayList<>();
		this.clickEvents.add(event);
		return this;
	}
	
	/*
	 * Adds a close event that is called when the inventory is closed for any reason
	 */
	public InventoryMenu closeEvent(CloseEvent event) {
		if (this.closeEvents == null)
			this.closeEvents = new ArrayList<>();
		this.closeEvents.add(event);
		return this;
	}
}