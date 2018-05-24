package com.edutec.andres.ejemplochat.entities;

import java.io.Serializable;

/**
 * Created by Andres on 12/05/2018.
 */

public class Message implements Serializable {
    private String user, avatar, mensaje;
    private long time;

    public Message (){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Message(String user, String avatar, String mensaje, long time) {

        this.user = user;
        this.avatar = avatar;
        this.mensaje = mensaje;
        this.time = time;
    }
}
