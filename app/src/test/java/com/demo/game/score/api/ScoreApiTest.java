package com.demo.game.score.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
                 glue = "com.demo.game.score.api",
                 plugin = "pretty",
                 strict = true)
public class ScoreApiTest {

}