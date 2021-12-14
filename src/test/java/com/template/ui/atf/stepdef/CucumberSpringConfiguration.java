package com.template.ui.atf.stepdef;

import com.template.ui.atf.CucumberDriverConfig;
import com.template.ui.logging.CucumberSpringLoggingConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@CucumberContextConfiguration
@Import({CucumberSpringLoggingConfiguration.class, CucumberDriverConfig.class})
@PropertySource("classpath:application.properties")
public class CucumberSpringConfiguration {
}