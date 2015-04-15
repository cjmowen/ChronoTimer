package csmsquared.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	public static final int NUM_CHANNELS = 12;	// The number of channels

	private List<ChronoListener> listeners;
	private ArrayList<Channel> channels;
	private LinkedList<Run> runs;
	private Queue<Racer> racerQueue;	// Implemented as a LinkedList since it provides the methods to be used as a queue

	AbstractRace race;	// Default race type is Individual
	private Run currentRun;		// Null if there is no current run
	private Racer[] currentRacers;	// The racers currently being timed
	


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
	 * Add a racer with the specified ID to the queue of racers.
	 * @param id The ID of the racer to add.
	 */
	public void num(int id) {
		racerQueue.add(new Racer(id));
	}

	/**
	 * Initialize a new run.
	 * @exception IllegalStateException A run is already in progress.
	 */
	public void newRun() {
		if(runExists()) throw new IllegalStateException("Must End the previous run.");

		currentRun = new Run(runs.size()+1 , getRaceType());
		runs.add(currentRun);
	}

	/**
	 * End the current run.
	 * @exception IllegalStateException There is no run to end.
	 */
	public void endRun() {
		if(!runExists()) throw new IllegalStateException("Must start new run.");

		Racer racer;
		for(int i = 0; i < currentRacers.length; ++i) {
			racer = currentRacers[i];
			if(racer == null) continue;

			racer.didNotFinish();
			currentRun.addRacer(racer);

			currentRacers[i] = null;
			notifyObservers(i + 1, false);	// Notify observers that the person has ended
		}
		currentRun.sendData();
		currentRun = null;
	}

	/**
	 * Start a racer in the first lane only.
	 */
	public void start() {
		start(1);
	}

	/**
	 * Start a racer in the specified lane.
	 * @param lane The lane to start.
	 */
	public void start(int lane) {		
		race.start(lane);
		notifyObservers(lane, true);
	}

	/**
	 * Stop the racer in the first lane only.
	 */
	public void stop() {
		stop(1);
	}

	/**
	 * Stop the racer in the specified lane.
	 * @param lane The lane to stop.
	 */
	public void stop(int lane) {
		race.stop(lane);
		notifyObservers(lane, false);
	}

	/**
	 * Set the type of event to the specified race type.
	 * @param raceType The type of race.
	 * @exception IllegalStateException There is an ongoing run.
	 */
	public void setRaceType(RaceType raceType) {
		if(runExists() && currentRun.hasRacers())	// Don't change the race type if the race has already started
			throw new IllegalStateException("Cannot change race types in the middle of a run.");

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

		if(currentRun != null) currentRun.setRaceType(raceType);	// If there is a current run, change its race type
	}

	/**
	 * Get the current race type.
	 * @return The current type of race.
	 */
	public RaceType getRaceType() {
		return race.getRaceType();
	}

	/**
	 * Get a list of IDs for the racers queued to run.
	 * @return The racer IDs.
	 */
	public LinkedList<Integer> getRacersInQueue() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(Racer racer : racerQueue) {
			list.add(racer.getId());
		}

		return list;
	}

	/**
	 * Get a list of actively competing racers as well as their current times.
	 * @return A list of strings representing the current racers and their run times.
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
	 * Get a list of strings representing the racers who have finished (or failed to finish) the run.
	 * @return A list of strings representing finished racers.
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
	 * Print all of the times in the current/last run in the format &lthours&gt:&ltminutes&gt:&ltseconds&gt.&ltcentiseconds&gt.
	 * @return A string representation of the run.
	 */
	public String print() {
		return print(runs.size());
	}

	/**
	 * Print all the times listed in the given run in the format &lthours&gt:&ltminutes&gt:&ltseconds&gt.&ltcentiseconds&gt.
	 * @param run The run to print
	 * @return A string representation of the run.
	 * @exception NoSuchElementException The run does not exist.
	 */
	public String print(int run) {
		run = run-1;
		if(run >= runs.size() || run < 0) throw new NoSuchElementException("Run " + (run+1) + " does not exist.");

		String runString = runs.get(run).toString();
		Printer.print(runString);
		return runString;
	}

	/**
	 * Export the data for the specified run to exportdata.txt. If exportdata.txt already exists,
	 * append the run data to the existing file.
	 * @param run The run to export.
	 * @throws IOException
	 */
	public void export(int run) throws IOException {
		if(run < 1 || run > runs.size()) throw new NoSuchElementException("Run " + run + " does not exist");

		PrintWriter pw = new PrintWriter(new FileWriter("exportdata.txt", true));	// Will append the output to exportdata.txt
		pw.println(runs.get(run - 1));

		pw.close();
	}

	/**
	 * Connect the given sensor to the specified channel.
	 * @param channel The channel to connect to.
	 * @param sensor The sensor to connect.
	 * @exception NoSuchElementException The channel does not exist.
	 */
	public void connect(int channel, Sensor sensor) {
		checkChannel(channel);
		channels.get(channel - 1).connectSensor(sensor);
	}

	/**
	 * Disconnect the sensor connected to the specified channel.
	 * @param channel The channel to disconnect from.
	 * @exception NoSuchElementException The channel does not exist
	 */
	public void disconnect(int channel) {
		--channel;
		checkChannel(channel);
		channels.get(channel).disconnectSensor();
	}

	/**
	 * Toggle the specified channel on/off.
	 * @param channel The channel to toggle.
	 * @exception NoSuchElementException If the channel does not exist.
	 */
	public void toggle(int channel) {
		checkChannel(channel);
		channels.get(channel - 1).toggle();
	}

	/**
	 * Check if the channel is active.
	 * @param channel The channel to check.
	 * @return True if the channel is active.
	 */
	public boolean isChannelActive(int channel) {
		checkChannel(channel);
		return channels.get(channel).isActive();
	}

	/**
	 * Trigger the specified channel.
	 * @param channel The channel to trigger.
	 */
	public void trigger(int channel) {
		checkChannel(channel);
		channels.get(channel - 1).trigger();
	}

	/**
	 * Add a listener to the ChronoTimer.
	 * @param listener The listener to add.
	 */
	public void addActionListener(ChronoListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove the listener from ChronoTimer.
	 * @param listener The listener to remove.
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
	 * Check that the ChronoTimer is in a valid state to start.
	 * Verify that:
	 *    - There exists a current run
	 *    - The lane in question exists
	 *    - There exist racers in the queue
	 * @param lane The lane to start.
	 * @exception IllegalStateException No current run exists or there are no racers in the queue to run.
	 * @exception IllegalArgumentException The method was passed a lane number the range [1, 12].
	 */
	private void verifyStartConditions(int lane) {
		if(!runExists()) throw new IllegalStateException("Must start the Run.");
		if(lane < 1 || lane > currentRacers.length) throw new IllegalArgumentException("Lane " + lane + " does not exist.");
		if(racerQueue.isEmpty()) throw new IllegalStateException("There are no racers in the queue.");
	}

	/**
	 * Check that the ChronoTimer is in a valid state to stop.
	 * Verify that:
	 *    - The lane in question exists
	 *    - There exists a racer in that lane
	 * @param lane The lane to start.
	 * @exception IllegalArgumentException The method was passed a lane number outside the range [1, 12].
	 */
	private void verifyStopConditions(int lane) {
		if(lane < 1 || lane > NUM_CHANNELS / 2) throw new IllegalArgumentException("Lane " + lane + " does not exist.");
		if(currentRacers[lane - 1] == null) throw new NoSuchElementException("There is no racer in lane " + lane);
	}

	/**
	 * Stop in a way that is applicable to all race types.
	 * This method assumes verifyStopConditions(int) has already been called.
	 * @param lane The lane to stop.
	 */
	private void genericStop(int lane) {
		currentRacers[--lane].end();	// End the racer
		currentRacers[lane] = null;		// Remove the from the list of current racers
	}

	/**
	 * Start a given racer in the specified lane. If a racer is already in that lane, mark them as DNF and
	 * then start the specified racer.
	 * @param racer The racer to start.
	 * @param lane The lane the racer will compete in.
	 */
	private void startInLane(Racer racer, int lane) {
		--lane;
		if(currentRacers[lane] != null) currentRacers[lane].didNotFinish();	// If there was an unfinished racer in the lane, mark them as DNF

		currentRacers[lane] = racer;	// Put the racer in the lane
		currentRun.addRacer(racer);		// Add the racer to the run
		racer.start();					// Start the racer
	}

	/**
	 * Represents a race where only one person is competing at a time.
	 */
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

	/**
	 * Represents a race where a group of people all start at once.
	 */
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

	/**
	 * Represents a race where multiple people start at different times.
	 */
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

	/**
	 * I don't even know what this is supposed to represent.
	 */
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
