package com.edutec.andres.ejemplochat.main;

import com.edutec.andres.ejemplochat.entities.Message;

/**
 * Created by Andres on 12/05/2018.
 */

public interface MainInteractor {
    void getMessage();
    void verifyUser();
    void getUser();
    void sendMessage(Message message);
    void noMessages();
}
