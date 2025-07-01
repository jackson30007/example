package me.jackson30007.example.game.upgrades;

/*
 * Game Upgrade
 * T represents a key object that is used as a unique identifier for the upgrade
 * V represents the default value
 */

public class GameUpgrade<T extends UpgradeKey, V> {
	public GameUpgrade(String id, V defaultValue) {
		this.id = id;
		this.defaultValue = defaultValue;
	}
	
	private String id;
	private V defaultValue;
	
	public String getId() {
		return id;
	}
	
	public V getDefaultValue() {
		return defaultValue;
	}
}