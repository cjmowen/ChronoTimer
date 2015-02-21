package csmsquared.main;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ChronoTimer {
	private ArrayList<Run> runs;
//	private ArrayList<Channel> channels;
	private ArrayList<Racer> racer;
	private boolean currentRun;
	private int currentIndex;
	private Racer currentRacer;
	private boolean start;
	
	/**
	 * Initializes All the local variable.
	 */
	public ChronoTimer()
	{
		currentRun = true;
		start = false;
		currentIndex = -1;
		runs = new ArrayList<Run>();
		racer = new ArrayList<Racer>();
	}
	
	
	/**
	 * Adds Racer to Racer List.
	 * @param r : r is Integer Racer ID and it will be added to Racer's List.
	 */
	public void num(int r)
	{
		racer.add(new Racer(r));
	}
	
	/**
	 * newRun() Will start a new Run.
	 * @exception IllegalStateException - if Run is already ON.
	 */
	
	public void newRun()
	{
		
		if(!currentRun) throw new IllegalStateException("Must End the previous Run");
		currentIndex++;
		runs.add(new Run());
	
		currentRun = false;
	}
	
	/**
	 * endRun will End the Run.
	 * @exception IllegalStateException - Run must have started.
	 */
	
	public void endRun()
	{
		if(currentRun!=false) throw new IllegalStateException("Must start new Run");
		currentRun = true;
	}
	
	/**
	 * start will take one racer out of the Racer list and start its timing.
	 * @exception IllegalStateException - If run has not started. If previous racer has not finished. And if There are no racer left in Racer List.
	 */
	
	public void start(){
		
		if(currentRun) throw new IllegalStateException("Must start the Run");
		if(start) throw new IllegalStateException("Must Stop previous racer");
		
		if(racer.isEmpty())
			throw new IllegalStateException("There are no racer in the queue");
		
		currentRacer=racer.get(0);
		currentRacer.start();

		if(runs.get(currentIndex).hasRacer(currentRacer))
			throw new IllegalStateException("Racer as already ran in the same Race. Can not run twice");
		
		racer.remove(0);
		start = true;
		
	}
	/**
	 * stop will stop the the current Race. And it will also record players ending time and adds racer to a appropriate run.
	 * @exception IllegalStateException if racer has not started yet.
	 */
	public void stop()
	{
		if(!start) throw new IllegalStateException("Must start before you Stop");
		currentRacer.end();
		runs.get(currentIndex).addRacer(currentRacer);
		start = false;
	}
	/**
	 * Print will print out all the racer's list in given run.
	 * Format : racerId H:M:S
	 * @param Run - Integer Given RUN
	 */
	
	public void print(int Run)
	{
		Run = Run-1;
		if(Run >= runs.size() || Run < 0) throw new NoSuchElementException("There No Run # "+(Run+1)+" found");
		System.out.print(runs.get(Run).toString());
	}
	
	
}
