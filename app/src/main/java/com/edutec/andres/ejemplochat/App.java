package com.edutec.andres.ejemplochat;

import android.app.Application;

import com.edutec.andres.ejemplochat.firebase.DI.FirebaseModule;
import com.edutec.andres.ejemplochat.firebase.FirebaseHelper;
import com.edutec.andres.ejemplochat.lib.DI.DaggerLibComponent;
import com.edutec.andres.ejemplochat.lib.DI.LibsModule;
import com.edutec.andres.ejemplochat.main.DI.DaggerMainComponent;
import com.edutec.andres.ejemplochat.main.DI.MainComponent;
import com.edutec.andres.ejemplochat.main.DI.MainModule;
import com.edutec.andres.ejemplochat.main.ui.View;

/**
 * Created by Andres on 12/05/2018.
 */

public class App extends Application{

    private static App instance;
    private AppModule appModule;
    private LibsModule libsModule;
    private FirebaseModule firebaseModule;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initModules();
    }

    private void initModules() {
        this.libsModule = new LibsModule();
        this.appModule = new AppModule(this);
        this.firebaseModule = new FirebaseModule();
    }

    public static synchronized App getInstance() { return instance; }

    public static String getUserSharedPreferences() { return "my-simple-chat"; }

    public MainComponent main(View view){
        return DaggerMainComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .firebaseModule(firebaseModule)
                .mainModule(new MainModule(view))
                .build();
    }

}
