package com.kioli.mvpretrorxexample;

import android.app.Application;

import com.kioli.mvpretrorxexample.core.data.ServiceGenerator;

public class App extends Application {

    private static App appContext;

    private ServiceGenerator serviceGenerator;

    public App() {
        appContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceGenerator = new ServiceGenerator();
    }

    public static App getInstance() {
        return appContext;
    }

    public ServiceGenerator getServiceGenerator(){
        return serviceGenerator;
    }
}
