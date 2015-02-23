package csmsquared.main;


public class Racer 
{
	private int id;
	private long startTime;
	private long endTime;
	
	public Racer(int id) throws IllegalArgumentException
	{
		if(id > 99999 || id<0) throw new IllegalArgumentException("id must be <0 and >=99999");
		this.id = id;
	}
	
	public void start()
	{
		//startTime = System.currentTimeMillis();
		startTime = Time.getTime();
	}
	public void end()
	{
		//endTime = System.currentTimeMillis();
		endTime =Time.getTime();
	}
	public long elapsedTime()
	{
		if(endTime <= 0) return -1;
		long elapsedTime = endTime-startTime;
		return elapsedTime > 9999990 ? -1 : elapsedTime;
	}
	public String toString()
	{
		if (elapsedTime() == -1) return id + " DNF";
		return id + " " + Time.toString(elapsedTime());
	}
	public int getId()
	{
		return id;
	}
	
}
