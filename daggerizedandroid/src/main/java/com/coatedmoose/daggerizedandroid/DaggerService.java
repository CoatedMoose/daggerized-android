package com.coatedmoose.daggerizedandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Service implementation that manages its own injection
 */
public abstract class DaggerService extends Service implements Injector {
    private ObjectGraph serviceGraph;
    private boolean initialized = false;

    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {
        initializeIfNeeded(intent);
        return onStartCommandDagger(intent, flags, startId);
    }

    @Override
    public final IBinder onBind(Intent intent) {
        initializeIfNeeded(intent);
        return onBindDagger(intent);
    }

    /**
     * @see #onStartCommand(Intent, int, int)
     */
    protected int onStartCommandDagger(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * @see #onBind(Intent)
     */
    protected abstract IBinder onBindDagger(Intent intent);

    @Override
    public <T> T inject(T o) {
        if (serviceGraph == null) {
            throw new IllegalStateException("Service object graph must be initialized before calling inject");
        }
        return serviceGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
        if (serviceGraph == null) {
            throw new IllegalStateException(
                    "Service object graph needs to be initialized before getting an object from the graph");
        }
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
        return parentGraph.plus(getModules().toArray());
    }

    private void initializeIfNeeded(Intent intent) {
        if (!initialized) {
            serviceGraph = createObjectGraph(((GraphHolder) getApplication()).getObjectGraph());
            initialized = true;
        }
    }
}
