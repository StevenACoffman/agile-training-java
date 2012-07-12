package edu.umich.lsa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RatingsTest
{

	@Test
	public void noEventsAreRatedByDefault()
	{
		Ratings ratings = new Ratings();
		Event event = new Event();
		assertThat(ratings.getRating(event), is(Ratings.UNRATED));
	}

	@Test
	public void canRateEvent() throws Exception
	{
		Ratings ratings = new Ratings();
		Event event = new Event();
		ratings.rate(event, 4);
		assertThat(ratings.getRating(event), is(4));
	}

	@Test
	public void rateOneOutOfTwoEvents() throws Exception
	{
		Ratings ratings = new Ratings();
		Event firstEvent = new Event();
		Event secondEvent = new Event();

		ratings.rate(firstEvent, 4);

		assertThat(ratings.getRating(secondEvent), is(Ratings.UNRATED));
	}

}
