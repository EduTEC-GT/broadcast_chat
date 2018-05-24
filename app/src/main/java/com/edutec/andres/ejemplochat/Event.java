package com.edutec.andres.ejemplochat;

/**
 * Created by Andres on 12/05/2018.
 */

public class Event {

    public final static int verifyUser = 0;
    public final static int getUser = 1;
    public final static int sendMessage = 2;
    public final static int newMessage = 3;

    private int tipo;
    private String mensaje, error;
    private Object o;

    public Event (){

    }

    public Event(int tipo, String mensaje, String error, Object o) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.error = error;
        this.o = o;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
