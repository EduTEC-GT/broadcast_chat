package com.edutec.andres.ejemplochat.firebase.DI;

import android.content.Context;

import com.edutec.andres.ejemplochat.firebase.FirebaseHelper;
import com.edutec.andres.ejemplochat.lib.base.EventBus;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class FirebaseModule {

    private FirebaseHelper helper;


    @Singleton
    @Provides
    FirebaseHelper providesFirebaseHelper(Context context, EventBus bus) {
        return new FirebaseHelper(context, bus);
    };

}
