package csmsquared.test;

import csmsquared.main.Printer;
import csmsquared.main.Time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class TestFromFile {
	
	
	public static void main(String[] args){
	
		Shell shell = new Shell();
		String x;
		try{
					FileReader fin = new FileReader(new File("inputTest.txt"));
					BufferedReader buf = new BufferedReader(fin);
					try {
						
						x = buf.readLine();
						
						
						while(x != null)
						{
							
							String [] arg = x.split("\t");
							
							Time.setTime(arg[0]);
							
							String result[] = arg[1].split(" ");
							
							shell.execute(result);
							
							x = buf.readLine();
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println(x);
		}catch(FileNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		
	
	}

}
