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

public class BonusScoreAbility extends Ability {
	private PickupType type;
	private double scorePerLevel;
	
	public BonusScoreAbility(PickupType type, double scorePerLevel) {
		this.type = type;
		this.scorePerLevel = scorePerLevel;
		this.validUpgrades.add(GameUpgrades.SCORE_MULTIPLIER);
	}
	
	@Override
	public Component getDescription(Player player, int level) {
		return Component.text("Increases score by " + FormatUtil.cleanNumber(scorePerLevel * level) + "% when picking up ", NamedTextColor.WHITE).append(type.getDisplayName());
	}
	
	@Override
	public Object apply(Player player, GameUpgrade<?, ?> upgrade, UpgradeKey key, Object value, int level) {
		// check for correct pickup type
		if (key != type) return value;
		double n = (double) value;
		double multiplier = 1 + (scorePerLevel * level)/100;
		return n * multiplier;
	}
}