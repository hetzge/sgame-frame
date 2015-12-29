package de.hetzge.sgame.frame;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.pmw.tinylog.Logger;

public class Timeline {

	/*
	 * TODO GDX auslagern, App anpassen 
	 * 
	 */
	
	private final static short FRAMES_PER_SECOND = 30;
	private final static short DEFAULT_NEXT_FRAMES = FRAMES_PER_SECOND;
	private final static float FRAME_DELTA = 1f / FRAMES_PER_SECOND;

	private final Timer frameTimer;

	private Frame current = new Frame(0);
	private Queue<IF_FrameEvent> buffer = new ConcurrentLinkedQueue<>();

	public Timeline() {
		this.frameTimer = new Timer(FRAMES_PER_SECOND, this::onFrame);
	}

	public void addFrameEvent(IF_FrameEvent frameEvent) {
		this.buffer.add(frameEvent);
	}

	private void onFrame() {
		flushBuffer();
		long before = System.currentTimeMillis();
		this.current.execute();
		FrameModule.update.update();
		long executionTime = System.currentTimeMillis() - before;
		if (executionTime > this.frameTimer.getEveryMilliseconds()) {
			Logger.warn("frame executiontime is longer then frametime: " + executionTime);
		}
		this.current = this.current.next();
	}

	private void flushBuffer() {
		int size = this.buffer.size();
		for (int i = 0; i < size; i++) {
			IF_FrameEvent frameEvent = this.buffer.poll();
			this.current.addFrameEvent(frameEvent);
		}
	}

	public void startFrameTimer() {
		this.frameTimer.start();
	}

	public void stopFrameTimer() {
		this.frameTimer.stop();
	}

	public void update() {
		Util.sleep(10);
		this.frameTimer.call();
	}

	public boolean isCurrentOrPast(int frameId) {
		return getCurrentFrameId() >= frameId;
	}

	public int getCurrentFrameId() {
		return this.current.getId();
	}

	public boolean isXthFrame(int xth) {
		return getCurrentFrameId() % xth == 0;
	}

	public int getNextFrameId(int frames) {
		return getCurrentFrameId() + frames;
	}

	public int getDefaultNextFrameId() {
		return getCurrentFrameId() + DEFAULT_NEXT_FRAMES;
	}

	public float getFrameDelta() {
		return FRAME_DELTA;
	}
}
