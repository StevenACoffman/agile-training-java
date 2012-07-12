package edu.umich.lsa.content;

import java.util.Observable;

public class Event extends Observable
{
	private int rating;	

	public Event(String id, Storage storage)
	{
		rating = 0;
		
		this.addObserver(storage);
	}

	public int getRating()
	{
		return rating;
	}

	public void rate(int score)
	{
		if (rating!=score)
		{
			rating = score;
			this.setChanged();
			this.notifyObservers();
		}
	}

}
