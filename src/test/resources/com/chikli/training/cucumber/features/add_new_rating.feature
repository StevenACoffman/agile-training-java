Feature: Add New Rating

@wip
Scenario: Adding a new rating
	Given there is an event that I have not rated
	When I rate the event
	Then the event rating is recorded
	
Scenario: Adding a new rating for one out of two events
	Given there are two events that I have not rated
	When I rate the first event
	Then only the first event's rating is recorded
	
Scenario: Adding a new rating for an unrated event without affecting another rated event
	Given there is one rated event and one unrated event
	When I rate the unrated event
	Then the rated event is unaffected
	And the unrated event's rating is recorded

