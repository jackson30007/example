package me.jackson30007.example.stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.jackson30007.example.game.GameData;
import me.jackson30007.example.game.shop.ShopItem;
import me.jackson30007.example.game.upgrades.PlayerUpgrades;

/*
 * Keeps track of various stats of a player
 * NOTE: stats are not persistent on logout
 */

public class PlayerStats {
	private static Map<Player, PlayerStats> stats = new HashMap<>();
	
	/*
	 * Gets the player's stats
	 */
	public static PlayerStats get(Player player) {
		if (stats.containsKey(player))
			return stats.get(player);
		PlayerStats ps = new PlayerStats(player);
		stats.put(player, ps);
		return ps;
	}
	
	public static void clearStats(Player player) {
		stats.remove(player);
	}
	
	private Player player;
	private Map<String, Object> values = new HashMap<>();
	private GameData gameData = new GameData();
	private PlayerUpgrades upgrades = new PlayerUpgrades(this);
	private Map<ShopItem, Integer> shopItems;
	private BigDecimal score;
	
	public PlayerStats(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean hasStat(String key) {
		return values.containsKey(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getStat(String key) {
		return (T) values.get(key);
	}
	
	public void setStat(String key, Object value) {
		values.put(key, value);
	}
	
	public int getInt(String key) {
		return getStat(key);
	}
	
	public int getDouble(String key) {
		return getStat(key);
	}
	
	public int getString(String key) {
		return getStat(key);
	}
	
	public int getBoolean(String key) {
		return getStat(key);
	}
	
	public JsonElement getJson(String key) {
		return getStat(key);
	}
	
	public JsonObject getJsonObject(String key) {
		return getStat(key);
	}
	
	public GameData getGameData() {
		return gameData;
	}
	
	public PlayerUpgrades getUpgrades() {
		return upgrades;
	}
	
	public BigDecimal getScore() {
		if (score == null) {
			if (hasStat("score"))
				score = new BigDecimal(getString("score"));
			else
				score = BigDecimal.ZERO;
		}
		return score;
	}
	
	public void setScore(BigDecimal score) {
		this.score = score;
		
		BigDecimal rounded = score.setScale(2, RoundingMode.FLOOR);
		
		// store up to 2 decimals
		setStat("score", rounded.toString());
		
		// update scoreboard
		getGameData().getScoreboard().setScore(player, rounded);
	}
	
	public void addScore(BigDecimal score) {
		setScore(getScore().add(score));
	}
	
	public boolean hasShopItem(ShopItem item) {
		return getShopItemLevel(item) > 0;
	}
	
	public Map<ShopItem, Integer> shopItems() {
		if (this.shopItems == null) {
			this.shopItems = new HashMap<>();
			
			// check if the player has purchased shop items
			if (hasStat("shopItems")) {
				JsonObject json = getJsonObject("shopItems");
				for (Entry<String, JsonElement> entry : json.entrySet()) {
					String id = entry.getKey();
					ShopItem item = ShopItem.getById(id);
					if (item == null) continue;
					int level = entry.getValue().getAsInt();
					// update cache
					this.shopItems.put(item, level);
				}
			}
			
		}
		return this.shopItems;
	}
	
	public int getShopItemLevel(ShopItem item) {
		// check cache
		if (shopItems().containsKey(item))
			return shopItems.get(item);
		return 0;
	}
	
	public void setShopItemLevel(ShopItem item, int level) {
		// update cache
		shopItems().put(item, level);
		
		// update json stat
		JsonObject json;
		if (hasStat("shopItems"))
			json = getJsonObject("shopItems");
		else
			setStat("shopItems", json = new JsonObject());
		json.addProperty(item.getId(), level);
	}
}