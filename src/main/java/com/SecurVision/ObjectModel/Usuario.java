package com.SecurVision.ObjectModel;

/**
 * Created by adrian on 26/10/2016.
 */
public class Usuario extends Persona {
    private String username;
    private String password;

    public Usuario(){
        super();
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String dni, String nombre, String apellidos, String username, String password) {
        super(dni, nombre, apellidos);
        this.username = username;
        this.password = password;
    }
}
