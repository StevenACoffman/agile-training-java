package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import edu.umich.lsa.content.Event;
import edu.umich.lsa.content.Storage;

public class EventTest
{
	@Test
	public void newEventIsUnrated()
	{
		Storage storage = new Storage();
		Event event = new Event("1", storage);
		assertThat(event.getRating(), is(0));
	}
}
