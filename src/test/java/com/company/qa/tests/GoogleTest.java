package com.company.qa.tests;

import com.company.qa.base.BaseTest;
import com.company.qa.retry.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyGoogleTitle() {

        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Title = " + title);

        Assert.assertTrue(title.contains("Google"));
    }
}
