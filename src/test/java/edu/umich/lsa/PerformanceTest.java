package edu.umich.lsa;

import static org.hamcrest.number.OrderingComparison.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

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
}
