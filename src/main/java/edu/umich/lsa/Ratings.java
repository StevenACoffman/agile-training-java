package edu.umich.lsa;


public class Ratings {

	private int eventRating;

	public int getRating(Event event) {
		return eventRating;
	}

	public void rate(Event event, int eventRating) {
		this.eventRating = eventRating;
	}

}
