package com.edutec.andres.ejemplochat.main.DI;

import com.edutec.andres.ejemplochat.AppModule;


import javax.inject.Singleton;

import dagger.Component;
import com.edutec.andres.ejemplochat.AppModule;
import com.edutec.andres.ejemplochat.firebase.DI.FirebaseModule;
import com.edutec.andres.ejemplochat.lib.DI.LibsModule;
import com.edutec.andres.ejemplochat.main.ui.MainActivity;

/**
 * Created by Andres on 19/05/2018.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, FirebaseModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
