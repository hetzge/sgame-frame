package de.hetzge.sgame.frame;

public class FrameSetup {

	private IF_Update update = () -> System.out.println("empty frame");
	
	public void setUpdate(IF_Update update) {
		this.update = update;
	}
	
	public IF_Update getUpdate() {
		return this.update;
	}
	
}
