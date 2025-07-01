package me.jackson30007.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.jackson30007.example.command.CommandRegistry;
import me.jackson30007.example.game.GameListeners;
import me.jackson30007.example.game.GameManager;
import me.jackson30007.example.stats.StatsListeners;

public class Main extends JavaPlugin {
	private static Main instance;
	
	public static Main instance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		// register all plugin commands
		CommandRegistry.registerCommands(this);
		
		// register listeners
		Bukkit.getPluginManager().registerEvents(new StatsListeners(), this);
		Bukkit.getPluginManager().registerEvents(new GameListeners(), this);
		
		// start the incremental pickup game
		GameManager.instance().startGame();
	}
	
	@Override
	public void onDisable() {
		
	}
}