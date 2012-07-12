package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import edu.umich.lsa.content.Event;
import edu.umich.lsa.content.Storage;

public class EventStoringTest
{
	private Storage storage;
	
	private static final String EVENT_FILE_NAME = System.getProperty("java.io.tmpdir") + File.separator + "event.txt";

	@Before
	public void createStorage()
	{
		storage = new Storage();
	}
	
	@After
	public void cleanup()
	{
		File ratingFile = new File(EVENT_FILE_NAME);
		assertTrue(ratingFile.delete());

	}

	@Test
	public void rateCommitCreatesAFile() throws Exception
	{
		Event event = new Event("1", storage);
		event.rate(2);
		File file = new File(EVENT_FILE_NAME);
		assertTrue(file.exists());
	}

	@Test
	public void fileHasRatingContents() throws Exception
	{
		Event event = new Event("1", storage);
		event.rate(2);
		File file = new File(EVENT_FILE_NAME);
		List<String> lines = Files.readLines(file, Charsets.UTF_8);

		assertThat(lines.size(), is(2));
		assertThat(lines.get(1), is("rating_1=2"));
	}

	@Test
	public void getRatingContentsFromFile() throws Exception
	{
		Event event = new Event("1", storage);
		event.rate(4);
		Event sameEventFromFile = new Event("1", storage);
		assertThat(sameEventFromFile.getRating(), is(4));
	}

	@Test
	public void getRatingsForEventByIdFromFile() throws Exception
	{
		File file = new File(EVENT_FILE_NAME);
		Files.write("rating_99 = 14", file, Charsets.UTF_8);
		Event event = new Event("99", storage);
		assertThat(event.getRating(), is(14));
	}
	
	@Test
	public void twoRatedEvents() throws Exception 
	{
		Event eventOne = new Event("1", storage);
		Event eventTwo = new Event("2", storage);
		eventOne.rate(3);
		eventTwo.rate(5);
		File file = new File(EVENT_FILE_NAME);
		List<String> lines = Files.readLines(file, Charsets.UTF_8);
		assertThat(lines.contains("rating_1=3"), is(true));
		assertThat(lines.contains("rating_2=5"), is(true));
	}

}
