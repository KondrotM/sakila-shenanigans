package com.sakila.api.SakilaApp.BDD;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/main/resources/features",
    glue = "com.sakila.api.SakilaApp"
)
public class RunCucumberTests {
}
