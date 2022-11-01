package com.bloomtech.socialfeed.observerpattern;

public interface Source {
    /**
     * Attach method for Source.
     * @param observer to be attached
     */
    void attach(Observer observer);

    /**
     * Detach method for Source.
     * @param observer to detach
     */
    void detach(Observer observer);

    /**
     * Updates all the observer method.
     */
    void updateAll();
}
