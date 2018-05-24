package com.edutec.andres.ejemplochat.main;

import com.edutec.andres.ejemplochat.Event;
import com.edutec.andres.ejemplochat.entities.Message;

/**
 * Created by Andres on 12/05/2018.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void onEvent(Event event);

    void getMessage();

    void sendMessages(Message message);

    void verifyUser();

    void getUser();

    void noMessages();


}
