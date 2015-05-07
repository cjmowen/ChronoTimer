package csmsquared.test;


import java.util.LinkedList;

import junit.framework.TestCase;


import csmsquared.main.ChronoTimer;
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
		
		LinkedList<Integer> racer = chrono.getRacersInQueue();
		
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
		
	}
	
	public void testRaceTypes()
	{
		chrono = new ChronoTimer();
		chrono.endRun();
		chrono.newRun();
		try{
			chrono.newRun();
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
			chrono.endRun();
		}
		try{
		chrono.endRun();
		}
		catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	public void testRacerQueue()
	{
		chrono = new ChronoTimer();
		chrono.endRun();
		for(int i=0; i < 155; i++)
		{
			chrono.num(i);
		}
		
		chrono.setRaceType(RaceType.Group);
		chrono.newRun();
		
		for(int i=0; i<6;i++)
		{
			chrono.toggle(i+1);
		}
			chrono.trigger(1);
		
		LinkedList<String> racers = chrono.getCurrentRacers();
		
	}
	
	
	
	
	
	
}
