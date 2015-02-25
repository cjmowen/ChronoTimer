package csmsquared.main;

public class Printer {
	// If output is null, print to stdout
	private static String output = null;
	
	
	/**
	 * Prints the given string to the printer's set output
	 * @param str the string to print
	 */
	public static void print(String str){
		if(output == null){
			System.out.println(str);
		}
		else{
			// TODO: print to the output file
		}
	}
	
	
	public static void setOutput(String output){
		// Set the output file to the specified output
	}
}
