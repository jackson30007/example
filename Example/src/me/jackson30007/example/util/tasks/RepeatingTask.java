package me.jackson30007.example.util.tasks;

import org.bukkit.Bukkit;

/*
 * Utility class to help manage bukkit's schedulers
 * */

public class RepeatingTask extends DelayedTask {
	public RepeatingTask(int delay, int period) {
		super(delay);
		this.delay = delay;
		this.period = period;
		this.infinite = true;
	}
	
	public RepeatingTask(int delay, int period, int repeats) {
		super(delay);
		this.period = period;
		this.repeats = repeats;
	}
	
	protected int period, repeats;
	protected int repeatsLeft;
	private TaskRunnable end;
	protected boolean infinite;
	
	@SuppressWarnings("unchecked")
	public <T extends RepeatingTask> T infinite(boolean infinite) {
		this.infinite = infinite;
		return (T) this;
	}
	
	@Override
	public RepeatingTask start() {
		stop();
		started = true;
		repeatsLeft = repeats;
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin(), new Runnable() {
			public void run() {
				if (infinite) {
					callRun();
					return;
				}
				
				if (repeatsLeft > 0) {
					callRun();
					repeatsLeft--;
				}
				
				if (repeatsLeft <= 0) {
					stop();
					callEnd();
				}
			}
		}, delay, period);
		return this;
	}
	
	public RepeatingTask end(TaskRunnable end) {
		this.end = end;
		return this;
	}
	
	public RepeatingTask end(Runnable end) {
		this.end = (task)->{
			end.run();
		};
		return this;
	}
	
	public RepeatingTask run(TaskRunnable run) {
		super.run(run);
		return this;
	}
	
	public RepeatingTask run(Runnable run) {
		super.run(run);
		return this;
	}
	
	protected void callEnd() {
		if (end != null)
			end.run(this);
	}
	
	public int getRepeatsLeft() {
		return repeatsLeft;
	}
	
	public void setRepeatsLeft(int repeatsLeft) {
		this.repeatsLeft = repeatsLeft;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void setPeriod(int period) {
		this.period = period;
	}
}