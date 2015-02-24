package csmsquared.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import csmsquared.channel.Channel;
import csmsquared.channel.ChannelEvent;
import csmsquared.channel.ChannelListener;
import csmsquared.sensor.Sensor;


public class ChronoTimer
{
	public static int NUM_CHANNELS = 2; // The number of channels
	
	private ArrayList<Channel> channels;
	private LinkedList<Run> runs;
	private Queue<Racer> racerQueue; // Implemented as a LinkedList since it provides the methods to be used as a queue
	
	private boolean runExists;    // True if a run is currently going on
	private Racer currentRacer;   // The racer that is currently being timed
	
	
	/**
	 * Initializes All the local variable.
	 */
	public ChronoTimer()
	{
		// Create and fill the channel array
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		for(int i = 0; i < NUM_CHANNELS; ++i){
			Channel c = new Channel();
			c.addChannelListener(new ChannelListener() {
				
				@Override
				public void onSignalReceived(ChannelEvent e) {
					// Get the index of the channel that received the signal
					int channel = channels.indexOf(e.getChannel()) + 1;
					
					// Odd channels start timing
					if(channel % 2 == 1) start();
					
					// Even channels end timing
					else stop();
				}
			});
			
			channels.add(i, c);
		}
		
		runs = new LinkedList<Run>();
		racerQueue = new LinkedList<Racer>();
		
		runExists = false;
	}
	
	
	/**
	 * Adds Racer to Racer List.
	 * @param r : r is Integer Racer ID and it will be added to Racer's List.
	 */
	public void num(int r)
	{
		racerQueue.add(new Racer(r));
	}
	
	
	/**
	 * Creates a new run
	 * @exception IllegalStateException - if Run is already ON.
	 */
	public void newRun()
	{
		if(runExists) throw new IllegalStateException("Must End the previous Run");
		
		runs.add(new Run());
		runExists = true;
	}
	
	
	/**
	 * endRun will End the Run.
	 * @exception IllegalStateException - Run must have started.
	 */
	public void endRun()
	{
		if(!runExists) throw new IllegalStateException("Must start new Run");
		runExists = false;
	}
	
	
	/**
	 * start will take one racer out of the Racer list and start its timing.
	 * @exception IllegalStateException - If run has not started. If previous racer has not finished. And if There are no racer left in Racer List.
	 */
	public void start()
	{
		if(!runExists) throw new IllegalStateException("Must start the Run");
		if(currentRacer != null) throw new IllegalStateException("Must Stop previous racer");
		if(racerQueue.isEmpty()) throw new IllegalStateException("There are no racer in the queue");
		
		currentRacer = racerQueue.poll();
		currentRacer.start();
		
	}
	
	
	/**
	 * stop will stop the the current Race. And it will also record players ending time and adds racer to a appropriate run.
	 * @exception IllegalStateException if racer has not started yet.
	 */
	public void stop()
	{
		if(currentRacer == null) throw new IllegalStateException("Must start before you Stop");
		
		currentRacer.end();
		runs.peekLast().addRacer(currentRacer);
		
		currentRacer = null;
	}
	
	
	/**
	 * Print will print out all the racer's list in given run.
	 * Format : racerId H:M:S
	 * @param Run - Integer Given RUN
	 * @exception NoSuchElementException if the run does not exist
	 */
	public void print(int Run)
	{
		Run = Run-1;
		if(Run >= runs.size() || Run < 0) throw new NoSuchElementException("There No Run # "+(Run+1)+" found");
		System.out.print(runs.get(Run).toString());
	}
	
	
	/**
	 * Connects a given sensor to the specified channel
	 * @param channel the channel to connect the sensor to
	 * @param sensor the sensor to connect
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void connect(int channel, Sensor sensor){
		--channel; // The index of a channel is one less than its number (Channel 1 is at index 0)
		checkChannel(channel);
		
		channels.get(channel).connectSensor(sensor);
	}
	
	/**
	 * Disconnects the sensor connected to the specified channel
	 * @param channel the channel to disconnect the sensor from
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void disconnect(int channel){
		--channel;
		checkChannel(channel);
		
		channels.get(channel).disconnectSensor();
	}
	
	
	/**
	 * Toggles the specified channel on/off
	 * @param channel the channel to toggle
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void toggle(int channel){
		--channel; // The index of a channel is one less than its number (Channel 1 is at index 0)
		checkChannel(channel);
		
		channels.get(channel).toggle();
	}
	
	
	private void checkChannel(int channel){
		if(channel < 0 || channel > NUM_CHANNELS) throw new NoSuchElementException("Channel " + channel + " does not exist");
	}
}
