package com.edutec.andres.ejemplochat.firebase;

/**
 * Created by Andres on 12/05/2018.
 */

public interface FirebaseResult {
    void onResult(Object o);
    void onError(String error);
}
