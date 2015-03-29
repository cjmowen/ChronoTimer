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
	public static int NUM_CHANNELS = 12;	// The number of channels
	
	private ArrayList<Channel> channels;
	private LinkedList<Run> runs;
	private Queue<Racer> racerQueue;	// Implemented as a LinkedList since it provides the methods to be used as a queue
	
	private RaceType raceType;	// Default race type is Individual
//	private boolean runExists;	// True if a run is currently going on
	private Run currentRun;		// Null if there is no current run
	private Racer[] currentRacers;	// The racers currently being timed
	
	
	/**
	 * Initializes All the local variable.
	 */
	public ChronoTimer() {
		// Create and fill the channel array
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		for(int i = 0; i < NUM_CHANNELS; ++i){
			Channel c = new Channel();
			c.addChannelListener(new ChannelListener() {
				
				@Override
				public void onSignalReceived(ChannelEvent e) {
					// Get the index of the channel that received the signal
					int channelNumber = channels.indexOf(e.getChannel()) + 1;
					
					// Odd channels start timing
					if(channelNumber % 2 == 1) start(channelNumber / 2);	// channelNumber / 2 is the lane number
					
					// Even channels end timing
					else stop(channelNumber / 2);
				}
			});
			
			channels.add(i, c);
		}
		
		runs = new LinkedList<Run>();
		newRun();
		racerQueue = new LinkedList<Racer>();
		
		raceType = RaceType.Individual;
		currentRacers = new Racer[NUM_CHANNELS / 2];
	}
	
	
	/**
	 * Adds Racer to Racer List.
	 * @param r : r is Integer Racer ID and it will be added to Racer's List.
	 */
	public void num(int r) {
		racerQueue.add(new Racer(r));
	}
	
	
	/**
	 * Creates a new run
	 * @exception IllegalStateException - if Run is already ON.
	 */
	public void newRun() {
		if(runExists()) throw new IllegalStateException("Must End the previous Run");
		
		currentRun = new Run();
		runs.add(currentRun);
	}
	
	
	/**
	 * Ends the current run.
	 * @exception IllegalStateException - Run must have started.
	 */
	public void endRun() {
		if(!runExists()) throw new IllegalStateException("Must start new Run");

		// TEST: This implementation should be sufficient for all race types
		for(Racer racer : currentRacers) {
			if(racer != null) {
				currentRun.addRacer(racer);	// If the racer has not yet finished, he is automatically counted as DNF
			}
		}
		
		currentRun = null;
	}
	
	
	/**
	 * start will take one racer out of the Racer list and start its timing.
	 * @exception IllegalStateException - If run has not started or if There are no racer left in Racer List.
	 */
	public void start(int lane) {
		// TEST: Implement different race types
		if(!runExists()) throw new IllegalStateException("Must start the Run.");
		if(lane < 0 || lane >= currentRacers.length) throw new IllegalArgumentException("Lane " + lane + " does not exist.");
		if(racerQueue.isEmpty()) throw new IllegalStateException("There are no racer in the queue.");
		
		switch (raceType) {
		case Individual:
			// Only lane 0 is used for individual events. Ignore all other channels.
			if(lane == 0) {
				Racer racer = currentRacers[0];
				if(racer != null) currentRun.addRacer(racer);
				
				currentRacers[0] = racer = racerQueue.poll();
				racer.start();
			}
			break;
		case Group:
			// Any start channel can trigger a group event to start
			for(Racer racer : currentRacers) {
				racer = racerQueue.poll();
				if(racer == null) break;
				
				racer.start();
			}
			break;
		case ParallelIndividual:
			// Parallel individual race type not yet supported
			break;
		case ParallelGroup:
			// Parallel group race type not yet supported
			break;
		default:
			// TODO: fill in with some default behavior
			break;
		}
		
	}
	
	
	/**
	 * Stops timing for the specified lane.
	 * @param lane the lane to stop.
	 * @exception IllegalArgumentException if the lane does not exist.
	 */
	public void stop(int lane) {
		// TEST: This implementation should work for all race types
		if(lane < 0 || lane >= NUM_CHANNELS / 2) throw new IllegalArgumentException("Lane " + lane + " does not exits.");
		if(currentRacers[lane] == null) return;
		
		currentRacers[lane].end();
		currentRun.addRacer(currentRacers[lane]);
		
		currentRacers[lane] = null;
	}
	
	
	/**
	 * Changes the type of race to be timed.
	 * @param type the type of race.
	 * @exception IllegalStateException if there is an ongoing run.
	 */
	public void setRaceType(RaceType type) {
		if(runExists()) throw new IllegalStateException("Must end the current run before changing race type.");
		
		raceType = type;
	}
	
	
	/**
	 * 
	 * @return the current type of race being timed.
	 */
	public RaceType getRaceType() {
		return raceType;
	}
	
	
	/**
	 * Prints all of the times in the current/last run
	 * in the format &lthours&gt:&ltminutes&gt:&ltseconds&gt.&ltcentiseconds&gt.
	 */
	public void print() {
		print(runs.size());
	}
	
	
	/**
	 * Prints all the times listed in the given run.
	 * Format : racerId HH:MM:SS.ss
	 * @param Run - Integer Given RUN
	 * @exception NoSuchElementException if the run does not exist
	 */
	public void print(int run) {
		run = run-1;
		if(run >= runs.size() || run < 0) throw new NoSuchElementException("There No Run # "+(run+1)+" found");
//		System.out.print(runs.get(run).toString());
		Printer.print(runs.get(run).toString());
	}
	
	
	/**
	 * Connects a given sensor to the specified channel
	 * @param channel the channel to connect the sensor to
	 * @param sensor the sensor to connect
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void connect(int channel, Sensor sensor) {
		--channel; // The index of a channel is one less than its number (Channel 1 is at index 0)
		checkChannel(channel);
		
		channels.get(channel).connectSensor(sensor);
	}
	
	
	/**
	 * Disconnects the sensor connected to the specified channel
	 * @param channel the channel to disconnect the sensor from
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void disconnect(int channel) {
		--channel;
		checkChannel(channel);
		
		channels.get(channel).disconnectSensor();
	}
	
	
	/**
	 * Toggles the specified channel on/off
	 * @param channel the channel to toggle
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void toggle(int channel) {
		--channel; // The index of a channel is one less than its number (Channel 1 is at index 0)
		checkChannel(channel);
		
		channels.get(channel).toggle();
	}
	
	
	private void checkChannel(int channel ) {
		if(channel < 0 || channel > NUM_CHANNELS) throw new NoSuchElementException("Channel " + channel + " does not exist");
	}
	
	
	private boolean runExists() {
		return currentRun != null;
	}
}
