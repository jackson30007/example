package me.jackson30007.example.game.ability.abilities;

import org.bukkit.entity.Player;

import me.jackson30007.example.game.PickupType;
import me.jackson30007.example.game.ability.Ability;
import me.jackson30007.example.game.upgrades.GameUpgrade;
import me.jackson30007.example.game.upgrades.GameUpgrades;
import me.jackson30007.example.game.upgrades.UpgradeKey;
import me.jackson30007.example.util.FormatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SpawnChanceAbility extends Ability {
	private PickupType type, previous;
	private double chancePerLevel;
	
	public SpawnChanceAbility(PickupType type, double chancePerLevel) {
		this.type = type;
		this.previous = PickupType.values()[type.ordinal()-1];
		this.chancePerLevel = chancePerLevel;
		this.validUpgrades.add(GameUpgrades.SPAWN_CHANCE);
	}
	
	@Override
	public Component getDescription(Player player, int level) {
		return Component.empty().append(type.getDisplayName()).append(Component.text(" has a " + FormatUtil.cleanNumber(chancePerLevel * level) + "% chance to spawn instead of ", NamedTextColor.WHITE))
			.append(previous.getDisplayName());
	}
	
	@Override
	public Object apply(Player player, GameUpgrade<?, ?> upgrade, UpgradeKey key, Object value, int level) {
		// check for correct pickup type
		if (key != type) return value;
		double n = (double) value;
		return n + (chancePerLevel * level);
	}
}