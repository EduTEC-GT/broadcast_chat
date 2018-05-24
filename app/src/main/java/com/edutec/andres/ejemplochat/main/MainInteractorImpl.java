package com.edutec.andres.ejemplochat.main;

import android.content.Context;

import com.edutec.andres.ejemplochat.Event;
import com.edutec.andres.ejemplochat.R;
import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.lib.base.EventBus;
import com.edutec.andres.ejemplochat.main.MainInteractor;

/**
 * Created by Andres on 12/05/2018.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainRepo repo;
    private Context context;
    private EventBus bus;

    public MainInteractorImpl(MainRepo repo, Context context, EventBus bus) {
        this.repo = repo;
        this.context = context;
        this.bus = bus;
    }

    @Override
    public void getMessage() {
        repo.getMessage();
    }

    @Override
    public void verifyUser() {
        repo.verifyUser();
    }

    @Override
    public void getUser() {
        repo.getUser();
    }

    @Override
    public void sendMessage(Message message) {
        if(message.getMensaje() != null && !message.getMensaje().isEmpty()){
            repo.sendMessage(message);
        }else {
            Event event = new Event();
            event.setError(context.getString(R.string.no_messages));
            bus.post(event);
        }
    }

    @Override
    public void noMessages() {
        repo.noMessages();
    }
}
