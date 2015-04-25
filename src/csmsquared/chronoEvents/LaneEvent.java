package csmsquared.chronoEvents;

import csmsquared.main.Time;

public class LaneEvent extends ChronoEvent{
	private boolean isStart;
	private int lane;

	public LaneEvent(boolean isStart, int lane) {
		super(Time.getTime());
		this.lane = lane;
		this.isStart = isStart;
	}

	public boolean isStart() {
		return isStart;
	}

	public int getLane() {
		return lane;
	}
}
