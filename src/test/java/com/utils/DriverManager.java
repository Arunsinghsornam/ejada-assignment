package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Utility class
    }

   
    public static void initDriver() {
        initDriver(ConfigReader.getBrowser());
    }

    public static void initDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = createFirefoxDriver();
                break;
            case "chrome":
            default:
                driver = createChromeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

  
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initDriver() first.");
        }
        return driver;
    }

  
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}
