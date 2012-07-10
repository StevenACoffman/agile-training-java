package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import edu.umich.lsa.content.Event;

public class EventStoringTest
{
	private static final String EVENT_FILE_NAME = System.getProperty("java.io.tmpdir") + File.separator + "event.txt";

	@After
	public void cleanup()
	{
		File ratingFile = new File(EVENT_FILE_NAME);
		assertTrue(ratingFile.delete());

	}

	@Test
	public void rateCommitCreatesAFile() throws Exception
	{
		Event event = new Event();
		event.rate(2);
		event.commit();
		File file = new File(System.getProperty("java.io.tmpdir") + "event.txt");
		assertTrue(file.exists());
	}

	@Test
	public void fileHasRatingContents() throws Exception
	{
		Event event = new Event();
		event.rate(2);
		event.commit();
		File file = new File(EVENT_FILE_NAME);
		List<String> lines = Files.readLines(file, Charsets.UTF_8);

		assertThat(lines.size(), is(1));
		assertThat(lines.get(0), is("rating = 2"));
	}

	@Test
	public void getRatingContentsFromFile() throws Exception
	{
		Event event = new Event();
		event.rate(4);
		event.commit();
		Event anotherEvent = new Event();
		assertThat(anotherEvent.getRating(), is(4));
	}
}
