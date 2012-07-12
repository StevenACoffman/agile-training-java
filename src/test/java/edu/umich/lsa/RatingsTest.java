package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class RatingsTest
{

	@Test
	public void noEventsAreRatedByDefault()
	{
		Ratings ratings = new Ratings();
		Event event = new Event(1);
		assertThat(ratings.getRating(event), is(Ratings.UNRATED));
	}

	@Test
	public void canRateEvent() throws Exception
	{
		Ratings ratings = new Ratings();
		Event event = new Event(2);
		ratings.rate(event, 4);
		assertThat(ratings.getRating(event), is(4));
	}

	@Test
	public void rateOneOutOfTwoEvents() throws Exception
	{
		Ratings ratings = new Ratings();
		Event firstEvent = new Event(3);
		Event secondEvent = new Event(4);

		ratings.rate(firstEvent, 4);

		assertThat(ratings.getRating(secondEvent), is(Ratings.UNRATED));
	}

	@Test
	public void eventRatingGoesToFile() throws Exception
	{
		Ratings ratings = new Ratings();
		Event event = new Event(5);
		ratings.rate(event, 10);

		File file = new File(Ratings.FILE_NAME);
		List<String> lines = Files.readLines(file, Charsets.UTF_8);
		assertThat(lines.get(0), is("5,10"));
	}

	@Test
	public void noRatingsFileWithUnratedEvent() throws Exception
	{
		new Ratings();
		new Event(6);
		File file = new File(Ratings.FILE_NAME);

		assertThat(file.exists(), is(false));
	}

	@Test
	public void twoEventRatingsGoToFile() throws Exception
	{
		Ratings ratings = new Ratings();
		Event event = new Event(5);
		ratings.rate(event, 10);

		Event secondEvent = new Event(6);
		ratings.rate(secondEvent, 9);

		File file = new File(Ratings.FILE_NAME);
		List<String> lines = Files.readLines(file, Charsets.UTF_8);
		assertThat(lines.contains("5,10"), is(true));
		assertThat(lines.contains("6,9"), is(true));
	}

	@Test
	public void oneEventRatedTwice() throws Exception
	{
		Ratings ratings = new Ratings();
		Event event = new Event(1);
		ratings.rate(event, 5);
		ratings.rate(event, 3);

		File file = new File(Ratings.FILE_NAME);
		List<String> lines = Files.readLines(file, Charsets.UTF_8);
		assertThat(lines.contains("1,3"), is(true));
		assertThat(lines.contains("1,5"), is(false));
	}

	@After
	public void deleteFile()
	{
		File file = new File(Ratings.FILE_NAME);
		if (file.exists())
			file.delete();
	}
}
