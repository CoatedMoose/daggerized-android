package com.coatedmoose.daggerizedandroid;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Implementers can inject objects.
 */
public interface Injector {

    /**
     * Inject parameters into an object
     * @param o the object to inject parameters into
     * @return the newly injected object
     */
    <T> T inject(T o);

    /**
     * Get an injected instance of an object
     * @param classType of the object to get an injected instance of
     * @return the injected instance
     */
    <T> T get(Class<T> classType);

    /**
     * @return the list of modules that the instantiation of this object can satisfy
     */
    List<Object> getModules();

    /**
     * Create the object graph. Base implementations should be mostly sane. Override if there is
     * conditional logic dependant on the parent graph.
     *
     * @param parentGraph base for child graph
     * @return child graph
     */
    ObjectGraph createObjectGraph(ObjectGraph parentGraph);
}
