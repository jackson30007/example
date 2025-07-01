package me.jackson30007.example.game.ability.abilities;

import org.bukkit.entity.Player;

import me.jackson30007.example.game.ability.Ability;
import me.jackson30007.example.game.upgrades.GameUpgrade;
import me.jackson30007.example.game.upgrades.GameUpgrades;
import me.jackson30007.example.game.upgrades.UpgradeKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class BonusCapacityAbility extends Ability {
	private int capacityPerLevel;
	
	public BonusCapacityAbility(int capacityPerLevel) {
		this.capacityPerLevel = capacityPerLevel;
		this.validUpgrades.add(GameUpgrades.MAX_PICKUPS);
	}
	
	@Override
	public Component getDescription(Player player, int level) {
		return Component.text("Increases max items by " + (capacityPerLevel * level), NamedTextColor.WHITE);
	}
	
	@Override
	public Object apply(Player player, GameUpgrade<?, ?> upgrade, UpgradeKey key, Object value, int level) {
		int n = (int) value;
		return n + (capacityPerLevel * level);
	}
}