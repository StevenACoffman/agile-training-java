package com.chikli.training.cucumber.features;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@Ignore
@RunWith(Cucumber.class)
@Cucumber.Options(tags = { "@ok" }, format = { "pretty", "html:target/cucumber-html-report" })
public class RunCompletedCukeTest {
}