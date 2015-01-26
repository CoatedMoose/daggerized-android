package com.coatedmoose.daggerizedandroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coatedmoose.daggerizedandroid.qualifier.ForApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Manage default injections for a the application level graph
 */
@Module(
        injects = DaggerApplication.class
)
public class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @ForApplication
    Application provideApplication() {
        return application;
    }

    @Provides
    SharedPreferences provideDefaultPreferences(@ForApplication Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
