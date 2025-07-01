package me.jackson30007.example.command.commands;

import org.bukkit.command.CommandSender;

import me.jackson30007.example.command.Command;

public class ExampleCommand extends Command {
	public ExampleCommand() {
		this.name = "example";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage("Example Command!");
	}
}