package com.coatedmoose.daggerizedandroid;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Extend this class to have your application inject off of its graph
 */
public class DaggerApplication extends Application implements Injector, GraphHolder {
    private ObjectGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationGraph = createObjectGraph(ObjectGraph.create());
        inject(this);
    }

    @Override
    public <T> T inject(T o) {
        if (applicationGraph == null) {
            throw new IllegalStateException("Application object graph needs to be initialized before calling inject");
        }
        return applicationGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
        return applicationGraph.get(classType);
    }

    @Override
    public List<Object> getModules() {
        List<Object> modules = new ArrayList<>();
        modules.add(new ApplicationModule(this));
        return modules;
    }

    @Override
    public ObjectGraph createObjectGraph(ObjectGraph parentGraph) {
        return applicationGraph.plus(getModules().toArray());
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return applicationGraph;
    }
}
