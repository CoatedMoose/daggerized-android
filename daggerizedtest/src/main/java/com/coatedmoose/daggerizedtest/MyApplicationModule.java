package com.coatedmoose.daggerizedtest;

import android.content.Context;
import android.content.SharedPreferences;

import com.coatedmoose.daggerizedandroid.ApplicationModule;
import com.coatedmoose.daggerizedandroid.qualifier.ForApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        addsTo = ApplicationModule.class,
        injects = MyApplication.class,
        library = true
)
public class MyApplicationModule {

    @Provides
    @Singleton
    @Named("saved_text")
    StringPreference provideSavedTextPreference(@ForApplication Context context, SharedPreferences preferences) {
        return new StringPreference(preferences, "saved_text", context.getString(R.string.hello_world));
    }
}
