package com.coatedmoose.daggerizedtest;

import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@SmallTest
public class ApplicationTest extends ApplicationTestCase<MyApplication> {
    public ApplicationTest() {
        super(MyApplication.class);
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
