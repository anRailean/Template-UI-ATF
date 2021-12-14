package com.template.ui.atf.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class NovaDriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String driverType, String driverPath) {
        switch (driverType.toUpperCase()) {
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-bcpio;application/octet-stream;image/bmp;application/x-bittorrent;");
                firefoxOptions.setProfile(profile);
                System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "EDGE":
                System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
                EdgeDriverService edgeService = EdgeDriverService.createDefaultService();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver.set(new EdgeDriver(edgeService, edgeOptions));
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();
                HashMap<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.addArguments("--window-size=1920,1080");
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                driver.set(new ChromeDriver(chromeOptions));
        }

        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver.get();
    }

    public static void quitDriver() {
        driver.get().close();
        driver.get().quit();
    }
}