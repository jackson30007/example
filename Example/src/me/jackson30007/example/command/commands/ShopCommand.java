package me.jackson30007.example.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jackson30007.example.command.Command;
import me.jackson30007.example.game.GameManager;
import me.jackson30007.example.game.shop.ShopMenu;
import net.md_5.bungee.api.ChatColor;

public class ShopCommand extends Command {
	public ShopCommand() {
		this.name = "shop";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
			return;
		}
		ShopMenu.open((Player) sender, GameManager.instance().getGameShop());
	}
}