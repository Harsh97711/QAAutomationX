package com.company.qa.tests;

import com.company.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() throws InterruptedException {
        driver.get("https://www.google.com");

        // 🔴 HOLD SESSION FOR VISIBILITY
        Thread.sleep(10000);

        Assert.assertTrue(driver.getTitle().contains("Google"));
    }

}
