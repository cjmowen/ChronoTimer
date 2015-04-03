package csmsquared.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import csmsquared.channel.Channel;
import csmsquared.channel.ChannelEvent;
import csmsquared.channel.ChannelListener;
import csmsquared.chronoEvents.ChronoListener;
import csmsquared.chronoEvents.LaneEvent;
import csmsquared.race.AbstractRace;
import csmsquared.race.RaceType;
import csmsquared.race.Racer;
import csmsquared.race.Run;
import csmsquared.sensor.Sensor;


public class ChronoTimer
{
	public static int NUM_CHANNELS = 12;	// The number of channels
	
	private List<ChronoListener> listeners;
	private ArrayList<Channel> channels;
	private LinkedList<Run> runs;
	private Queue<Racer> racerQueue;	// Implemented as a LinkedList since it provides the methods to be used as a queue
		
	AbstractRace race;	// Default race type is Individual
	private Run currentRun;		// Null if there is no current run
	private Racer[] currentRacers;	// The racers currently being timed
	
	
	/**
	 * Initializes All the local variable.
	 */
	public ChronoTimer() {
		Time.setTime("RESET");	// Default to regular system time
		
		listeners = new ArrayList<ChronoListener>();
		
		// Create and fill the channel array
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		for(int i = 0; i < NUM_CHANNELS; ++i){
			Channel c = new Channel(i + 1);
			c.addChannelListener(new ChannelListener() {
				
				@Override
				public void onSignalReceived(ChannelEvent e) {
					// Get the index of the channel that received the signal
					int channelNumber = e.getChannel();
					int laneNumber = (channelNumber + 1) / 2;	// Quick and dirty ceiling to get the lane number: (1+1)/2 = 2/2 = 1; (2+1)/2 = 3/2 = 1
					
					// Odd channels start timing
					if(channelNumber % 2 == 1) start(laneNumber);
					
					// Even channels end timing
					else stop(laneNumber);
				}
			});
			
			channels.add(i, c);
		}
		
		race = new IndividualRace();
		
		runs = new LinkedList<Run>();
		newRun();
		
		racerQueue = new LinkedList<Racer>();
		
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
		if(runExists()) throw new IllegalStateException("Must End the previous run.");
		
		currentRun = new Run(getRaceType());
		runs.add(currentRun);
	}
	
	
	/**
	 * Ends the current run.
	 * @exception IllegalStateException - Run must have started.
	 */
	public void endRun() {
		if(!runExists()) throw new IllegalStateException("Must start new run.");

		// TEST: This implementation should be sufficient for all race types
		Racer racer;
		for(int i = 0; i < currentRacers.length; ++i) {
			racer = currentRacers[i];
			if(racer == null) continue;
			
			racer.didNotFinish();
			currentRun.addRacer(racer);
			
			currentRacers[i] = null;
			notifyObservers(i + 1, false);	// Notify observers that the person has ended
		}
		
		currentRun = null;
	}
	
	
	/**
	 * Starts the first lane only.
	 */
	public void start() {
		start(1);
	}
	
	/**
	 * Starts timing for the specified lane.
	 */
	public void start(int lane) {		
		race.start(lane);
		notifyObservers(lane, true);
	}
	
	
	/**
	 * Stops timing for the first lane.
	 */
	public void stop() {
		stop(1);
	}
	
	
	/**
	 * Stops timing for the specified lane.
	 * @param lane the lane to stop.
	 */
	public void stop(int lane) {
		race.stop(lane);
		notifyObservers(lane, false);
	}
	
	
	/**
	 * Changes the type of race to be timed.
	 * @param type the type of race.
	 * @exception IllegalStateException if there is an ongoing run.
	 */
	public void setRaceType(RaceType raceType) {
//		if(runExists()) throw new IllegalStateException("Must end the current run before changing race type.");
		if(currentRun != null && currentRun.hasRacers()) throw new IllegalStateException("Cannot change race types in the middle of a run.");
		
		switch(raceType) {
		case Individual:
			race = new IndividualRace();
			break;
		case Group:
			race = new GroupRace();
			break;
		case ParallelIndividual:
			race = new ParallelIndividualRace();
			break;
		case ParallelGroup:
			race = new ParallelGroupRace();
			break;
		default:
			throw new IllegalArgumentException("Unsupported race type");
		}
		
		currentRun.setRaceType(raceType);
	}
	
	
	/**
	 * Returns the current competition type.
	 * @return the current type of race being timed.
	 */
	public RaceType getRaceType() {
		return race.getRaceType();
	}
	
	
	/**
	 * Returns a list of IDs for the racers queued up to run.
	 * @return the ArrayList of racer IDs
	 */
	public LinkedList<Integer> getRacersInQueue() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(Racer racer : racerQueue) {
			list.add(racer.getId());
		}
		
