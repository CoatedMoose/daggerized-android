package com.coatedmoose.daggerizedandroid;

import android.app.Service;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Service implementation that manages its own injection
 */
public abstract class DaggerService extends Service implements Injector {
    private ObjectGraph serviceGraph;

    @Override
    public <T> T inject(T o) {
        return serviceGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
        return serviceGraph.get(classType);
    }

    @Override
    public List<Object> getModules() {
        List<Object> modules = new ArrayList<>();
        modules.add(new ServiceModule(this));
        return modules;
    }

    @Override
    public ObjectGraph createObjectGraph(ObjectGraph parentGraph) {
        return parentGraph.plus(getModules());
    }
}
