package com.chikli.training.cucumber.features;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import edu.umich.lsa.content.Event;

public class AddNewRatingSteps
{
	private static final String EVENT_FILE_NAME = System.getProperty("java.io.tmpdir") + File.separator + "event.txt";

	private Event firstEvent;
	private Event secondEvent;

	@Given("^there (?:is|are) (\\d+) events? that I have not rated$")
	public void createEvents(int numberOfEvents) throws Throwable
	{
		firstEvent = new Event("1");
		if (numberOfEvents == 2)
		{
			secondEvent = new Event("2");
		}
	}

	@When("^I rate the (?:first |.*)event$")
	public void I_rate_the_first_event() throws Throwable
	{
		firstEvent.rate(4);
		firstEvent.commit();
	}

	@Then("^the event rating is recorded$")
	public void the_event_rating_is_recorded() throws Throwable
	{

		Event newEvent = new Event("1");

		assertThat(newEvent.getRating(), is(4));

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
