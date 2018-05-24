package com.edutec.andres.ejemplochat.main.ui;

import com.edutec.andres.ejemplochat.entities.Message;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Andres on 12/05/2018.
 */

public interface View {
    void load(boolean loading);
    void showError(String error);
    void showMessage(String message);
    void newMessage(Message message);
    void sentMessage();

    void getUser(FirebaseUser o);

    void verifyUser(boolean o);
}
