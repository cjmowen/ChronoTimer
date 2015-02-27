package csmsquared.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import csmsquared.main.Printer;
import csmsquared.main.Time;


public class TestFromFile {
	
	public static void main(String[] args){
		
		Printer.setOutput("output.txt");
	
		Shell shell = new Shell();
		String x;
		try {
			FileReader fin = new FileReader(new File("inputTest.txt"));
			BufferedReader buf = new BufferedReader(fin);

			try {
				x = buf.readLine();

				while(x != null) {
					// Split the timestamp and command
					String [] arg = x.split("\t");
					
					// Set the time
					Time.setTime(arg[0]);

					// Pass the command to the shell
					String result[] = arg[1].split(" ");
					shell.execute(result);

					x = buf.readLine();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
