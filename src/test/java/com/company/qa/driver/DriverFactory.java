package com.company.qa.driver;

import com.company.qa.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {
        try {
            String execution = ConfigReader.get("execution");
            String browser = ConfigReader.get("browser");

            WebDriver webDriver;

            if ("grid".equalsIgnoreCase(execution)) {
                if ("chrome".equalsIgnoreCase(browser)) {
                    webDriver = new RemoteWebDriver(
                            new URL(ConfigReader.get("gridUrl")),
                            new ChromeOptions()
                    );
                } else {
                    webDriver = new RemoteWebDriver(
                            new URL(ConfigReader.get("gridUrl")),
                            new FirefoxOptions()
                    );
                }
            } else {
                if ("chrome".equalsIgnoreCase(browser)) {
                    WebDriverManager.chromedriver().setup();
                    webDriver = new org.openqa.selenium.chrome.ChromeDriver();
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new org.openqa.selenium.firefox.FirefoxDriver();
                }
            }

            driver.set(webDriver);

        } catch (Exception e) {
            throw new RuntimeException("Driver init failed", e);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
