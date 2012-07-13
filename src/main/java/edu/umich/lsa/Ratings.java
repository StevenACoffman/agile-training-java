package edu.umich.lsa;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Ratings {
	public static final int UNRATED = -1;
	public static final String FILE_PATH = 
			System.getProperty("java.io.tmpdir") +
			File.separator + "ratings" + File.separator;
	public static final String FILE_NAME_PREFIX = FILE_PATH + "eventRating";
	public static final String FILE_NAME_EXTENSION = ".csv";
	private final Map<Event, Integer> ratings;

	public Ratings() {
		ratings = new HashMap<Event, Integer>();
	}

	public int getRating(Event event) {
		Integer rating = ratings.get(event);
		if (rating == null) {
			return UNRATED;
		}
		return rating;
	}

	public void rate(Event event, int eventRating) {
		ratings.put(event, eventRating);
		writeRating(event);
	}

	private void writeRating(Event event) {
		File ratingDirectory = new File(FILE_PATH);
		if(!ratingDirectory.exists())
		{
			ratingDirectory.mkdir();
		}
		File ratingFile = new File(FILE_NAME_PREFIX + event.getEventID()
				+ FILE_NAME_EXTENSION);
		ratingFile.delete();
		try {
			StringBuilder builder = new StringBuilder();

			int eventRating = ratings.get(event);
			builder.append(event.getEventID()).append(",").append(eventRating)
					.append(System.getProperty("line.seperator"));

			Files.append(builder.toString(), ratingFile, Charsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write to Ratings file", e);
		}
	}
	public void deleteAllFiles() throws IOException
	{
		File ratingsDirectory = new File(FILE_PATH);
		
		if (ratingsDirectory.exists() && ratingsDirectory.isDirectory())
		{
			for (File currentFile : ratingsDirectory.listFiles())
			{
				currentFile.delete();
			}
			ratingsDirectory.delete();
		} 
		
	}
}
