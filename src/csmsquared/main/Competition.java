package csmsquared.main;

public abstract class Competition {
	private final RaceType raceType;
	
	public Competition(RaceType raceType) {
		this.raceType = raceType;
	}
	
	public RaceType getRaceType() {
		return raceType;
	}
	
	public abstract void start(int lane);
	public abstract void stop(int lane);
}
