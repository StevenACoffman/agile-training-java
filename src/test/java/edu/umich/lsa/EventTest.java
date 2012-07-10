package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import edu.umich.lsa.content.Event;

public class EventTest {

	@Test
	public void checkThatNewEventIsUnrated()
	{
		Event event = new Event();
		assertThat(event.getRating(), is(0));
	}
	
	@Test
	public void checkThatRatingCanBeSet() 
	{
		Event event = new Event();
		event.rate(3);
		assertThat(event.getRating(), is(3));
	}
}
