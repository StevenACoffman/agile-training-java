package com.chikli.training.cucumber.features;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@Cucumber.Options(tags = { "@ok" }, format = { "pretty", "html:target/cucumber-html-report" }, monochrome = true)
public class RunCompletedCukeTest
{
}