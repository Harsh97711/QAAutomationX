package com.company.qa.driver;

import com.company.qa.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            try {
                String execution = ConfigReader.get("execution");
                String browser = ConfigReader.get("browser");

                System.out.println("Execution mode = " + execution);
                System.out.println("Browser = " + browser);

                if ("grid".equalsIgnoreCase(execution)) {

                    System.out.println(">>> USING SELENIUM GRID <<<");

                    if ("chrome".equalsIgnoreCase(browser)) {
                        ChromeOptions options = new ChromeOptions();
                        driver.set(new RemoteWebDriver(
                                new URL(ConfigReader.get("gridUrl")),
                                options
                        ));

                    } else if ("firefox".equalsIgnoreCase(browser)) {
                        FirefoxOptions options = new FirefoxOptions();
                        driver.set(new RemoteWebDriver(
                                new URL(ConfigReader.get("gridUrl")),
                                options
                        ));
                    }

                } else {

                    System.out.println(">>> USING LOCAL DRIVER <<<");

                    if ("chrome".equalsIgnoreCase(browser)) {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());

                    } else if ("firefox".equalsIgnoreCase(browser)) {
                        WebDriverManager.firefoxdriver().setup();
                        driver.set(new FirefoxDriver());
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize WebDriver", e);
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
