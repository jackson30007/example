package me.jackson30007.example.inventory;

import org.bukkit.entity.Player;

public interface ClickEvent {
	public void onClick(Player player, ClickInfo info);
}