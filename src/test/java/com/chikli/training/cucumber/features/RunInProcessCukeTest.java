package com.chikli.training.cucumber.features;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;
@Ignore
@RunWith(Cucumber.class)
@Cucumber.Options(tags = { "@wip" }, format = { "pretty", "html:target/cucumber-html-report" }, monochrome = true)
public class RunInProcessCukeTest {
	// This class is just a runner for the Cucumber features
}