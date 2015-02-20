package csmsquared.main;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single Run with the race times for multiple racers. Each
 * racer is unique (a racer can only race once in a Run).
 */
public class Run {
	private static final int INITIAL_CAPACITY = 10;
	
	private List<Racer> racers;
	private int effectiveSize;
	
	public Run(){
		racers = new ArrayList<Racer>(INITIAL_CAPACITY);
		effectiveSize  =0;
	}
	
	/**
	 * Add the competitor's run time
	 * @param competitor
	 * @param time the time the competitor completed the run in
	 */
	public void addRacer(Racer r){
		racers.add(r);
		effectiveSize++;
	}
	
	
	/**
	 * Check if the racer exists in the Run or not.
	 * @param r
	 * @return status of players existense.
	 */
	public boolean hasRacer(Racer r)
	{
		for(int i=0; i < effectiveSize ; i++)
		{
			if(racers.get(i).getId()==r.getId())
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the run time for the specified competitor
	 * @param competitor the competitor to get the run time for
	 * @return the competitor's run time or -1 if not found
	 */
	public long getTime(int competitor){
		
		
		for(int i=0; i < effectiveSize;i++)
		{
			if(racers.get(i).getId()==competitor) return racers.get(i).elapsedTime(); 
		}
		
		
		return -1;
		
	}
	public String toString()
	{
		String result="";
		
		for(int i=0; i< effectiveSize; i++)
		{
			result += racers.get(i).toString()+"\n";
		}
		
		return result;
	}
}
	


