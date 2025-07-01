package me.jackson30007.example.game;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.papermc.paper.event.player.PlayerTrackEntityEvent;
import me.jackson30007.example.stats.PlayerStats;

public class GameListeners implements Listener {
	@EventHandler
	public void onPickup(PlayerAttemptPickupItemEvent event) {
		// only check for items that are pickups
		if (!GamePickups.isPickup(event.getItem())) return;
		event.setCancelled(true);
		Player player = event.getPlayer();
		GameData data = PlayerStats.get(player).getGameData();
		if (!data.pickups.containsKey(event.getItem())) return;
		GamePickups.pickup(player, data.pickups.get(event.getItem()));
	}
	
	@EventHandler
	public void onTrack(PlayerTrackEntityEvent event) {
		if (!(event.getEntity() instanceof Item)) return;
		Item item = (Item) event.getEntity();
		// only check for items that are pickups
		if (!GamePickups.isPickup(item)) return;
		Player player = event.getPlayer();
		GameData data = PlayerStats.get(player).getGameData();
		// don't let player see if it isn't their item
		if (!data.pickups.containsKey(item))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		// despawn player's pickups on the map
		GamePickups.despawnPickups(event.getPlayer());
	}
}