package com.template.ui.atf.stepdef;

import com.template.ui.atf.Context;
import com.template.ui.atf.factory.UiEnvironment;
import com.template.ui.logging.CucumberScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;

@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class Hooks {

    @Autowired
    private Context context;

    private final HashMap<String, UiEnvironment> uiEnvironmentHashMap = new HashMap<>();

    @Autowired
    private CucumberScenarioContext cucumberScenarioContext;

    @Before
    public void setup(Scenario scenario) {
        cucumberScenarioContext.setCurrentScenario(scenario);
        context.setUiEnvironment(uiEnvironmentHashMap);
    }

    @After
    public void cleanup() {
        for (UiEnvironment uiEnvironment : uiEnvironmentHashMap.values()) {
            uiEnvironment.getBrowser().getDriver().close();
            uiEnvironment.getBrowser().getDriver().quit();
        }
    }
}