package csmsquared.main;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single Run with the race times for multiple racers. Each
 * racer is unique (a racer can only race once in a Run).
 */
public class Run {
	private static final int INITIAL_CAPACITY = 10;
	
	private List<RunTime> times;
	
	
	public Run(){
		times = new ArrayList<RunTime>(INITIAL_CAPACITY);
	}
	
	/**
	 * Add the competitor's run time
	 * @param competitor
	 * @param time the time the competitor completed the run in
	 */
	public void addTime(int competitor, long time){
		times.add(new RunTime(competitor, time));
	}
	
	/**
	 * Returns the run time for the specified competitor
	 * @param competitor the competitor to get the run time for
	 * @return the competitor's run time or -1 if not found
	 */
	public long getTime(int competitor){
		for(RunTime t : times){
			if(t.getCompetitor() == competitor)
				return t.getTime();
		}
		
		return -1;
	}
	
	
	/**
	 * Stores the run time for a single competitor
	 */
	protected class RunTime{
		private static final int MAX_COMPETITOR_NUMBER = 99999;
		
		int competitor;
		long time;
		
		/**
		 * Creates an entry for a competitor's time
		 * @param competitor the competitor's id in the range [0, 9999]
		 * @param time the time they completed the run in (must be >0)
		 */
		protected RunTime(int competitor, long time){
			if(competitor > MAX_COMPETITOR_NUMBER || competitor < 0) 
				throw new IllegalArgumentException("Competitor number must be in the range [0, " + MAX_COMPETITOR_NUMBER + "]");
			if(time < 0) 
				throw new IllegalArgumentException("Run time must be non-negative");
			
			this.competitor = competitor;
			this.time = time;
		}
		
		int getCompetitor(){
			return competitor;
		}
		
		long getTime(){
			return time;
		}
	}
}
