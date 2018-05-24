package com.edutec.andres.ejemplochat.main;

import android.content.Context;

import com.edutec.andres.ejemplochat.Event;
import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.lib.base.EventBus;
import com.edutec.andres.ejemplochat.main.ui.View;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Andres on 12/05/2018.
 */

public class MainPresenterImpl implements MainPresenter {

    private EventBus bus;
    private Context context;
    private View view;
    private MainInteractor interactor;

    public MainPresenterImpl(EventBus bus, Context context, View view, MainInteractor interactor){
        this.bus = bus;
        this.context = context;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() { bus.register(this); }

    @Override
    public void onDestroy() { bus.unRegister(this); }

    @Override
    @Subscribe
    public void onEvent(Event event) {
        view.load(false);
        if (event.getError() != null && !event.getError().isEmpty()) {
            view.showMessage(event.getMensaje());
        }
        switch (event.getTipo()) {
            case Event.sendMessage:
                view.sentMessage();
                break;
            case Event.newMessage:
                view.newMessage((Message) event.getO());
                break;
            case Event.getUser:
                view.getUser((FirebaseUser) event.getO());
                break;
            case Event.verifyUser:
                view.verifyUser((boolean) event.getO());
                break;
        }
    }

    @Override
    public void getMessage() {
        view.load(true);
        interactor.getMessage();
    }

    @Override
    public void sendMessages(Message message) {
        view.load(true);
        interactor.sendMessage(message);
    }

    @Override
    public void verifyUser() {
        view.load(true);
        interactor.verifyUser();
    }

    @Override
    public void getUser() {
        view.load(true);
        interactor.getUser();
    }

    @Override
    public void noMessages() {
        view.load(true);
        interactor.noMessages();
    }
}
