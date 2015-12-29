package de.hetzge.sgame.frame;

public class Test {

	
	public static void main(String[] args) {
		
		FrameModule.instance.startFrameTimer();
		
		while(true){
			FrameModule.instance.update();
		}
		
		
	}
	
}
