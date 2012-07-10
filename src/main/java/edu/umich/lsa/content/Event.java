package edu.umich.lsa.content;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Event
{
	private static final String EVENT_FILE_NAME = System.getProperty("java.io.tmpdir") + "event.txt";
	private int rating;
	private final Properties properties;

	static
	{
		System.out.println(EVENT_FILE_NAME);
	}

	public Event()
	{
		rating = 0;
		properties = new Properties();
	}

	private void loadProperties()
	{
		File file = new File(EVENT_FILE_NAME);

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
			loadProperties();
			String ratingStr = properties.getProperty("rating", "-1");
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
		file.createNewFile();
		Files.write("rating = " + rating, file, Charsets.UTF_8);

	}

}