		return list;
	}
	
	
	/**
	 * Returns a list of strings showing the racers currently being timed
	 * as well as their current times.
	 * @return list of strings representing the run time of current racers.
	 */
	public LinkedList<String> getCurrentRacers() {
		LinkedList<String> list = new LinkedList<String>();
		for(Racer racer : currentRacers) {
			if(racer == null) continue;
			list.add(racer.toString() + " R");
		}
		
		return list;
	}
	
	
	/**
	 * Returns a list of strings representing the racers who have finished (or failed
	 * to finish) the run.
	 * @return list of strings representing finished racers.
	 */
	public LinkedList<String> getFinishedRacers() {
		ArrayList<Racer> racers = currentRun.getRacers();
		LinkedList<String> list = new LinkedList<String>();
		for(Racer racer : racers) {
			list.add(racer.toString() + " F");
		}
		
		return list;
	}
	
	
	/**
	 * Prints all of the times in the current/last run
	 * in the format &lthours&gt:&ltminutes&gt:&ltseconds&gt.&ltcentiseconds&gt.
	 * @return a string representing the run
	 */
	public String print() {
		return print(runs.size());
	}
	
	
	/**
	 * Prints all the times listed in the given run.
	 * Format : racerId HH:MM:SS.ss
	 * @param run the run to print
	 * @return a string representing the run
	 * @exception NoSuchElementException if the run does not exist
	 */
	public String print(int run) {
		run = run-1;
		if(run >= runs.size() || run < 0) throw new NoSuchElementException("Run " + (run+1) + " does not exist.");
		
		String runString = runs.get(run).toString();
		Printer.print(runString);
		return runString;
	}
	
	
	/**
	 * Connects a given sensor to the specified channel
	 * @param channel the channel to connect the sensor to
	 * @param sensor the sensor to connect
	 * @exception NoSuchElementException if the channel does not exist
	 */
	public void connect(int channel, Sensor sensor) {
		--channel;
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
		checkChannel(channel);
		channels.get(channel - 1).toggle();
	}
	
	
	/**
	 * Triggers the specified channel.
	 * @param channel the channel
	 */
	public void trigger(int channel) {
		checkChannel(channel);
		channels.get(channel - 1).trigger();
	}
	
	
	/**
	 * Adds a listener to the ChronoTimer.
	 * @param listener the listener to add.
	 */
	public void addActionListener(ChronoListener listener) {
		listeners.add(listener);
	}
	
	
	/**
	 * Removes the listener form ChronoTimer.
	 * @param listener the listener to remove
	 */
	public void removeActionListener(ChronoListener listener) {
		listeners.remove(listener);
	}
	
	
	private void checkChannel(int channel ) {
		if(channel < 1 || channel > NUM_CHANNELS) throw new NoSuchElementException("Channel " + channel + " does not exist.");
	}
	
	
	private boolean runExists() {
		return currentRun != null;
	}
	
	private void notifyObservers(int lane, boolean isStartEvent) {
		LaneEvent event = new LaneEvent(lane, isStartEvent);
		for(ChronoListener listener : listeners) {
			listener.onLaneEvent(event);
		}
	}
	
	/**
	 * This method checks the start conditions applicable to all race types.
	 * It verifies that:
	 *    - There exists a current run
	 *    - The lane in question exists
	 *    - There exist racers in the queue
	 * @param lane the lane to start
	 * @exception IllegalStateException - No current run exists or there are no racers in the queue to run.
	 * @exception IllegalArgumentException - The method was passed a lane number the range [1, 12]
	 */
	private void verifyStartConditions(int lane) {
		if(!runExists()) throw new IllegalStateException("Must start the Run.");
		if(lane < 1 || lane > currentRacers.length) throw new IllegalArgumentException("Lane " + lane + " does not exist.");
		if(racerQueue.isEmpty()) throw new IllegalStateException("There are no racers in the queue.");
	}
	
	/**
	 * This method checks the stop conditions applicable to all race types.
	 * It verifies that:
	 *    - The lane in question exists
	 *    - There exists a racer in that lane
	 * @param lane the lane to start
	 * @exception IllegalArgumentException - The method was passed a lane number outside the range [1, 12]
	 */
	private void verifyStopConditions(int lane) {
		if(lane < 1 || lane > NUM_CHANNELS / 2) throw new IllegalArgumentException("Lane " + lane + " does not exist.");
		if(currentRacers[lane - 1] == null) throw new NoSuchElementException("There is no racer in lane " + lane);
	}
	
	/**
	 * Handles stopping in a way that is applicable to all race types.
	 * This method assumes verifyStopConditions(int) has already been called.
	 * @param lane - The lane to stop
	 */
	private void genericStop(int lane) {
		currentRacers[--lane].end();	// End the racer
		currentRacers[lane] = null;		// Remove the from the list of current racers
	}
	
	private void startInLane(Racer racer, int lane) {
		--lane;
		if(currentRacers[lane] != null) currentRacers[lane].didNotFinish();	// If there was an unfinished racer in the lane, mark them as DNF
		
		currentRacers[lane] = racer;	// Put the racer in the lane
		currentRun.addRacer(racer);	// Add the racer to the run
		racer.start();	// Start the racer
	}
	
	private class IndividualRace extends AbstractRace {

		public IndividualRace() {
			super(RaceType.Individual);
		}
		
		public void start(int lane) {
			verifyStartConditions(lane);
			
			if(lane != 1 ||	// Only the first lane is used for an individual run
					!channels.get(1).isActive()) return;	// Don't start a racer if there is no way to stop them
			
			startInLane(racerQueue.poll(), lane);	// We know that there is >= 1 racer in the queue because we passed the verifyStartConditions() method
		}

		public void stop(int lane) {
			verifyStopConditions(lane);
			if(lane != 1) return;	// Only the first lane is used in an individual run
			
			genericStop(lane);
		}
	}
	
	private class GroupRace extends AbstractRace {
		
		public GroupRace() {
			super(RaceType.Group);
		}

		@Override
		public void start(int lane) {
			verifyStartConditions(lane);
			
			// Any start channel can kick off a group run
			Racer racer;
			for(lane = 1; lane <= currentRacers.length; ++lane) {
				if(!channels.get(lane * 2 - 1).isActive()) continue;	// Do not start a racer in a lane if there is no way to stop them (i.e. the finish channel is inactive)
				
				racer = racerQueue.poll();
				if(racer == null) return;	// No more racers left in the queue
				startInLane(racer, lane);
			}
		}

		@Override
		public void stop(int lane) {
			verifyStopConditions(lane);
			
			genericStop(lane);
		}
	}
	
	private class ParallelIndividualRace extends AbstractRace {

		public ParallelIndividualRace() {
			super(RaceType.ParallelIndividual);
		}
		
		@Override
		public void start(int lane) {
			verifyStartConditions(lane);
			
			startInLane(racerQueue.poll(), lane);
		}

		@Override
		public void stop(int lane) {
			verifyStopConditions(lane);
			
			genericStop(lane);
		}
	}
	
	private class ParallelGroupRace extends AbstractRace {

		public ParallelGroupRace() {
			super(RaceType.ParallelGroup);
		}
		
		@Override
		public void start(int lane) {
			throw new UnsupportedOperationException("This race type not yet supported");
//			verifyStartConditions(lane);
			
		}

		@Override
		public void stop(int lane) {
			throw new UnsupportedOperationException("This race type not yet supported");
//			verifyStopConditions(lane);
			
		}
	}
}
