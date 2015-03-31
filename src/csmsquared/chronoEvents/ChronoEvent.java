package csmsquared.chronoEvents;

public abstract class ChronoEvent {
	long timeStamp;
	
	public ChronoEvent(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
}
