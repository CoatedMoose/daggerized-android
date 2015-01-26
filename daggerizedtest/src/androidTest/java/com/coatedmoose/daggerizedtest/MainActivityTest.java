package com.coatedmoose.daggerizedtest;

import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String INITIAL_PREFERENCE_STRING = "Hello, World!";
    private static final String SECOND_PREFERENCE_STRING = "Not hello world";

    private MainActivity mActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        mActivity.savedText.set(INITIAL_PREFERENCE_STRING);
    }

    public void testAppContextInjected() {
        assertNotNull(mActivity.applicationContext);
    }

    public void testActivityContext() {
        assertNotNull(mActivity.activityContext);
    }

    public void testStringPreference() {
        assertNotNull(mActivity.savedText);
    }

    public void testEditText() {
        assertEquals(mActivity.savedText.get(), INITIAL_PREFERENCE_STRING);
        onView(withId(R.id.saveable_text)).perform(clearText(), typeText(SECOND_PREFERENCE_STRING));
        assertEquals(mActivity.savedText.get(), SECOND_PREFERENCE_STRING);
    }

    public void testSavedTextPersists() {
        assertEquals(mActivity.savedText.get(), INITIAL_PREFERENCE_STRING);
        onView(withId(R.id.saveable_text)).perform(clearText(), typeText(SECOND_PREFERENCE_STRING));
        assertEquals(mActivity.savedText.get(), SECOND_PREFERENCE_STRING);
        mActivity.finish();
        mActivity = getActivity();
        assertEquals(mActivity.savedText.get(), SECOND_PREFERENCE_STRING);
    }
}