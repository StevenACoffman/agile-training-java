package edu.umich.lsa;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Ratings
{
	public static final int UNRATED = -1;
	public static final String FILE_NAME = System.getProperty("tmp.io.dir")
			+ File.pathSeparator + "ratings.csv";
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
		File ratingFile = new File(FILE_NAME);
		try
		{
			Files.append((event.getEventID() + "," + eventRating + System
					.lineSeparator()), ratingFile, Charsets.UTF_8);
		} catch (IOException e)
		{
			throw new RuntimeException("Failed to write to Ratings file", e);
		}

	}
}
