package me.jackson30007.example.game.upgrades;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import me.jackson30007.example.game.ability.Ability;
import me.jackson30007.example.game.shop.ShopItem;
import me.jackson30007.example.stats.PlayerStats;

/*
 * Stores data about a player's upgrades
 */
public class PlayerUpgrades {
	private PlayerStats stats;
	private Map<String, Object> gameUpgrades = new HashMap<>();
	
	public PlayerUpgrades(PlayerStats stats) {
		this.stats = stats;
	}
	
	public void clearUpgradeCache() {
		gameUpgrades.clear();
	}
	
	public <T extends UpgradeKey, V> V getGameUpgrade(GameUpgrade<T, V> upgrade) {
		return getGameUpgrade(upgrade, null);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends UpgradeKey, V> V getGameUpgrade(GameUpgrade<T, V> upgrade, T key) {
		String s = key(upgrade, key);
		
		// check cache
		if (gameUpgrades.containsKey(s))
			return (V) gameUpgrades.get(s);
		
		V value = upgrade.getDefaultValue();
		
		// query upgrade value by checking all purchased shop item abilities
		for (Entry<ShopItem, Integer> entry : stats.shopItems().entrySet()) {
			ShopItem item = entry.getKey();
			int level = entry.getValue();
			for (Ability ability : item.getAbilities()) {
				if (!ability.acceptsUpgrade(upgrade)) continue;
				value = (V) ability.apply(stats.getPlayer(), upgrade, key, value, level);
			}
		}
		
		// update cache
		gameUpgrades.put(s, value);
		
		return value;
	}
	
	public <T extends UpgradeKey, V> void setGameUpgrade(GameUpgrade<T, V> upgrade, T key) {
		gameUpgrades.put(key(upgrade, key), key);
	}
	
	private String key(GameUpgrade<?, ?> upgrade, UpgradeKey key) {
		return upgrade.getId() + (key != null ? "_" + key.getId() : "");
	}
}