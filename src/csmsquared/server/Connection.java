package csmsquared.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

public class Connection {

	public static void main(String[] args)
	{
		Connection x = new Connection();
		x.sendData(1);
		x.sendData(2);

	}
	
	public void sendData(int num)
	{
		HttpURLConnection conn;
		DataOutputStream out;
		
		try{
			URL site = new URL("http://chronotimerserver.appspot.com/data");
			conn= (HttpURLConnection) site.openConnection();
			conn.setDoOutput(true);
			out = new DataOutputStream(conn.getOutputStream());
			
			
			conn.setRequestMethod("POST");
			
			conn.setDoInput(true);
		
		
			JsonObject value = new JsonObject();
	//		value.addProperty("runNumber", run.getRunNumber());
	//		
	//		JsonArray arr = new JsonArray();
	//		for(Racer racer : run.getRacers())
	//		{
	//			JsonObject r = new JsonObject();
	//			r.addProperty("id", racer.getElapsedTime());
	//			arr.add(r);
	//		}
	//		
	//		value.add("racers", arr);
			
			value.addProperty("num", num);
			
			JsonArray jsonArr = new JsonArray();
			for(int i = 0; i < 10; ++i) {
				JsonObject tmp = new JsonObject();
				tmp.addProperty("number", i);
				
				jsonArr.add(tmp);
			}
			
			value.add("number_array", jsonArr);
			
			String content =
					"data= " + 	value.toString();
			
			out.writeBytes(content);
			out.flush();
			out.close();
			
			
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String temp;
			do
			{
				temp = reader.readLine();			
			}while(temp == null || temp.equals(""));
			System.out.println(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
