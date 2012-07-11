package com.chikli.training.cucumber.features;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import edu.umich.lsa.content.Event;

public class AddNewRatingSteps
{
	private static final String EVENT_FILE_NAME = System.getProperty("java.io.tmpdir") + File.separator + "event.txt";

	private List<Event> eventList;

	@Given("^there (?:is|are) (\\d+) events? that I have not rated$")
	public void createEvents(int numberOfEvents) throws Throwable
	{
		eventList = new ArrayList<Event>();
		for (int i = 1; i <= numberOfEvents; i++)
		{
			eventList.add(new Event(Integer.toString(i)));
		}
	}

	@When("^I rate event (\\d+) as (\\d+)$")
	public void rate_event(int eventNumber, int rating) throws Throwable
	{
		Event eventToRate = eventList.get(eventNumber - 1);
		eventToRate.rate(rating);
		eventToRate.commit();
		System.out.println("Rating Event Number:" + eventNumber + " as: " + rating);
	}

	@Then("^event (\\d+)'s rating is recorded as (\\d+)$")
	public void the_event_rating_is_recorded(int eventNumber, int rating) throws Throwable
	{
		Event newEvent = new Event(Integer.toString(eventNumber));
		System.out.println("Rating Event Number:" + eventNumber + " as: " + rating);
		assertThat(newEvent.getRating(), is(rating));

	}

	@Then("^only the first event's rating is recorded$")
	public void only_the_first_event_s_rating_is_recorded() throws Throwable
	{
		Event firstEventFromFile = new Event("1");
		assertThat(firstEventFromFile.getRating(), is(4));
		Event secondEventFromFile = new Event("2");
		assertThat(secondEventFromFile.getRating(), is(0));
	}

	@After
	public void cleanup() throws Throwable
	{
		File ratingFile = new File(EVENT_FILE_NAME);
		ratingFile.delete();
	}
}
