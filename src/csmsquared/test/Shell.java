package csmsquared.test;
import java.util.Scanner;

import csmsquared.main.ChronoTimer;


public class Shell {
	private static final String INPUT_LINE_INDICATOR = ">> ";
	private static final String SYSTEM_OFF_ALERT = "ChronoTimer system is OFF";
	
	private ChronoTimer chrono;
	private String[] args;
	
	public Shell(){
		// The ChronoTimer is considered off if it is null
		chrono = null;
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
	 * Executes the command passed via the String array args.
	 * @param args
	 */
	private void execute(String[] args){
		switch(args[0]){
		case "RESET":
			// Resets the system to its initial state
			// TODO: Command 'RESET'
			
			break;
			
		case "TIME":
			// Sets the current time
			// TODO: Command 'TIME'
			
			break;
			
		case "TOG":
			// Toggles the state of a specified channel
			// TODO: Command 'TOG'
			
			break;
			
		case "CONN":
			// Connects a specified sensor type to a specified channel
			// TODO: Command 'CONN'
			
			break;
			
		case "DISC":
			// Disconnects a sensor from a specified channel
			// TODO: Command 'DISC'
			
			break;
			
		case "EVENT":
			// Sets the event type (IND | PARIND | GRP | PARGRP)
			// TODO: Command 'EVENT'
			
			break;
			
		case "NEWRUN":
			// Creates a new run
			// TODO: Command 'NEWRUN'
			
			break;
			
		case "ENDRUN":
			// Ends the current run
			// TODO: Command 'ENDRUN'
			
			break;
			
		case "PRINT":
			// Prints the specified run to stdout
			// TODO: Command 'PRINT'
			
			break;
			
		case "EXPORT":
			// Export the run to an XML file
			// TODO: Command 'EXPORT'
			
			break;
			
		case "NUM":
			// Sets the specified competitor number as the next competitor to start
			// TODO: Command 'NUM'
			
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
			// TODO: Command 'START'
			
			break;
			
		case "FINISH":
			// Finish trigger channel 2
			// TODO: Command 'FINISH'
			
			break;
			
		case "TRIG":
			// Triggers the specified channel
			// TODO: Command 'TRIG'
			
			break;
		default:
			System.out.println("Unknown command: " + args[0].toUpperCase());
		}
	}
	
	private boolean isChronoOn(){
		return chrono != null;
	}
	
	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.run();
	}
}
