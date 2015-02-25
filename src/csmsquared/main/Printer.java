package csmsquared.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Printer {
	// If output is null, print to stdout
	private static String output = null;
	private static PrintWriter pw;
	
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
			pw.write(str);
			pw.close();
			
		}
	}
	
	
	public static void setOutput(String o){
		// Set the output file to the specified output
	output = o;
	try {
		 pw = new PrintWriter(new File(output));
	} catch (FileNotFoundException e) {
		System.out.print(e.getMessage());
	}
	}
	
	
	
	
}
