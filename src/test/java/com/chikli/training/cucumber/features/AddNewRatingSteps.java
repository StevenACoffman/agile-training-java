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
	private Event firstEvent;
	private Event secondEvent;

	@Given("^there (?:is|are) (\\d+) events? that I have not rated$")
	public void createUnratedEvents(int numberOfEvents) throws Throwable
	{
		ratings = new Ratings();
		firstEvent = new Event();
		if (numberOfEvents == 2)
		{
			secondEvent = new Event();
		}
	}

	@When("^I rate event (\\d+) as (\\d+)$")
	public void rateEvent(int eventID, int eventRating) throws Throwable
	{
		if (eventID == 1)
			ratings.rate(firstEvent, eventRating);
		else
			ratings.rate(secondEvent, eventRating);
	}

	@Then("^event (\\d+)'s rating is recorded as (\\d+)$")
	public void eventRatingIsRecordedAs(int eventID, int eventRating)
			throws Throwable
	{

		Event event = firstEvent;
		if (eventID == 2)
			event = secondEvent;

		assertThat(ratings.getRating(event), is(eventRating));

	}

	@Then("^event (\\d+) is unrated$")
	public void eventIsUnrated(int eventID) throws Throwable
	{
		Event event = firstEvent;
		if (eventID == 2)
			event = secondEvent;

		assertThat(ratings.getRating(event), is(0));
	}
}
