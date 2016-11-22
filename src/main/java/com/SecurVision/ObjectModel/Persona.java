package com.SecurVision.ObjectModel;

/**
 * Created by adrian on 25/10/2016.
 */
public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private String horaUltimoCheckeo;

    public Persona(){}


    public Persona(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.horaUltimoCheckeo = null;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getHoraUltimoCheckeo() {
        return horaUltimoCheckeo;
    }

    public void setHoraUltimoCheckeo(String horaUltimoCheckeo) {
        this.horaUltimoCheckeo = horaUltimoCheckeo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
