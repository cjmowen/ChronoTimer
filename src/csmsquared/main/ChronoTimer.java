package csmsquared.main;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ChronoTimer {
	private ArrayList<Run> runs;
//	private ArrayList<Channel> channels;
	private ArrayList<Racer> racer;
	private boolean currentRun;
	private int currentIndex;
	private Racer currentRacer;
	private boolean start;
	public ChronoTimer()
	{
		currentRun = true;
		start = false;
		currentIndex = -1;
		runs = new ArrayList<Run>();
		racer = new ArrayList<Racer>();
	}
	
	public void num(int r)
	{
		racer.add(new Racer(r));
	}
	
	public void newRun()
	{
		
		if(!currentRun) throw new IllegalStateException("Must End the previous Run");
		currentIndex++;
		runs.add(new Run());
	
		currentRun = false;
	}
	
	public void endRun()
	{
		if(currentRun!=false) throw new IllegalArgumentException("Must start new Run");
		currentRun = true;
	}
	
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
	
	public void stop()
	{
		if(!start) throw new IllegalStateException("Must start before you Stop");
		currentRacer.end();
		runs.get(currentIndex).addRacer(currentRacer);
		start = false;
	}
	
	
	public void print(int Run)
	{
		Run = Run-1;
		if(Run >= runs.size() || Run < 0) throw new NoSuchElementException("There No Run # "+Run+" found");
		System.out.print(runs.get(Run).toString());
	}
	
	
}
