package me.jackson30007.example;

import org.bukkit.plugin.java.JavaPlugin;

import me.jackson30007.example.command.CommandRegistry;

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
	}
	
	@Override
	public void onDisable() {
		
	}
}