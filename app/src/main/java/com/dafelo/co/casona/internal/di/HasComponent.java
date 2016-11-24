package com.dafelo.co.casona.internal.di;

/**
 * Created by root on 24/10/16.
 */

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface HasComponent<C> {
    C getComponent();
}
