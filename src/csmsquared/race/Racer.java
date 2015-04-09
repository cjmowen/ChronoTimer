package csmsquared.race;

import csmsquared.main.Time;


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
		startTime = Time.getTime();
	}


	public void end()
	{
		if(endTime != 0) throw new IllegalStateException("The racer has already ended");
		endTime = Time.getTime();
	}


	public void didNotFinish() {
		endTime = -1;
	}


	public long getStartTime()
	{
		return startTime;
	}


	/**
	 * Returns the racer's elapsed time.
	 * @return the elapsed time, or -1 if the racer did not finish.
	 */
	public long getElapsedTime()
	{
		if(startTime <= 0) return 0;

		long elapsedTime;
		if(endTime == -1) {
			return -1;
		}
		else if(endTime == 0) {
			elapsedTime = Time.getTime() - startTime;
		}
		else {
			elapsedTime = endTime - startTime;
		}

		return elapsedTime > 9999990 ? -1 : elapsedTime;
	}


	public String toString()
	{
		if (getElapsedTime() == -1) return "[" + id + "]:" + " DNF";
		return "[" + id + "]: " + Time.toString(getElapsedTime());
	}


	public int getId()
	{
		return id;
	}

}
