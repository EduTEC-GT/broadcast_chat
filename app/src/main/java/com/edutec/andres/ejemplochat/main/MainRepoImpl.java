package com.edutec.andres.ejemplochat.main;

import android.content.Context;

import com.edutec.andres.ejemplochat.Event;
import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.firebase.FirebaseHelper;
import com.edutec.andres.ejemplochat.firebase.FirebaseResult;
import com.edutec.andres.ejemplochat.lib.base.EventBus;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Andres on 12/05/2018.
 */

public class MainRepoImpl implements MainRepo {

    private Context context;
    private EventBus bus;
    private FirebaseHelper helper;

    public MainRepoImpl(Context context, EventBus bus, FirebaseHelper helper) {
        this.context = context;
        this.bus = bus;
        this.helper = helper;
    }

    @Override
    public void getMessage() {
        helper.getMessages();
    }

    @Override
    public void verifyUser() {
        helper.verifyUser(new FirebaseResult() {
            @Override
            public void onResult(Object o) {
                bus.post(new Event(Event.verifyUser, null, null, o));
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void getUser() {
        helper.getUser(new FirebaseResult() {
            @Override
            public void onResult(Object o) {
                bus.post(new Event(Event.getUser, null, null, o ));
            }

            @Override
            public void onError(String error) {
                bus.post(new Event(Event.getUser, null, error, null));
            }
        });
    }

    @Override
    public void sendMessage(final Message message) {
        helper.sendMessage(message, new FirebaseResult() {
            @Override
            public void onResult(Object o) {
                bus.post(new Event(Event.sendMessage, null, null, null));
            }

            @Override
            public void onError(String error) {
                bus.post(new Event(Event.sendMessage, null, error, null));

            }
        });

    }

    @Override
    public void noMessages() {
        helper.notMessage();
        bus.post(new Event());
    }
}
