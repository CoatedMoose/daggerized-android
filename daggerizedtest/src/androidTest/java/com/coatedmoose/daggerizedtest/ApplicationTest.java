package com.coatedmoose.daggerizedtest;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    /**
     * Test basic startup/shutdown of app
     */
    public void testSimpleCreate() throws InterruptedException {
        createApplication();
        Thread.sleep(5000);
        terminateApplication();
        Thread.sleep(5000);
    }
}
