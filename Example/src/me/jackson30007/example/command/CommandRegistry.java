package me.jackson30007.example.command;

import java.util.Collection;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.jackson30007.example.command.commands.ExampleCommand;
import me.jackson30007.example.command.commands.GiveScoreCommand;
import me.jackson30007.example.command.commands.ShopCommand;

public class CommandRegistry {
	/*
	 * Registers all commands for this plugin
	 */
	public static void registerCommands(JavaPlugin plugin) {
		registerCommand(plugin, new ExampleCommand());
		registerCommand(plugin, new ShopCommand());
		registerCommand(plugin, new GiveScoreCommand());
	}
	
	
	/*
	 * Registers an instance of Command with Bukkit
	 */
	public static void registerCommand(JavaPlugin plugin, Command command) {
		BasicCommand bc = new BasicCommand() {
			@Override
			public void execute(CommandSourceStack commandSourceStack, String[] args) {
				command.execute(commandSourceStack.getSender(), args);
			}
			
			@Override
			public Collection<String> suggest(final CommandSourceStack commandSourceStack, final String[] args) {
		        return command.getSuggestions(commandSourceStack.getSender(), args);
		    }
			
			@Override
			public boolean canUse(final CommandSender sender) {
				return command.canUse(sender);
			}
		};
		
		if (command.hasAliases())
			plugin.registerCommand(command.getName(), command.getDescription(), command.getAliases(), bc);
		else
			plugin.registerCommand(command.getName(), command.getDescription(), bc);
	}
}