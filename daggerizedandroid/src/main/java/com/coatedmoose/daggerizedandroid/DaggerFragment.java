package com.coatedmoose.daggerizedandroid;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

public class DaggerFragment extends Fragment implements Injector {
    private ObjectGraph fragmentGraph;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        fragmentGraph = createObjectGraph(((GraphHolder) getActivity()).getObjectGraph());
        fragmentGraph.inject(this);
    }

    @Override
    public <T> T inject(T o) {
        if (fragmentGraph == null) {
            throw new IllegalStateException("Fragment object graph must be initialized before calling inject");
        }
        return fragmentGraph.inject(o);
    }

    @Override
    public <T> T get(Class<T> classType) {
        if (fragmentGraph == null) {
            throw new IllegalStateException(
                    "Fragment object graph must be initialized before getting an object from the graph");
        }
        return fragmentGraph.get(classType);
    }


    @Override
    public ObjectGraph createObjectGraph(ObjectGraph parentGraph) {
        return parentGraph.plus(getModules().toArray());
    }

    @Override
    public List<Object> getModules() {
        List<Object> modules = new ArrayList<>();
        modules.add(new FragmentModule());
        return modules;
    }
}
