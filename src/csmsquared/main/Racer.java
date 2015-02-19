package csmsquared.main;


public class Racer 
{
	private int id;
	private long startTime;
	private long endTime;
	
	public Racer(int id)
	{
		this.id = id;
	}
	
	public void start()
	{
		startTime = System.currentTimeMillis();
	}
	public void end()
	{
		endTime = System.currentTimeMillis();
	}
	public long elapsedTime()
	{
		return endTime-startTime;
	}
	public String toString()
	{
		return id + " " + Time.toString(elapsedTime());
	}
	public int getId()
	{
		return id;
	}
	
}
