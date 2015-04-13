package csmsquared.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

import csmsquared.race.Racer;
import csmsquared.race.Run;

public class Connection {
	Run run;
	String elapsedTime;
	DataOutputStream out;
	HttpURLConnection conn;
	public Connection()
	{	
		try{

		URL site = new URL("");
		conn= (HttpURLConnection) site.openConnection();
		out = new DataOutputStream(conn.getOutputStream());
		

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		}catch(Exception e){
			e.printStackTrace();
		}

	}


	public void sendData(Run run)
	{
		JsonObject value = new JsonObject();
		value.addProperty("runNumber", 1);
		
		JsonArray arr = new JsonArray();
		for(Racer racer : run.getRacers())
		{
			JsonObject r = new JsonObject();
			r.addProperty("id", racer.getElapsedTime());
			arr.add(r);
		}
		
		value.add("racers", arr);
		
		String content =
				"data= " + value.toString();
		
		try {
			out.writeBytes(content);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String temp = reader.readLine();
			if(!reader.equals(""))
			{
				System.out.println("bad input");
				temp=reader.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
