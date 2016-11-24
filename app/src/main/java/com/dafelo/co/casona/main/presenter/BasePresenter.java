package com.dafelo.co.casona.main.presenter;

/**
 * Created by root on 25/08/16.
 */
public interface BasePresenter<T> {

    void subscribe();
    void setView(T view);
    void unsubscribe();

}
