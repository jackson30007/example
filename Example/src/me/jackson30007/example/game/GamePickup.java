package me.jackson30007.example.game;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.jackson30007.example.inventory.ItemBuilder;
import net.kyori.adventure.text.Component;

public class GamePickup {
	private Player owner;
	private PickupType type;
	private Item item;
	
	public GamePickup(Player owner, PickupType type) {
		this.owner = owner;
		this.type = type;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public PickupType getType() {
		return type;
	}
	
	public Item getItem() {
		return item;
	}
	
	public boolean isSpawned() {
		return item != null;
	}
	
	public void spawn(Location loc) {
		// set name of item to a random name to prevent item stacking
		ItemStack itemStack = new ItemBuilder(type.getItemStack().clone()).setName(Component.text(GameManager.random.nextInt(99999999))).build();
		
		loc.getWorld().dropItem(loc, itemStack, item->{
			this.item = item;
			GamePickups.register(this);
			item.setOwner(owner.getUniqueId());
			item.setWillAge(false);
		});
	}
	
	public void despawn() {
		despawn(true);
	}
	
	public void despawn(boolean unregister) {
		if (!isSpawned()) return;
		
		if (unregister)
			GamePickups.unregister(this);
		
		this.item.remove();
		this.item = null;
	}
}