package com.chikli.training.cucumber.features;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import edu.umich.lsa.content.Event;

public class AddNewRatingSteps
{

	private Event event;

	@Given("^there is an event that I have not rated$")
	public void there_is_an_event_that_I_have_not_rated() throws Throwable
	{

		event = new Event();
		assertThat(event.getRating(), is(0));
	}

	@When("^I rate the event$")
	public void I_rate_the_event() throws Throwable
	{

		event.rate(4);
		event.commit();
	}

	@Then("^the event rating is recorded$")
	public void the_event_rating_is_recorded() throws Throwable
	{

		Event newEvent = new Event();

		assertThat(newEvent.getRating(), is(4));
	}

}
