package com.coatedmoose.daggerizedandroid;

import android.app.Activity;
import android.content.Context;

import com.coatedmoose.daggerizedandroid.qualifier.ForActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Module responsible for injecting the basic activity
 */
@Module(
        addsTo = ApplicationModule.class,
        injects = {
                DaggerFragmentActivity.class,
                DaggerListActivity.class
        }
)
public class ActivityModule {

    private final Activity activity;

    ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ForActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }
}
