package com.kioli.mvpretrorxexample;

import android.app.Application;

import com.kioli.mvpretrorxexample.core.data.NetworkService;

public class App extends Application {

    private static App appContext;

    private NetworkService networkService;

    public App() {
        appContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        networkService = new NetworkService();
    }

    public static App getInstance() {
        return appContext;
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}
