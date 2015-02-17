package csmsquared.main;
import java.lang.Math;

public class Time 
{
	public static void main(String[] args)
	{
		long temp = System.currentTimeMillis();
		System.out.println(temp);
		String current = toString(temp);
		System.out.println(current);		
		System.out.println(toMillis(current));
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
