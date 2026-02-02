package com.company.qa.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.company.qa.driver.DriverFactory.getDriver;

public class AllureTestListener implements ITestListener {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] captureScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot();
    }
}
