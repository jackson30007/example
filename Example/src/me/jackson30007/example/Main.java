package me.jackson30007.example;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static Main instance;
	
	public static Main instance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		for (int i = 0; i < 1000; i++)
			System.out.println("onEnable!");
	}
	
	@Override
	public void onDisable() {
		
	}
}