package edu.umich.lsa;

import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

public class PerformanceTest
{
	@Test
	public void canRate10kEventsInOneMinute() throws Exception 
	{
		Ratings ratings = new Ratings();
		int millisecondsPerMinute = 60 * 1000;
		long startTime = System.currentTimeMillis();
		for (int i=0;i<10000;i++)
		{
			Event event = new Event(i);
			ratings.rate(event, i);
			if (i%10==0)
			{
				System.out.println("number of events: " + i);
				System.out.println("milliseconds lapsed: " + (System.currentTimeMillis()-startTime));
			}
			assertThat((System.currentTimeMillis()-startTime), lessThan(millisecondsPerMinute*1L));
		}
		long endTime = System.currentTimeMillis();
		assertThat((endTime-startTime), lessThan(millisecondsPerMinute*1L));
	}
	@After
	public void deleteFiles() throws IOException
	{
		Ratings ratings = new Ratings();
		ratings.deleteAllFiles();
	}
}
