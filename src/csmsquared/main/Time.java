package csmsquared.main;
import java.lang.Math;

public class Time 
{
	private static long currentTime = -1;
	
	public static void setTime(String time){
		if(time.equalsIgnoreCase("reset")){
			currentTime = -1;
		}
		else{
			currentTime = toMillis(time);
		}
	}
	
	public static long getTime(){
		if(currentTime < 0) return System.currentTimeMillis();
		return currentTime;
	}
	

	
	public static long toMillis(String time)
	{
		long result = 0;
		int hourcolon = time.indexOf(':');
		int semicolon =  time.lastIndexOf(':');
		int period = time.indexOf('.');
		
		String hou = time.substring(0, time.indexOf(':'));
		long hours = Integer.parseInt(hou);

		String mins = time.substring(hourcolon+1, time.lastIndexOf(':'));
		long minutes = Integer.parseInt(mins);
		
		String secs;
		long seconds;
		String digs;
		long digits;
		
		if(period != -1){
			secs = time.substring(semicolon+1, period);
			seconds = Integer.parseInt(secs);
		
			digs = time.substring(period+1);
			digits = Integer.parseInt(digs);
		
		}
		else{
			secs = time.substring(semicolon+1);
			seconds = Integer.parseInt(secs);
			digits = 0;
		}
		
		result = (hours*3600000) + (minutes*60000) + (seconds*1000) + (digits * 10);
		
		
		
		return result;	
	}
	public static String toString(long milliseconds)
	{
		String result;
		long hours = milliseconds/3600000;
		long minutes = milliseconds/60000;
		long seconds = (milliseconds/1000)%60;
		long digits = Math.round((milliseconds/10.0)%100);
		result = hours + ":" + minutes + ":" + seconds + "." + digits;
		
		return result;
	}
}
