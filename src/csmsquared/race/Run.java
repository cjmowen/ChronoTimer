package csmsquared.race;

import java.util.ArrayList;

/**
 * This class represents a single Run with the race times for multiple racers. Each
 * racer is unique (a racer can only race once in a Run).
 */
public class Run {
	private static final int INITIAL_CAPACITY = 10;

	private ArrayList<Racer> racers;
	private RaceType raceType;

	/**
	 * Constructor
	 * @param type the type of race being measured in this run.
	 */
	public Run(RaceType type){
		racers = new ArrayList<Racer>(INITIAL_CAPACITY);
		raceType = type;
	}

	/**
	 * Sets the race type of this run.
	 * @param raceType
	 */
	public void setRaceType(RaceType raceType) {
		this.raceType = raceType;
	}

	/**
	 * Add the competitor's run time
	 * @param competitor
	 * @param time the time the competitor completed the run in
	 */
	public void addRacer(Racer r){
		for(int i = 0; i<racers.size(); i++){
			if(racers.get(i).getId() == r.getId()){
				if(racers.get(i).getElapsedTime() > r.getElapsedTime()){
					racers.set(i, r);
				}
				return;
			}
		}
		racers.add(r);
	}


	/**
	 * Check if the racer exists in the Run or not.
	 * @param r
	 * @return status of players existence.
	 */
	public boolean hasRacer(Racer r)
	{
		for(int i=0; i < racers.size() ; i++)
		{
			if(racers.get(i).getId()==r.getId())
				return true;
		}
		return false;
	}

	/**
	 * Check if any racers have yet been recorded in the run.
	 * @return - True if there are recorded racers, otherwise false.
	 */
	public boolean hasRacers() {
		return !racers.isEmpty();
	}


	public ArrayList<Racer> getRacers() {
		return racers;
	}

	/**
	 * Returns the run time for the specified competitor
	 * @param competitor the competitor to get the run time for
	 * @return the competitor's run time or -1 if not found
	 */
	public long getTime(int competitor){


		for(int i=0; i < racers.size();i++)
		{
			if(racers.get(i).getId()==competitor) return racers.get(i).getElapsedTime(); 
		}


		return -1;

	}


	public String toString()
	{
		// Windows doesn't recognize '\n' as a newline, so this
		// is necessary to get it to appear in the file
		String newline = System.getProperty("line.separator");
		String result = raceType.toString() + newline;

		//		for(int i=0; i < racers.size(); i++)
		//		{
		//			result += racers.get(i).toString() + newline;
		//		}

		for(Racer racer : racers) {
			result += racer.toString() + newline;
		}

		return result;
	}
	

}



