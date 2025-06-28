package me.jackson30007.example.util.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.jackson30007.example.Main;

/*
 * Utility class to help manage bukkit's schedulers
 * */

public class DelayedTask {
	public static void runLate(Runnable run) {
		runLate(1, run);
	}
	
	public static void runLate(TaskRunnable run) {
		runLate(1, run);
	}
	
	public static void runLate(int ticks, Runnable run) {
		new DelayedTask(ticks).run(run).start();
	}
	
	public static void runLate(int ticks, TaskRunnable run) {
		new DelayedTask(ticks).run(run).start();
	}
	
	private Plugin plugin;
	
	protected Plugin plugin() {
		if (plugin == null) {
			plugin = Main.instance();
		}
		return plugin;
	}
	
	public DelayedTask(long delay) {
		this.delay = delay;
	}
	
	protected long delay;
	protected int taskId;
	protected boolean started;
	private TaskRunnable run;
	
	public DelayedTask start() {
		started = true;
		taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin(), new Runnable() {
			public void run() {
				started = false;
				callRun();
			}
		}, delay);
		return this;
	}
	
	public DelayedTask run(TaskRunnable run) {
		this.run = run;
		return this;
	}
	
	public DelayedTask run(Runnable run) {
		this.run = (task)->{
			run.run();
		};
		return this;
	}
	
	protected void callRun() {
		if (run != null)
			run.run(this);
	}
	
	public void stop() {
		if (started) {
			started = false;
			Bukkit.getScheduler().cancelTask(taskId);
		}
	}
	
	public boolean isRunning() {
		return started;
	}
}