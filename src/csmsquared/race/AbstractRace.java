package csmsquared.race;


public abstract class AbstractRace {
	private final RaceType raceType;
	
	public AbstractRace(RaceType raceType) {
		this.raceType = raceType;
	}
	
	public RaceType getRaceType() {
		return raceType;
	}
	
	public abstract void start(int lane);
	public abstract void stop(int lane);
}
