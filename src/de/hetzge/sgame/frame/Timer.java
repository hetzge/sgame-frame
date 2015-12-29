package de.hetzge.sgame.frame;

public class Timer {

	private boolean started = false;
	private final short fps;
	private final short everyMilliseconds;
	private long timestamp = 0;
	private final Runnable execute;

	public Timer(short fps, Runnable execute) {
		this.fps = fps;
		this.execute = execute;
		this.everyMilliseconds = (short) (1000 / fps);
	}

	public void call() {
		if (this.started) {
			long currentTimeMillis = System.currentTimeMillis();
			if (currentTimeMillis > this.timestamp + this.everyMilliseconds) {
				this.timestamp = this.timestamp == 0 ? currentTimeMillis : this.timestamp + this.everyMilliseconds;
				this.execute.run();
			}
		}
	}

	public short getFps() {
		return this.fps;
	}

	public short getEveryMilliseconds() {
		return this.everyMilliseconds;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public boolean isStarted() {
		return this.started;
	}

	public void start() {
		this.started = true;
		this.timestamp = 0;
	}

	public void stop() {
		this.started = false;
	}

	public float delta() {
		return (float) (System.currentTimeMillis() - this.timestamp) / this.everyMilliseconds;
	}

}
