package me.jackson30007.example.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

public abstract class Command {
	protected String name; // name of the command, must be set
	protected String description; // description of the command, can be left null
	protected List<String> aliases; // aliases for the command, can be left null
	protected String permission; // permission to use the command, if null anyone can use this command
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean hasAliases() {
		return aliases != null;
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	public boolean canUse(CommandSender sender) {
		return permission == null || sender.hasPermission(permission);
	}
	
	/*
	 * Called when the command is executed
	 */
	public abstract void execute(CommandSender sender, String[] args);
	
	/*
	 * Returns a list of suggestions for tab completion
	 */
	public List<String> getSuggestions(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}
}