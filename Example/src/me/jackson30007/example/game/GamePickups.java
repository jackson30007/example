package me.jackson30007.example.game;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import me.jackson30007.example.game.upgrades.GameUpgrades;
import me.jackson30007.example.stats.PlayerStats;

/*
 * Keeps track of game pickups & handles pickup collections
 */

public class GamePickups {
	private static Map<Item, GamePickup> pickups = new HashMap<>();
	
	public static boolean isPickup(Item item) {
		return pickups.containsKey(item);
	}
	
	public static Collection<GamePickup> getPickups(Player player) {
		return PlayerStats.get(player).getGameData().getPickups().values();
	}
	
	public static void register(GamePickup pickup) {
		GameData data = PlayerStats.get(pickup.getOwner()).getGameData();
		data.pickups.put(pickup.getItem(), pickup);
		pickups.put(pickup.getItem(), pickup);
	}
	
	public static void unregister(GamePickup pickup) {
		GameData data = PlayerStats.get(pickup.getOwner()).getGameData();
		data.pickups.remove(pickup.getItem());
		pickups.remove(pickup.getItem());
	}
	
	public static void despawnPickups(Player player) {
		GameData data = PlayerStats.get(player).getGameData();
		// when despawning, don't unregister to prevent concurrent modification error
		for (GamePickup pickup : data.pickups.values()) {
			pickups.remove(pickup.getItem());
			pickup.despawn(false);
		}
		data.pickups.clear();
	}
	
	public static void pickup(Player player, GamePickup pickup) {
		pickup.despawn();
		
		PlayerStats stats = PlayerStats.get(player);
		BigDecimal score = pickup.getType().getScore();
		
		// apply upgrade multiplier
		score = score.multiply(new BigDecimal(stats.getUpgrades().getGameUpgrade(GameUpgrades.SCORE_MULTIPLIER, pickup.getType())));
		
		// update player's stats & scoreboard
		stats.addScore(score);
	}
}