package me.jackson30007.example.command.commands;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jackson30007.example.command.Command;
import me.jackson30007.example.stats.PlayerStats;
import net.md_5.bungee.api.ChatColor;

public class GiveScoreCommand extends Command {
	public GiveScoreCommand() {
		this.name = "givescore";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
			return;
		}
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Please specify a value.");
			return;
		}
		try {
			BigDecimal score = new BigDecimal(args[0]);
			Player player = (Player) sender;
			PlayerStats stats = PlayerStats.get(player);
			stats.addScore(score);
			sender.sendMessage(ChatColor.GREEN + "Gave yourself " + score.toString() + " score!");
		}catch(Exception e) {
			sender.sendMessage(ChatColor.RED + "Invalid value.");
		}
	}
}