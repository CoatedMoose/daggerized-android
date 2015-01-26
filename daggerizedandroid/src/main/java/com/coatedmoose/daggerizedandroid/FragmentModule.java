package com.coatedmoose.daggerizedandroid;

import dagger.Module;

@Module(
        addsTo = ActivityModule.class,
        injects = {
                DaggerFragment.class
        }
)
public class FragmentModule {
}
