package me.jackson30007.example.game.ability;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import me.jackson30007.example.game.upgrades.GameUpgrade;
import me.jackson30007.example.game.upgrades.UpgradeKey;
import net.kyori.adventure.text.Component;

/*
 * Ability class handles descriptions for upgrades & the bonuses given by the upgrades
 */

public abstract class Ability {
	protected Set<GameUpgrade<?, ?>> validUpgrades = new HashSet<>();
	
	public boolean acceptsUpgrade(GameUpgrade<?, ?> upgrade) {
		return validUpgrades.contains(upgrade);
	}
	
	// gets the description of the ability
	public abstract Component getDescription(Player player, int level);
	
	// returns the upgraded value after this upgrade is applied
	public abstract Object apply(Player player, GameUpgrade<?, ?> upgrade, UpgradeKey key, Object value, int level);
}