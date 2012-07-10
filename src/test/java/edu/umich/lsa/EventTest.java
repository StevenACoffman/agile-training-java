package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import edu.umich.lsa.content.Event;

public class EventTest
{

	@Test
	public void newEventIsUnrated()
	{
		Event event = new Event("1");
		assertThat(event.getRating(), is(0));
	}

	@Test
	public void ratingCanBeSet()
	{
		Event event = new Event("1");
		event.rate(3);
		assertThat(event.getRating(), is(3));
	}

}
