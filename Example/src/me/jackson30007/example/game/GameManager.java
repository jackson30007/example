package me.jackson30007.example.game;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.jackson30007.example.game.ability.abilities.BonusCapacityAbility;
import me.jackson30007.example.game.ability.abilities.BonusScoreAbility;
import me.jackson30007.example.game.ability.abilities.SpawnChanceAbility;
import me.jackson30007.example.game.ability.abilities.SpawnSpeedAbility;
import me.jackson30007.example.game.shop.Shop;
import me.jackson30007.example.game.shop.ShopItem;
import me.jackson30007.example.util.tasks.RepeatingTask;

public class GameManager {
	private static GameManager instance;
	public static Random random = new Random();
	
	public static GameManager instance() {
		if (instance == null)
			return instance = new GameManager();
		return instance;
	}
	
	private Shop shop;
	
	private GameManager() {
		shop = new Shop("Upgrades");
		
		shop.addItems(
			new ShopItem("spawnspeed", "Spawn Speed", new ItemStack(Material.CLOCK))
				.cost(10, 1.75)
				.addAbility(new SpawnSpeedAbility(10)),
				
			new ShopItem("itemcapacity", "Item Capacity", new ItemStack(Material.CHEST))
				.cost(7.5, 1.6)
				.addAbility(new BonusCapacityAbility(1)),
				
			new ShopItem("coalmulti", "Coal Multiplier", new ItemStack(Material.COAL_BLOCK))
				.cost(2.5, 1.5)
				.addAbility(new BonusScoreAbility(PickupType.COAL, 50)),
				
			new ShopItem("copperchance", "Copper Spawn Chance", new ItemStack(Material.COPPER_INGOT))
				.cost(5, 1.25)
				.addAbility(new SpawnChanceAbility(PickupType.COPPER, 2.5)),
			
			new ShopItem("coppermulti", "Copper Multiplier", new ItemStack(Material.COPPER_BLOCK))
				.cost(15, 1.55)
				.addAbility(new BonusScoreAbility(PickupType.COPPER, 50)),
				
			new ShopItem("ironchance", "Iron Spawn Chance", new ItemStack(Material.IRON_INGOT))
				.cost(20, 1.275)
				.addAbility(new SpawnChanceAbility(PickupType.IRON, 2.5)),
			
			new ShopItem("ironmulti", "Iron Multiplier", new ItemStack(Material.IRON_BLOCK))
				.cost(40, 1.6)
				.addAbility(new BonusScoreAbility(PickupType.IRON, 50)),
				
			new ShopItem("goldchance", "Gold Spawn Chance", new ItemStack(Material.GOLD_INGOT))
				.cost(50, 1.3)
				.addAbility(new SpawnChanceAbility(PickupType.GOLD, 2.5)),
			
			new ShopItem("goldmulti", "Gold Multiplier", new ItemStack(Material.GOLD_BLOCK))
				.cost(100, 1.625)
				.addAbility(new BonusScoreAbility(PickupType.GOLD, 50)),
				
			new ShopItem("diamondchance", "Diamond Spawn Chance", new ItemStack(Material.DIAMOND))
				.cost(150, 1.325)
				.addAbility(new SpawnChanceAbility(PickupType.DIAMOND, 2.5)),
			
			new ShopItem("diamondmulti", "Diamond Multiplier", new ItemStack(Material.DIAMOND_BLOCK))
				.cost(300, 1.65)
				.addAbility(new BonusScoreAbility(PickupType.DIAMOND, 50)),
				
			new ShopItem("emeraldchance", "Emerald Spawn Chance", new ItemStack(Material.EMERALD))
				.cost(500, 1.35)
				.addAbility(new SpawnChanceAbility(PickupType.EMERALD, 2.5)),
			
			new ShopItem("emeraldmulti", "Emerald Multiplier", new ItemStack(Material.EMERALD_BLOCK))
				.cost(1000, 1.675)
				.addAbility(new BonusScoreAbility(PickupType.EMERALD, 50)),
				
			new ShopItem("netheritechance", "Netherite Spawn Chance", new ItemStack(Material.NETHERITE_INGOT))
				.cost(5000, 1.4)
				.addAbility(new SpawnChanceAbility(PickupType.NETHERITE, 2.5)),
			
			new ShopItem("netheritemulti", "Netherite Multiplier", new ItemStack(Material.NETHERITE_BLOCK))
				.cost(10000, 1.725)
				.addAbility(new BonusScoreAbility(PickupType.NETHERITE, 50))
		);
		
	}
	
	public Shop getGameShop() {
		return shop;
	}
	
	public void startGame() {
		new RepeatingTask(1, 1)
			.run(GameLoop::tick)
			.start();
	}
}