package csmsquared.chronoEvents;

import csmsquared.main.Time;

public class LaneEvent extends ChronoEvent{
	private boolean isStart;
	
	public LaneEvent(boolean isStart) {
		super(Time.getTime());
		this.isStart = isStart;
	}

	public boolean isStart() {
		return isStart;
	}
}
