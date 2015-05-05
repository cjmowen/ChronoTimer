package csmsquared.main;


import java.util.LinkedList;

import junit.framework.TestCase;


import csmsquared.race.RaceType;

public class TestChronoTimer extends TestCase {
	ChronoTimer chrono;
	
	public void testInitalize()
	{
		ChronoTimer chrono = new ChronoTimer();
		assertEquals(chrono.getRaceType(),RaceType.Individual);
	}
	
	public void testAddingRemovingPlayer()
	{
		 chrono = new ChronoTimer();
		for(int i=1; i< 99999;i++)
			chrono.num(i);
		
		LinkedList racer = chrono.getRacersInQueue();
		
		for(int i=1; i < 99999; i++)
		{
			assertEquals(racer.poll(),(Integer)i);
		}
		assertTrue(racer.isEmpty());
	}
	
	public void testAddingHigherNumber()
	{
		chrono = new ChronoTimer();
		
		try{
			
			chrono.num(10000000);
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalArgumentException);
		}
		
	}s
	
	
	
	
}
