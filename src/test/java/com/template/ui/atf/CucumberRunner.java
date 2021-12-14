package com.template.ui.atf;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber/cucumber.json", "html:target/cucumber/test-report.html"},
        features = "classpath:features/",
        tags = "@Example",
        glue = {"classpath:com.template.ui.atf.stepdef"}
)
public class CucumberRunner {
}