package csmsquared.test;
import java.util.NoSuchElementException;
import java.util.Scanner;

import csmsquared.main.ChronoTimer;
import csmsquared.main.RaceType;
import csmsquared.main.Time;
import csmsquared.sensor.EyeSensor;
import csmsquared.sensor.GateSensor;
import csmsquared.sensor.PadSensor;
import csmsquared.sensor.Sensor;


public class Shell {
	private static final String INPUT_LINE_INDICATOR = ">> ";
	private static final String SYSTEM_OFF_ALERT = "ChronoTimer system is OFF";
	
	private ChronoTimer chrono;
//	private List<Sensor> sensors;
	private Sensor[] sensors;
	
	private String[] args;
	
	private boolean running;
	
	public Shell(){
		// The ChronoTimer is considered off if it is null
		chrono = null;
		
//		sensors = new ArrayList<Sensor>(ChronoTimer.NUM_CHANNELS);
		sensors = new Sensor[ChronoTimer.NUM_CHANNELS];
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);
		running = true;
		
		// The running loop
		while(running){
			System.out.print(INPUT_LINE_INDICATOR);
			args = in.nextLine().split(" ");
			
			execute(args);
		}
		
		in.close();
	}
	
	/**
	 * Executes the command passed
	 * @param arg the array of command argument strings
	 */
	public void execute(String[] arg){
		
		for(int i = 0; i < arg.length; ++i){
			arg[i] = arg[i].toUpperCase();
		}
		
		// Catch shell-related commands
		switch(arg[0]){
		case "EXIT":
			// Exits the simulator
			running = false;
			return;
			
		case "ON":
			// Turns the ChronoTimer system on
			chrono = new ChronoTimer();
			System.out.println("ChronoTimer 1009 is ON");
			return;
			
		case "OFF":
			// Turns the ChronoTimer system off (simulator remains on)
			chrono = null;
			System.out.println("ChronoTimer 1009 is OFF");
			return;
			
		default:
			if(!isChronoOn()){
				System.out.println(SYSTEM_OFF_ALERT);
				return;
			}
		}
		
		// Catch ChronoTimer-related commands
		switch(arg[0]){
		case "RESET":	// Resets the system to its initial state
			chrono = new ChronoTimer();
			break;
			
		case "TIME":	// Sets the current time
			Time.setTime(arg[1]);
			break;
			
		case "TOGGLE":	// Toggles the state of a specified channel
		case "TOG":
			if(isNum(arg[1])){
				try{
					chrono.toggle(Integer.parseInt(arg[1]));
				} catch(NoSuchElementException e){
					System.out.println(e.getMessage());
				}
			}
			else{
				System.out.println(arg[1] + " is not a valid channel");
			}
			
			break;
			
		case "CONN":	// Connects a specified sensor type to a specified channel
			// Check that there are enough arguments
			if(arg.length < 3) {
				System.out.println("Not enough arguments for command CONN");
				break;
			}
			
			String sensorType = arg[1];
			Sensor sensor;
			int channel;
			
			// Check that the channel argument is a valid integer
			if(!isNum(arg[2])){
				System.out.println(arg[2] + " is not a valid integer");
				break;
			}
			else{
				channel = Integer.parseInt(arg[2]);
			}
			
			// Check that channel is a valid channel number
			if(channel < 1 || channel > ChronoTimer.NUM_CHANNELS){
				System.out.println(channel + " is not a valid channel");
				break;
			}
			
			// Check the type of sensor
			if(sensorType.equals("GATE")) sensor = new GateSensor();
			else if(sensorType.equals("EYE")) sensor = new EyeSensor();
			else if(sensorType.equals("PAD")) sensor = new PadSensor();
			else{
				System.out.println(sensorType + " is not a recognized type of sensor");
				break;
			}
			
			// Add the sensor
			sensors[channel - 1] = sensor;
			chrono.connect(channel, sensor);
			
			break;
			
		case "DISC":	// Disconnects a sensor from a specified channel
			if(isNum(arg[1])){
				try{
					int index = Integer.parseInt(arg[1]);
					chrono.disconnect(index);
				} catch(NoSuchElementException e){
					System.out.println(e.getMessage());
				}
			}
			break;
			
		case "EVENT":	// Sets the event type (IND | PARIND | GRP | PARGRP)
			if(arg.length > 1) {
				try {
					String eventType = arg[1];
					switch(eventType) {
					case "IND":
						chrono.setRaceType(RaceType.Individual);
						break;
					case "GRP":
						chrono.setRaceType(RaceType.Group);
						break;
					case "PARIND":
//						chrono.setRaceType(RaceType.ParallelIndividual);
						System.out.println(eventType + " not yet supported");
						break;
					case "PARGRP":
//						chrono.setRaceType(RaceType.ParallelGroup);
						System.out.println(eventType + " not yet supported");
						break;
					default:
						System.out.println(eventType + " is not a valid event type");
					}
				} catch (IllegalStateException e) {
					System.out.println("Cannot change the event type of an ongoing run");
				}
			}
			else {
				System.out.println("Must specify an event type (IND | GRP | PARIND | PARGRP)");
			}
			break;
			
		case "NEWRUN":	// Creates a new run
			try{
				chrono.newRun();
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "ENDRUN":	// Ends the current run
			try{
				chrono.endRun();
			} catch(IllegalStateException e){
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "PRINT":	// Prints the specified run
			// Checks if the run number argument exists
			if(arg.length > 1){
				// Checks that it is a valid number
				if(isNum(arg[1])){
					try {
						chrono.print(Integer.parseInt(arg[1]));
					} catch (NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
				}
				else{
					System.out.println(arg[1] + " is not a recognized run number");
				}
			}
			else{
				chrono.print();
			}
			break;
			
		case "EXPORT":	// Export the run to an XML file
			// TODO: Command 'EXPORT'
			
			break;
			
		case "NUM":	// Sets the specified competitor number as the next competitor to start
			if(arg.length > 1 && isNum(arg[1])){
				try{
					chrono.num(Integer.parseInt(arg[1]));
				} catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
			else{
				System.out.println("Enter a valid competitor number");
			}
			
			break;
			
		case "CLR":	// Clears the specified competitor number as the next competitor
			// TODO: Command 'CLR'
			
			break;
			
		case "SWAP":	// Exchange the next two competitors to finish
			// TODO: Command 'SWAP'
			
			break;
			
		case "RCL":	// Recalls the last triggered event
			// TODO: Command 'RCL'
			
			break;
			
		case "START":	// Triggers channel 1
			try {
				sensors[0].trip();
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}

			break;
			
		case "FINISH":	// Triggers channel 1
		case "FIN":
			try {
				sensors[1].trip();
			} catch (NullPointerException e) {
				System.out.println("No sensor connected to channel 1");
			}

			break;
			
		case "TRIG":	// Triggers the specified channel
			if(arg.length > 1) {
				if(isNum(arg[1])) {
					int channelNumber = Integer.parseInt(arg[1]);
					try {
						chrono.trigger(channelNumber);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					System.out.println(arg[1] + " is not an integer.");
				}
			}
			break;
		default:
			System.out.println("Unknown command: " + arg[0].toUpperCase());
		}
	}
	
	private boolean isNum(String str){
		for(int i = 0; i < str.length(); ++i){
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		
		return true;
	}
	
	private boolean isChronoOn(){
		return chrono != null;
	}
	
	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.run();
	}
}
