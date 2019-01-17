package com.goozix.githubdisplay.app;

import android.app.Application;

import com.goozix.githubdisplay.injection.AppComponent;
import com.goozix.githubdisplay.injection.AppModule;
import com.goozix.githubdisplay.injection.DaggerAppComponent;

public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
