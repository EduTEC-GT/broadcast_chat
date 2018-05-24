package com.edutec.andres.ejemplochat.firebase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.edutec.andres.ejemplochat.Event;
import com.edutec.andres.ejemplochat.R;
import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.lib.base.EventBus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Andres on 12/05/2018.
 */

public class FirebaseHelper {

    private Context context;
    private FirebaseAuth mauth;
    private DatabaseReference ref;
    private ChildEventListener listener;
    private EventBus bus;


    public FirebaseHelper(Context context, EventBus bus) {
        this.context = context;
        this.bus = bus;
        this.mauth = FirebaseAuth.getInstance();
        this.ref = FirebaseDatabase.getInstance().getReference("messages");
    }

    public void verifyUser(FirebaseResult result) {
        result.onResult(mauth.getCurrentUser() != null);
    }

    public void getUser(FirebaseResult result) {
        if (mauth.getCurrentUser() != null) {
            result.onResult(mauth.getCurrentUser());
        }else{
            result.onError(context.getString(R.string.no_user));
        }
    }

    public void sendMessage(Message message, final FirebaseResult result) {
        ref.push().setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    result.onResult(null);
                }else {
                    result.onError(task.getException().getLocalizedMessage());
                }
            }
        });
    }

    public void getMessages(){
        listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                Event event = new Event(Event.newMessage, null, null, message);
                bus.post(event);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ref.orderByChild("time").addChildEventListener(listener);
    }

    public void notMessage() {
        ref.removeEventListener(this.listener);
    }

}
