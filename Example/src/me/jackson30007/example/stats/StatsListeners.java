package me.jackson30007.example.stats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatsListeners implements Listener {
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent event) {
		// clear players stats to free up memory
		PlayerStats.clearStats(event.getPlayer());
	}
}