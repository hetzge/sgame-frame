package de.hetzge.sgame.frame;

import java.io.Serializable;

public interface IF_FrameEvent extends Serializable {

	int getFrameId();

	void execute();

}
