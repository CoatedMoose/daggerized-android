package com.coatedmoose.daggerizedandroid;

import android.app.ListActivity;

import java.util.List;

import dagger.ObjectGraph;

/**
 */
public class DaggerListActivity extends ListActivity implements Injector {
    private ObjectGraph activityGraph;

    @Override
    public <T> T inject(T o) {
        return activityGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
        return activityGraph.get(classType);
    }

    @Override
    public List<Object> getModules() {
        return null;
    }

    @Override
    public ObjectGraph createObjectGraph(ObjectGraph parentGraph) {
        return null;
    }
}
