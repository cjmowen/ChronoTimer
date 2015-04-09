package csmsquared.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
			pw.print(str);
			//			pw.write(str);
			pw.close();

		}
	}

	/**
	 * SetOutput opens up new File with given path and name of the file.
	 * @param o : O is the name of the output file. 
	 * @exception FileNotFoundException : Will occur if given file not found. Or permission is denied.
	 */
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
