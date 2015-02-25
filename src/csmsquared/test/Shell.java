package csmsquared.test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import csmsquared.main.ChronoTimer;
import csmsquared.main.Time;
import csmsquared.sensor.EyeSensor;
import csmsquared.sensor.GateSensor;
import csmsquared.sensor.PadSensor;
import csmsquared.sensor.Sensor;


public class Shell {
	private static final String INPUT_LINE_INDICATOR = ">> ";
	private static final String SYSTEM_OFF_ALERT = "ChronoTimer system is OFF";
	
	private ChronoTimer chrono;
	private List<Sensor> sensors;
	
	private String[] args;
	
	private boolean running;
	
	public Shell(){
		// The ChronoTimer is considered off if it is null
		chrono = null;
		
		sensors = new ArrayList<Sensor>(ChronoTimer.NUM_CHANNELS);
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
		case "RESET":
			// Resets the system to its initial state
			chrono = new ChronoTimer();
			break;
			
		case "TIME":
			// Sets the current time
			Time.setTime(arg[1]);
			break;
			
		case "TOG":
			// Toggles the state of a specified channel
			if(isNum(arg[1])){
				try{
					chrono.toggle(Integer.parseInt(arg[1]));
				} catch(IndexOutOfBoundsException e){
					System.out.println(e.getMessage());
				}
			}
			else{
				System.out.println(arg[1] + " is not a valid channel");
			}
			
			break;
			
		case "CONN":
			// Connects a specified sensor type to a specified channel

			// Check that there are enough arguments
			if(arg.length < 3)
				System.out.println("Not enough arguments for command CONN");
			
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
			sensors.add(channel - 1, sensor);
			chrono.connect(channel, sensor);
			
//			if(isNum(arg[1])){
//				try{
//					int index = Integer.parseInt(arg[1]);
//					Sensor sensor = new Sensor();
//					try{
//						sensors.add(index - 1, sensor);
//					} catch(Exception e){
//						System.out.println("No such sensor");
//					}
//					chrono.connect(index, sensor);
//				} catch(NoSuchElementException e){
//					System.out.println(e.getMessage());
//				}
//			}
			
			break;
			
		case "DISC":
			// Disconnects a sensor from a specified channel
			if(isNum(arg[1])){
				try{
					int index = Integer.parseInt(arg[1]);
					chrono.disconnect(index);
				} catch(NoSuchElementException e){
					System.out.println(e.getMessage());
				}
			}
			break;
			
		case "EVENT":
			// Sets the event type (IND | PARIND | GRP | PARGRP)
			// TODO: Command 'EVENT'
			
			break;
			
		case "NEWRUN":
			// Creates a new run
			try{
				chrono.newRun();
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "ENDRUN":
			// Ends the current run
			try{
				chrono.endRun();
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "PRINT":
			// Prints the specified run to stdout\
			// Checks if the run number argument exists
			if(arg.length >= 2){
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
			
		case "EXPORT":
			// Export the run to an XML file
			// TODO: Command 'EXPORT'
			
			break;
			
		case "NUM":
			// Sets the specified competitor number as the next competitor to start
			if(args.length > 1 && isNum(args[1])){
				try{
					chrono.num(Integer.parseInt(args[1]));
				} catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
			else{
				System.out.println("Enter a valid competitor number");
			}
			
			break;
			
		case "CLR":
			// Clears the specified competitor number as the next competitor
			// TODO: Command 'CLR'
			
			break;
			
		case "SWAP":
			// Exchange the next two competitors to finish
			// TODO: Command 'SWAP'
			
			break;
			
		case "RCL":
			// Recalls the last triggered event
			// TODO: Command 'RCL'
			
			break;
			
		case "START":
			// Start timing
			try {
				sensors.get(0).trip();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			break;
			
		case "FINISH":
		case "FIN":
			// Finish timing
			try {
				sensors.get(1).trip();
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}

			break;
			
		case "DNF":
			// The racer did not finish
			try{
				chrono.didNotFinish();
			} catch(IllegalStateException e){
				System.out.println(e.getMessage());
			}
			
		case "TRIG":
			// Triggers the specified channel
			// TODO: Command 'TRIG'
			
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
