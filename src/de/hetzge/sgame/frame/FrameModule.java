package de.hetzge.sgame.frame;

public class FrameModule {

	public static final Timeline instance = new Timeline();

	public static IF_Update update = () -> System.out.println("empty frame");

}
