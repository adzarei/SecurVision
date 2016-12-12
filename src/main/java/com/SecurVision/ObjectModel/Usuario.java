package com.SecurVision.ObjectModel;

/**
 * Created by adrian on 26/10/2016.
 */
public class Usuario extends Persona {
    private String username;
    private String password;
    private String tipo;

    public Usuario(){
        super();
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.tipo = "1";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario(String dni, String nombre, String apellidos, String username, String password, String tipo) {
        super(dni, nombre, apellidos);
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }
}
