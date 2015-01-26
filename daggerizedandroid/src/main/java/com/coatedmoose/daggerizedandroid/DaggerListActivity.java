package com.coatedmoose.daggerizedandroid;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

public class DaggerListActivity extends ListActivity implements Injector, GraphHolder {
    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGraph = createObjectGraph(((GraphHolder) getApplication()).getObjectGraph());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;

        super.onDestroy();
    }

    @Override
    public <T> T inject(T o) {
        if (activityGraph == null) {
            throw new IllegalStateException("Activity object graph needs to be initialized before calling inject");
        }
        return activityGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
        if (activityGraph == null) {
            throw new IllegalStateException(
                    "Activity object graph needs to be initialized before getting an object from the graph");
        }
        return activityGraph.get(classType);
    }
    @Override
    public List<Object> getModules() {
        List<Object> modules = new ArrayList<>();
        modules.add(new ActivityModule(this));
        return modules;
    }

    @Override
    public ObjectGraph createObjectGraph(ObjectGraph parentGraph) {
        return parentGraph.plus(getModules());
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return activityGraph;
    }
}
