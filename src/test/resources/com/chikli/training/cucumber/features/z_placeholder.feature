@wip
Feature: Prevent "in process" Cucumber JUnit test from failing 

Scenario: The build should not fail when no wip tags exist
	Given there are no scenarios tagged with @wip
	Then the build should succeed
