Feature: Modify Existing Rating

@ok
Scenario: Modifying an existing rating for a single event
	Given there is 1 event that I have rated as 5
	When I rate event 1 as 3
	Then event 1's rating is recorded as 3
