package me.jackson30007.example.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/*
 * Stores game locations
 */
public class GameLocations {
	public static Selection getSpawnArea() {
		World world = Bukkit.getWorld("world");
		Location p1 = new Location(world, -0.5, 80, -26.5);
		Location p2 = new Location(world, 7.5, 80, -18.5);
		return new Selection(p1, p2);
	}
}