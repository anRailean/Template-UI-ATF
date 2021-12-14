package com.template.ui.atf;

import com.template.ui.atf.factory.UiEnvironment;
import com.template.ui.logging.CucumberScenarioContext;
import com.template.ui.logging.SpringContext;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ScenarioScope
public class Context {

    private final HashMap<String, Object> dataDictionary;

    public Context() {
        dataDictionary = new HashMap<>();
    }

    public void put(String key, Object object) {
        dataDictionary.put(key, object);
    }

    public <T> T get(String key) throws RuntimeException {
        if (!dataDictionary.containsKey(key)) {
            throw new RuntimeException("Couldn't find the key you are looking for in scenario context");
        }
        return (T) dataDictionary.get(key);
    }

    @Autowired
    private CucumberScenarioContext cukeScenarioContext;

    private HashMap<String, UiEnvironment> uiEnvironmentContainer;

    public void takeScreenshot(String name, String driverName) {
        cukeScenarioContext.getCurrentScenario().attach(uiEnvironmentContainer.get(driverName)
                .getBrowser().takeScreenshot(), "image/png", name + ".png");
    }

    public UiEnvironment getUiEnvironment(String driverName) {
        if (uiEnvironmentContainer.containsKey(driverName)) {
            return uiEnvironmentContainer.get(driverName);
        } else {
            uiEnvironmentContainer.put(driverName, SpringContext.getBean(UiEnvironment.class));
        }
        return uiEnvironmentContainer.get(driverName);
    }

    public void setUiEnvironment(HashMap<String, UiEnvironment> uiEnvironmentContainer) {
        this.uiEnvironmentContainer = uiEnvironmentContainer;
    }
}