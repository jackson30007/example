package me.jackson30007.example.game.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import me.jackson30007.example.game.ability.Ability;

/*
 * 
 * Note: id's must be unique for all shop items
 */

public class ShopItem {
	private static Map<String, ShopItem> ITEMS = new HashMap<>();
	
	public static ShopItem getById(String id) {
		return ITEMS.get(id);
	}
	
	private String id, name;
	private ItemStack displayItem;
	private List<Ability> abilities = new ArrayList<>();
	private double baseCost, costScaling;
	
	public ShopItem(String id, String name, ItemStack displayItem) {
		this.id = id;
		this.name = name;
		this.displayItem = displayItem;
		
		ITEMS.put(id, this);
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemStack getDisplayItem() {
		return displayItem.clone();
	}
	
	public List<Ability> getAbilities() {
		return abilities;
	}
	
	public ShopItem addAbility(Ability ability) {
		this.abilities.add(ability);
		return this;
	}
	
	public ShopItem cost(double base, double scaling) {
		this.baseCost = base;
		this.costScaling = scaling;
		return this;
	}
	
	// calculate cost = (base * scaling^currentLevel)
	public BigDecimal getCost(int level) {
		BigDecimal cost = new BigDecimal(baseCost);
		
		cost = cost.multiply(new BigDecimal(costScaling).pow(level));
		
		// only keep up to 2 decimals
		cost = cost.setScale(2, RoundingMode.DOWN);
		
		return cost;
	}
}