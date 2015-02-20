package csmsquared.main;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ChronoTimer {
	private ArrayList<Run> runs;
//	private ArrayList<Channel> channels;
	private ArrayList<Racer> racer;
	private boolean run;
	private int currentIndex;
	private Racer currentRacer;
	private boolean start;
	public ChronoTimer()
	{
		start = false;
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
		if(start) throw new IllegalStateException("Must Stop previous racer");
		
		if(racer.isEmpty())
			throw new IllegalStateException("There are no racer in the queue");
		
		currentRacer=racer.get(0);
		currentRacer.start();

		if(!runs.get(currentIndex).hasRacer(currentRacer))
			throw new IllegalStateException("Racer as already ran in the same Race. Can not run twice");
		
		racer.remove(0);
		start = true;
		
	}
	
	public void stop()
	{
		if(start) throw new IllegalStateException("Must start before you Stop");
		currentRacer.end();
		runs.get(currentIndex).addRacer(currentRacer);
		start = false;
	}
	
	
	public void print(int Run)
	{
		if(Run >= runs.size() || Run < 0) throw new NoSuchElementException("No ");
		System.out.print(runs.get(Run).toString());
	}
	
	
}
