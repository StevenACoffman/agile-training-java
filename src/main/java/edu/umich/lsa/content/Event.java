package edu.umich.lsa.content;

public class Event {
	
	private int rating;
	
	public Event() {
		rating = 0;
	}

	public int getRating() {
		return rating;
	}

	public void rate(int score) {
		rating = score;		
	}
}
