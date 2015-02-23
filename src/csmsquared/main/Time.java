package csmsquared.main;
import java.lang.Math;

public class Time 
{
	private static long currentTime = -1;
	
	public static void setTime(String time){
		currentTime = toMillis(time);
	}
	
	public static long getTime(){
		if(currentTime < 0) return System.currentTimeMillis();
		return currentTime;
	}
	

	
	public static long toMillis(String time)
	{
		long result = 0;
		int semicolon =  time.indexOf(':');
		int period = time.indexOf('.');
		
		String mins = time.substring(0, time.indexOf(':'));
		long minutes = Integer.parseInt(mins);
		
		String secs = time.substring(semicolon+1, period);
		long seconds = Integer.parseInt(secs);
		
		String digs = time.substring(period+1);
		long digits = Integer.parseInt(digs);
		
		result = (minutes*60000) + (seconds*1000) + (digits * 10);
		
		
		
		return result;	
	}
	public static String toString(long milliseconds)
	{
		String result;
		long minutes = milliseconds/60000;
		long seconds = (milliseconds/1000)%60;
		long digits = Math.round((milliseconds/10.0)%100);
		result = minutes + ":" + seconds + "." + digits;
		
		return result;
	}
}
