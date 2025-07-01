package me.jackson30007.example.game;

import java.math.BigDecimal;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.jackson30007.example.game.upgrades.UpgradeKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public enum PickupType implements UpgradeKey {
	COAL("coal", Component.text("Coal").color(NamedTextColor.DARK_GRAY), new ItemStack(Material.COAL), new BigDecimal(1)),
	COPPER("copper", Component.text("Copper").color(TextColor.color(184, 115, 51)), new ItemStack(Material.COPPER_INGOT), new BigDecimal(2.5)),
	IRON("iron", Component.text("Iron").color(NamedTextColor.GRAY), new ItemStack(Material.IRON_INGOT), new BigDecimal(10)),
	GOLD("gold", Component.text("Gold").color(NamedTextColor.GOLD), new ItemStack(Material.GOLD_INGOT), new BigDecimal(50)),
	DIAMOND("diamond", Component.text("Diamond").color(NamedTextColor.AQUA), new ItemStack(Material.DIAMOND), new BigDecimal(250)),
	EMERALD("emerald", Component.text("Emerald").color(NamedTextColor.GREEN), new ItemStack(Material.EMERALD), new BigDecimal(1000)),
	NETHERITE("netherite", Component.text("Netherite").color(NamedTextColor.RED), new ItemStack(Material.NETHERITE_INGOT), new BigDecimal(10000)),
	;
	
	private String id;
	private Component displayName;
	private ItemStack item;
	private BigDecimal score;
	
	PickupType(String id, Component displayName, ItemStack item, BigDecimal score) {
		this.id = id;
		this.displayName = displayName;
		this.item = item;
		this.score = score;
	}
	
	public String getId() {
		return id;
	}
	
	public Component getDisplayName() {
		return displayName;
	}
	
	public ItemStack getItemStack() {
		return item;
	}
	
	public BigDecimal getScore() {
		return score;
	}
}