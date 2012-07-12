Feature: Add New Rating

@ok
Scenario: Adding a new rating for a single event
	Given there is 1 event that I have not rated
	When I rate event 1 as 4
	Then event 1's rating is recorded as 4

@ok		
Scenario: Adding a new rating for one out of two events
	Given there are 2 events that I have not rated
	When I rate event 1 as 4
	Then event 1's rating is recorded as 4
	And event 2 is unrated
	
@ok
Scenario: Allow 0 value ratings
	Given there is 1 event that I have not rated
	When I rate event 1 as 0
	Then event 1's rating is recorded as 0

Scenario: Adding a new rating for an unrated event without affecting another rated event
	Given there are 2 events that I have not rated
	When I rate event 1 as 4
	And I rate event 2 as 16
	Then event 1's rating is recorded as 4
	And event 2's rating is recorded as 16
