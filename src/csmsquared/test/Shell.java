package csmsquared.test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import csmsquared.main.ChronoTimer;
import csmsquared.main.Time;
import csmsquared.sensor.Sensor;


public class Shell {
	private static final String INPUT_LINE_INDICATOR = ">> ";
	private static final String SYSTEM_OFF_ALERT = "ChronoTimer system is OFF";
	
	private ChronoTimer chrono;
	private List<Sensor> sensors;
	
	private String[] args;
	
	public Shell(){
		// The ChronoTimer is considered off if it is null
		chrono = null;
		
		sensors = new ArrayList<Sensor>(ChronoTimer.NUM_CHANNELS);
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);
		boolean running = true;
		
		// The running loop
		while(running){
			System.out.print(INPUT_LINE_INDICATOR);
			args = in.nextLine().split(" ");
			args[0] = args[0].toUpperCase();
			
			switch(args[0]){
			case "EXIT":
				// Exits the simulator
				running = false;
				break;
				
			case "ON":
				// Turns the ChronoTimer system on
				chrono = new ChronoTimer();
				System.out.println("ChronoTimer 1009 is ON");
				break;
				
			case "OFF":
				// Turns the ChronoTimer system off (simulator remains on)
				chrono = null;
				System.out.println("ChronoTimer 1009 is OFF");
				break;
				
			default:
				if(!isChronoOn())
					System.out.println(SYSTEM_OFF_ALERT);
				else
					execute(args);
			}
		}
		
		in.close();
	}
	
	/**
	 * Executes the command passed
	 * @param arg the array of command argument strings
	 */
	private void execute(String[] arg){
		switch(arg[0]){
		case "RESET":
			// Resets the system to its initial state
			chrono = new ChronoTimer();
			break;
			
		case "TIME":
			Time.setTime(arg[1]);
			
			// Sets the current time
			// TODO: Command 'TIME'
			
			break;
			
		case "TOG":
			// Toggles the state of a specified channel
			// TODO: Command 'TOG'
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
			// TODO: Command 'CONN'
			if(isNum(arg[1])){
				try{
					int index = Integer.parseInt(arg[1]);
					Sensor sensor = new Sensor();
					try{
						sensors.add(index - 1, sensor);
					} catch(Exception e){
						System.out.println("No such sensor");
					}
					chrono.connect(index, sensor);
				} catch(NoSuchElementException e){
					System.out.println(e.getMessage());
				}
			}
			
			break;
			
		case "DISC":
			// Disconnects a sensor from a specified channel
			// TODO: Command 'DISC'
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
			// TODO: Command 'NEWRUN'
			try{
				chrono.newRun();
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "ENDRUN":
			// Ends the current run
			// TODO: Command 'ENDRUN'
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
				System.out.println("No run specified");
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
			// Start trigger channel 1
			// TEST: Command 'START'
			try {
				sensors.get(0).trip();
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}

			break;
			
		case "FINISH":
			// Finish trigger channel 2
			// TEST: Command 'FINISH'
			try {
				sensors.get(1).trip();
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}

			break;
			
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
