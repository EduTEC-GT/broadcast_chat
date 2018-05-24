package com.edutec.andres.ejemplochat;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app= app;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return this.app;
    }

    @Singleton
    @Provides
    Context providesApplicationContext() {
        return  this.app.getApplicationContext();
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences(
                app.getUserSharedPreferences(),
                Context.MODE_PRIVATE
        );
    }
}
