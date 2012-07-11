package edu.umich.lsa.content;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class Event
{
	private static final String RATING_PREFIX = "rating_";
	private final String EVENT_FILE_NAME = System.getProperty("java.io.tmpdir") + File.separator + "event.txt";
	private int rating;
	private final Properties properties;

	private final String ratingName;

	public Event(String id)
	{
		this.ratingName = RATING_PREFIX + id;

		rating = 0;
		properties = new Properties();
		loadProperties();
	}

	private void loadProperties()
	{
		File file = new File(EVENT_FILE_NAME);
		if (file.exists())
		{
			try
			{
				FileReader reader = new FileReader(file);

				properties.load(reader);
				reader.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getRating()
	{
		if (rating == 0)
		{
			readRating();
		}
		return rating;
	}

	private void readRating()
	{
		try
		{
			String ratingStr = properties.getProperty(ratingName, "-1");
			if (ratingStr != null && !ratingStr.equals("-1"))
			{
				rating = Integer.parseInt(ratingStr);
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	}

	public void rate(int score)
	{
		rating = score;
	}

	public void commit() throws IOException
	{
		File file = new File(EVENT_FILE_NAME);
		if (!file.exists())
		{
			file.createNewFile();
		}
		properties.setProperty(ratingName, Integer.toString(rating));
		Writer out = new FileWriter(file);
		properties.store(out, null);
		out.close();

	}

}
