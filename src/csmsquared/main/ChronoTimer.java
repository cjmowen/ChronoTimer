package csmsquared.main;

import java.util.ArrayList;
import java.util.Queue;

public class ChronoTimer {
	private ArrayList<Run> runs;
//	private ArrayList<Channel> channels;
	private ArrayList<Racer> racer;
	private boolean run;
	private int currentIndex;
	public ChronoTimer()
	{
		currentIndex = -1;
		runs = new ArrayList<Run>();
		racer = new ArrayList<Racer>();
	}
	
	public void num(Racer r)
	{
		racer.add(r);
		
	}
	
	public void newRun()
	{
		
		if(!run) throw new IllegalStateException("Must End the previous Run");
		runs.add(new Run());
		currentIndex++;
		run = true;
	}
	
	public void endRun()
	{
		run = false;
	}
	
	public void start(){
		
		if(racer.isEmpty())
			throw new IllegalStateException("There are no racer in the queue");
		
		
		Racer race = racer.get(0);
		runs.get(currentIndex).addRacer(race);
		racer.remove(0);
		
		
	}
	
	
	
}
