package me.jackson30007.example.inventory;

import org.bukkit.entity.Player;

public interface CloseEvent {
	public void onClose(Player player, OpenMenu open);
}