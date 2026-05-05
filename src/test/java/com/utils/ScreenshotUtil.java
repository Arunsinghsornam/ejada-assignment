package com.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotUtil - Captures screenshots and attaches them to Allure reports.
 */
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "target/screenshots/";

    private ScreenshotUtil() {}

    public static void captureAndAttach(WebDriver driver, String screenshotName) {
        if (!ConfigReader.isScreenshotOnFailure()) return;

        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            String safeName = screenshotName.replaceAll("[\\\\/:*?\"<>|]", "_").replaceAll("\\s+", "_");
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshotBytes));
            saveToFile(screenshotBytes, safeName);

        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    private static void saveToFile(byte[] bytes, String name) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = name.replaceAll("\\s+", "_") + "_" + timestamp + ".png";
        Path dirPath = Paths.get(SCREENSHOT_DIR);
        Files.createDirectories(dirPath);
        Files.write(dirPath.resolve(fileName), bytes);
    }
}
