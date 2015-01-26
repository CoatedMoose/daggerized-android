package com.coatedmoose.daggerizedandroid;

import dagger.Module;

@Module(
        addsTo = ActivityModule.class,
        injects = {
                DaggerFragment.class
        },
        library = true
)
public class FragmentModule {
}
