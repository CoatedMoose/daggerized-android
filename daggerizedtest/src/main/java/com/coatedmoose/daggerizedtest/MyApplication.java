package com.coatedmoose.daggerizedtest;

import com.coatedmoose.daggerizedandroid.DaggerApplication;

import java.util.List;

public class MyApplication extends DaggerApplication {

    @Override
    public List<Object> getModules() {
        List<Object> modules = super.getModules();
        modules.add(new MyApplicationModule());
        return modules;
    }
}
