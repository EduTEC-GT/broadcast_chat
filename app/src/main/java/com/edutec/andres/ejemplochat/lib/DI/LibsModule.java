package com.edutec.andres.ejemplochat.lib.DI;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.edutec.andres.ejemplochat.lib.GlideImageLoader;
import com.edutec.andres.ejemplochat.lib.GreenRobotEventBus;
import com.edutec.andres.ejemplochat.lib.base.EventBus;
import com.edutec.andres.ejemplochat.lib.base.ImageLoader;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 11/12/2017.
 */
@Module
public class LibsModule {
    public LibsModule() {
    }

    @Singleton
    @Provides
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus bus){
        return new GreenRobotEventBus(bus);
    }

    @Singleton
    @Provides
    org.greenrobot.eventbus.EventBus providesGreenRobot(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Singleton
    @Provides
    ImageLoader providesImageLoader(RequestManager manager){
        return new GlideImageLoader(manager);
    }

    @Singleton
    @Provides
    RequestManager providesRequestManager(Context context){
        return Glide.with(context);
    }

}
