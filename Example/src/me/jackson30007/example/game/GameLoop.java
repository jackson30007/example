package me.jackson30007.example.game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.jackson30007.example.game.upgrades.GameUpgrades;
import me.jackson30007.example.game.upgrades.PlayerUpgrades;
import me.jackson30007.example.stats.PlayerStats;

public class GameLoop {
	public static void tick() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerStats stats = PlayerStats.get(player);
			PlayerUpgrades upgrades = stats.getUpgrades();
			GameData data = stats.getGameData();
			
			// ignore if player has max spawns
			int spawned = GamePickups.getPickups(player).size();
			if (spawned >= upgrades.getGameUpgrade(GameUpgrades.MAX_PICKUPS)) continue;
			
			// iterate spawn ticks
			data.spawnPowerupIn++;
			if (data.spawnPowerupIn >= upgrades.getGameUpgrade(GameUpgrades.PICKUP_SPAWN_TICKS)) {
				data.spawnPowerupIn = 0;
				spawnRandomPickup(player, upgrades, data);
			}
		}
	}
	
	public static void spawnRandomPickup(Player player, PlayerUpgrades upgrades, GameData data) {
		Selection selection = GameLocations.getSpawnArea();
		new GamePickup(player, randomPickupType(upgrades)).spawn(selection.getRandomLocation());
	}
	
	private static PickupType randomPickupType(PlayerUpgrades upgrades) {
		Random r = GameManager.random;
		PickupType type = PickupType.COAL;
		
		for (PickupType pt : PickupType.values()) {
			if (pt == type) continue;
			// calculate chance to spawn next pickup tier
			double chance = upgrades.getGameUpgrade(GameUpgrades.SPAWN_CHANCE, pt);
			if (chance <= 0 || r.nextInt(100000) >= chance * 1000) break;
			type = pt;
		}
		
		return type;
	}
}