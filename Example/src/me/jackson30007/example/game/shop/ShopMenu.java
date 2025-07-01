package me.jackson30007.example.game.shop;

import java.math.BigDecimal;

import org.bukkit.entity.Player;

import me.jackson30007.example.game.ability.Ability;
import me.jackson30007.example.inventory.InventoryItem;
import me.jackson30007.example.inventory.InventoryMenu;
import me.jackson30007.example.inventory.ItemBuilder;
import me.jackson30007.example.stats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ShopMenu {
	/*
	 * Opens the shop inventory for the player
	 */
	public static void open(Player p, Shop shop) {
		InventoryMenu menu = new InventoryMenu(54);
		PlayerStats stats = PlayerStats.get(p);
		BigDecimal score = stats.getScore();
		
		int slot = 10;
		int counter = 0;
		for (ShopItem shopItem : shop.getItems()) {
			int currentLevel = stats.getShopItemLevel(shopItem);
			BigDecimal cost = shopItem.getCost(currentLevel);
			boolean canAfford = score.compareTo(cost) >= 0;
			ItemBuilder itemBuilder = new ItemBuilder(shopItem.getDisplayItem())
				.setName(Component.text(shopItem.getName(), NamedTextColor.YELLOW));
			
			int stackSize = Math.min(Math.max(1, currentLevel), 99);
			
			itemBuilder.setMaxStackSize(stackSize);
			itemBuilder.setAmount(stackSize);
			
			itemBuilder.addLore(Component.empty());
			itemBuilder.addLore(Component.text("Level " + currentLevel, NamedTextColor.GREEN));
			itemBuilder.addLore(Component.text("Upgrade Cost: ", NamedTextColor.GRAY).append(Component.text(cost.toString() + " Score", NamedTextColor.YELLOW)));
			for (Ability ability : shopItem.getAbilities()) {
				itemBuilder.addLore(Component.empty());
				itemBuilder.addLore(ability.getDescription(p, Math.max(1, currentLevel)));
			}
			itemBuilder.addLore(Component.empty());
			
			if (canAfford)
				itemBuilder.addLore(Component.text("Click to purchase", NamedTextColor.YELLOW));
			else
				itemBuilder.addLore(Component.text("Cannot afford", NamedTextColor.RED));
			
			InventoryItem invItem = new InventoryItem(itemBuilder.build());
			menu.setItem(slot, invItem.click((player, info)->{
				if (!canAfford) return;
				stats.addScore(cost.multiply(new BigDecimal(-1)));
				// increment item level
				stats.setShopItemLevel(shopItem, currentLevel + 1);
				// clear upgrade cache
				stats.getUpgrades().clearUpgradeCache();
				// reopen menu
				open(p, shop);
			}));
			
			// increment slot & prevent sides from being used
			slot++;
			counter++;
			if (counter >= 7) {
				slot += 2;
				counter = 0;
			}
		}
		
		menu.open(p);
	}
}