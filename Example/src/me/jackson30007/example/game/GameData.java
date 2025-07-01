package me.jackson30007.example.game;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Item;

/*
 * Represents data representing the player's game data
 */
public class GameData {
	public int spawnPowerupIn;
	public Map<Item, GamePickup> pickups = new HashMap<>();
	private GameScoreboard scoreboard = new GameScoreboard();
	
	public Map<Item, GamePickup> getPickups() {
		return pickups;
	}
	
	public GameScoreboard getScoreboard() {
		return scoreboard;
	}
}