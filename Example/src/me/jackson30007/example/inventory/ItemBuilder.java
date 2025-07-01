package me.jackson30007.example.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.destroystokyo.paper.profile.CraftPlayerProfile;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

import net.kyori.adventure.text.Component;

/*
 * Utility class used to quickly modify item metadata
 */

public class ItemBuilder {
	private ItemStack item;
	private ItemMeta meta;
	private List<Component> lore = new ArrayList<>();
	
	public ItemBuilder(ItemStack item) {
		this.item = item.clone();
		this.meta = this.item.getItemMeta();
	}
	
	public ItemBuilder(Material material) {
		this(new ItemStack(material));
	}
	
	public ItemBuilder setName(Component name) {
		meta.customName(name);
		return this;
	}
	
	public ItemBuilder addLore(Component lore) {
		this.lore.add(lore);
		return this;
	}
	
	public ItemBuilder setAmount(int amount) {
		this.item.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setMaxStackSize(int maxStackSize) {
		this.meta.setMaxStackSize(maxStackSize);
		return this;
	}
	
	public ItemBuilder setSkullTextures(UUID uuid, String textures) {
		if (!(meta instanceof SkullMeta)) return this;
		SkullMeta meta = (SkullMeta) this.meta;
		PlayerProfile profile = new CraftPlayerProfile(uuid, null);
		profile.setProperty(new ProfileProperty("textures", textures));
		meta.setPlayerProfile(profile);
		return this;
	}
	
	public ItemBuilder setSkullTextures(String textures) {
		if (!(meta instanceof SkullMeta)) return this;
		SkullMeta meta = (SkullMeta) this.meta;
		PlayerProfile profile = meta.getPlayerProfile();
		if (profile == null)
			profile = new CraftPlayerProfile(null, null);
		profile.setProperty(new ProfileProperty("textures", textures));
		meta.setPlayerProfile(profile);
		return this;
	}
	
	public ItemStack build() {
		meta.lore(lore);
		this.item.setItemMeta(meta);
		return this.item;
	}
}