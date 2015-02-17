package csmsquared.main;

/**
 * This class represents a single Run with the race times for multiple racers. Each
 * racer is unique (a racer can only race once in a Run).
 */
public class Run {
	
	
	/**
	 * 
	 */
	protected class RunTime{
		private static final int MAX_COMPETITOR_NUMBER = 99999;
		
		int competitor;
		long time;
		
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
