package me.jackson30007.example.game.upgrades;

import me.jackson30007.example.game.PickupType;

public class GameUpgrades {
	public static GameUpgrade<?, Integer> PICKUP_SPAWN_TICKS = new GameUpgrade<>("spawnTicks", 5 * 20);
	public static GameUpgrade<?, Integer> MAX_PICKUPS = new GameUpgrade<>("maxPickups", 5);
	public static GameUpgrade<PickupType, Double> SPAWN_CHANCE = new GameUpgrade<>("spawnChance", 0D);
	public static GameUpgrade<PickupType, Double> SCORE_MULTIPLIER = new GameUpgrade<>("scoreMultiplier", 1D);
}