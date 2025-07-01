package me.jackson30007.example.game.ability.abilities;

import org.bukkit.entity.Player;

import me.jackson30007.example.game.ability.Ability;
import me.jackson30007.example.game.upgrades.GameUpgrade;
import me.jackson30007.example.game.upgrades.GameUpgrades;
import me.jackson30007.example.game.upgrades.UpgradeKey;
import me.jackson30007.example.util.FormatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SpawnSpeedAbility extends Ability {
	private double percentPerLevel;
	
	public SpawnSpeedAbility(double percentPerLevel) {
		this.percentPerLevel = percentPerLevel;
		this.validUpgrades.add(GameUpgrades.PICKUP_SPAWN_TICKS);
	}
	
	@Override
	public Component getDescription(Player player, int level) {
		return Component.text("Items spawn " + FormatUtil.cleanNumber(percentPerLevel * level) + "% faster", NamedTextColor.WHITE);
	}
	
	@Override
	public Object apply(Player player, GameUpgrade<?, ?> upgrade, UpgradeKey key, Object value, int level) {
		int n = (int) value;
		double multiplier = 1 + (percentPerLevel * level)/100;
		return (int) (n / multiplier);
	}
}