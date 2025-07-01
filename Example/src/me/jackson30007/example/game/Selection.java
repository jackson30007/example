package me.jackson30007.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

/*
 * Represents a cuboid area
 */

public class Selection {
	private Location pos1, pos2;
	
	public Selection(Location pos1, Location pos2) {
		this.pos1 = pos1;
		this.pos2 = pos2;
	}
	
	public World getWorld() {
		return pos1.getWorld();
	}
	
	public boolean inside(Entity entity) {
		return this.inside(entity.getLocation());
	}
	
	public boolean inside(Location loc) {
		double x = loc.getX(), y = loc.getY(), z = loc.getZ(),
			x1 = getSmallestX(), y1 = getSmallestY(), z1 = getSmallestZ(), 
			x2 = getLargestX(), y2 = getLargestY(), z2 = getLargestZ();
		
		boolean inX = x >= x1 && x <= x2;
		boolean inY = y >= y1 && y <= y2;;
		boolean inZ = z >= z1 && z <= z2;
		return inX && inY && inZ;
}
	
	public boolean inside(Block block) {
		return inside(block.getLocation());
	}
	
	public double getSmallestX() {
		return Math.min(pos1.getX(), pos2.getX());
	}
	
	public double getLargestX() {
		return Math.max(pos1.getX(), pos2.getX());
	}
	
	public double getSmallestY() {
		return Math.min(pos1.getY(), pos2.getY());
	}
	
	public double getLargestY() {
		return Math.max(pos1.getY(), pos2.getY());
	}
	
	public double getSmallestZ() {
		return Math.min(pos1.getZ(), pos2.getZ());
	}
	
	public double getLargestZ() {
		return Math.max(pos1.getZ(), pos2.getZ());
	}
	
	public Location getMinPoint() {
		return new Location(pos1.getWorld(), getSmallestX(), getSmallestY(), getSmallestZ());
	}
	
	public Location getMaxPoint() {
		return new Location(pos1.getWorld(), getLargestX(), getLargestY(), getLargestZ());
	}
	
	
	public List<Location> getLocations() {
		List<Location> locs = new ArrayList<>();
		World world = pos1.getWorld();
		for (double x =  getSmallestX(); x <=  getLargestX(); x++) {
			for (double z =  getSmallestZ(); z <=  getLargestZ(); z++) {
				for (double y =  getSmallestY(); y <=  getLargestY(); y++) {
					locs.add(new Location(world, x, y, z));
				}
			}
		}
		return locs;
	}
	
	public Location getClosestLocation(Entity entity) {
		return getClosestLocation(entity.getLocation());
	}
	
	/*
	 * returns the closest location inside the selection to the passed location
	 */
	public Location getClosestLocation(Location closestTo) {
		double minX = Math.min(pos1.getX(), pos2.getX());
		double maxX = Math.max(pos1.getX(), pos2.getX());
		double minY = Math.min(pos1.getY(), pos2.getY());
		double maxY = Math.max(pos1.getY(), pos2.getY());
		double minZ = Math.min(pos1.getZ(), pos2.getZ());
		double maxZ = Math.max(pos1.getZ(), pos2.getZ());
		
		double closestX = Math.max(minX, Math.min(closestTo.getX(), maxX));
		double closestY = Math.max(minY, Math.min(closestTo.getY(), maxY));
		double closestZ = Math.max(minZ, Math.min(closestTo.getZ(), maxZ));
		
		return new Location(closestTo.getWorld(), closestX, closestY, closestZ);
	}
	
	public Location getCenterLocation() {
		World world = pos1.getWorld();
		double xDiff = getLargestX() - getSmallestX();
		double yDiff = getLargestY() - getSmallestY();
		double zDiff = getLargestZ() - getSmallestZ();
		
		double x = getLargestX() - (xDiff / 2);
		double y = getLargestY() - (yDiff / 2);
		double z = getLargestZ() - (zDiff / 2);
		
		return new Location(world, x, y, z);
	}
	
	/*
	 * Returns a random location inside the selection
	 */
	public Location getRandomLocation() {
		Random r = GameManager.random;
		World world = pos1.getWorld();
		double xDiff = getLargestX() - getSmallestX();
		double yDiff = getLargestY() - getSmallestY();
		double zDiff = getLargestZ() - getSmallestZ();
		
		double x = getSmallestX() + (xDiff * r.nextDouble());
		double y = getSmallestY() + (yDiff * r.nextDouble());
		double z = getSmallestZ() + (zDiff * r.nextDouble());
		
		return new Location(world, x, y, z);
	}
	
	public Selection clone() {
		Location pos1 = (this.pos1 == null ? null : this.pos1.clone());
		Location pos2 = (this.pos2 == null ? null : this.pos2.clone());
		return new Selection(pos1, pos2);
	}
}