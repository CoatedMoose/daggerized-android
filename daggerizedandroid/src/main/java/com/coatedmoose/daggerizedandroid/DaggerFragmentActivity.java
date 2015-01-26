package com.coatedmoose.daggerizedandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

public class DaggerFragmentActivity extends FragmentActivity implements Injector, GraphHolder {
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
        return activityGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
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
        return parentGraph.plus(getModules().toArray());
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return activityGraph;
    }
}
