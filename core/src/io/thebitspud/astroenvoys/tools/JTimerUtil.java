package io.thebitspud.astroenvoys.tools;

public abstract class JTimerUtil {
	private double timeElapsed, timerDuration;
	private boolean autoRepeat, active;

	public JTimerUtil(double duration, boolean autoRepeat, boolean active) {
		this.timerDuration = duration;
		this.autoRepeat = autoRepeat;

		this.active = active;
	}

	public void tick(float delta) {
		if(!active) return;

		timeElapsed += delta;
		if(timeElapsed >= timerDuration) {
			onActivation();

			if(autoRepeat) timeElapsed -= timerDuration;
			else {
				timeElapsed = 0;
				active = false;
			}
		}
	}

	public abstract void onActivation();

	public double getTimeElapsed() {
		return timeElapsed;
	}

	public double getTimerDuration() {
		return timerDuration;
	}

	public boolean isAutoRepeating() {
		return autoRepeat;
	}

	public boolean isActive() {
		return active;
	}

	public void setTimeElapsed(double time) {
		this.timeElapsed = time;
	}

	public void setAutoRepeat(boolean repeat) {
		this.autoRepeat = repeat;
	}

	public void setTimerDuration(double duration) {
		this.timerDuration = duration;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}