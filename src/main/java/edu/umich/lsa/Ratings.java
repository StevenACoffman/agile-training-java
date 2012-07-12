package edu.umich.lsa;

import java.util.HashMap;
import java.util.Map;

public class Ratings
{
	public static final int UNRATED = -1;
	private final Map<Event, Integer> ratings;

	public Ratings()
	{
		ratings = new HashMap<Event, Integer>();
	}

	public int getRating(Event event)
	{
		Integer rating = ratings.get(event);
		if (rating == null)
		{
			return UNRATED;
		}
		return rating;
	}

	public void rate(Event event, int eventRating)
	{
		ratings.put(event, eventRating);
	}
}
