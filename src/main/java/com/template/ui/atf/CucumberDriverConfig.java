package com.template.ui.atf;

import com.template.ui.atf.factory.NovaDriverFactory;
import com.template.ui.atf.factory.UiEnvironment;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.testmonkeys.maui.core.browser.Browser;
import org.testmonkeys.maui.core.factory.PageFactory;
import org.testmonkeys.maui.core.factory.PageScanner;

@Configuration
@ComponentScan("com.template.ui.atf")
public class CucumberDriverConfig {

    @Value("${browser.name}")
    private String driverType;

    @Value("${drivers.path}")
    private String driverPath;

    @Value("${page.objects.package}")
    private String pageObjects;

    @Value("${app.base.url}")
    private String baseUrl;

    private Browser browser;

    private PageFactory pageFactory;

    @Bean
    @ScenarioScope
    public Browser browser() {
        return this.browser;
    }

    @Bean
    @ScenarioScope
    public PageFactory pageFactory(Browser browser) {
        return this.pageFactory;
    }

    @Bean
    @ScenarioScope
    @Scope("prototype")
    public UiEnvironment uiEnvironment() {
        this.browser = new Browser(NovaDriverFactory.getDriver(driverType, driverPath));
        PageScanner pageScanner = new PageScanner(pageObjects);
        this.pageFactory = new PageFactory(browser, pageScanner, baseUrl);
        return new UiEnvironment(browser, pageScanner, pageFactory);
    }
}