package com.edutec.andres.ejemplochat.lib.DI;

import com.edutec.andres.ejemplochat.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 11/12/2017.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class})
public interface LibComponent {
}
