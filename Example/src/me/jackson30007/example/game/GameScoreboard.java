package me.jackson30007.example.game;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.ChatColor;

public class GameScoreboard {
	private Scoreboard scoreboard;
	private Team scoreTeam;
	
	public GameScoreboard() {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = this.scoreboard.registerNewObjective("example", Criteria.DUMMY, Component.text("Example Game", NamedTextColor.AQUA));
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		createNewTeam(ChatColor.AQUA.toString(), Component.text(currentDate(), NamedTextColor.DARK_GRAY), null, 2);
		
		createNewTeam(ChatColor.BLACK.toString(), null, null, 1);
		
		scoreTeam = createNewTeam("", Component.text("Score: ", NamedTextColor.YELLOW), Component.text("0", NamedTextColor.WHITE), 0);
	}
	
	public void setScore(Player player, BigDecimal score) {
		if (player.getScoreboard() != scoreboard)
			player.setScoreboard(scoreboard);
		
		scoreTeam.suffix(Component.text(score.toString(), NamedTextColor.WHITE));
	}
	
	private Team createNewTeam(String name, Component prefix, Component suffix, int score) {
		Team team = this.scoreboard.registerNewTeam(name);
		team.prefix(prefix);
		team.suffix(suffix);
		team.addEntry(name);
		this.scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(name).setScore(score);
		return team;
	}
	
	private String currentDate() {
		Calendar calendar = new GregorianCalendar();
		return (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DATE) 
		+ "/" + calendar.get(Calendar.YEAR);
	}
}