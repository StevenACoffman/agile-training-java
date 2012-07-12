package com.chikli.training.cucumber.features;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import edu.umich.lsa.Event;
import edu.umich.lsa.Ratings;

public class AddNewRatingSteps
{		
	private Ratings ratings;
	private Event event;

	@Given("^there (?:is|are) (\\d+) events? that I have not rated$")
	public void atLeastOneUnratedEventExists(int arg1) throws Throwable {
		ratings = new Ratings();
		event = new Event();
	}
	
	@When("^I rate event (\\d+) as (\\d+)$")
	public void rateEvent(int eventID, int eventRating) throws Throwable {
		ratings.rate(event, eventRating);
	}

	@Then("^event (\\d+) rating is recorded as (\\d+)$")
	public void event_rating_is_recorded_as(int eventID, int eventRating) throws Throwable {
		assertThat(ratings.getRating(event), is(eventRating));
	}

	//^only the first event's rating is recorded$
}
