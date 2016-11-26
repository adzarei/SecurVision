package com.SecurVision.ObjectModel;

/**
 * Created by adrian on 25/10/2016.
 */
public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private String horaUltimoCheckeo;
    private String nivel_id;
    private String horario_id;
    private String isUsuario;

    public Persona(){}


    public Persona(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.horaUltimoCheckeo = null;
    }

    public Persona(String dni, String nombre, String apellidos, String nivel_id, String horario_id, String isUsuario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nivel_id = nivel_id;
        this.horario_id = horario_id;
        this.isUsuario = isUsuario;
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

    public String getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(String nivel_id) {
        this.nivel_id = nivel_id;
    }

    public String getHorario_id() {
        return horario_id;
    }

    public void setHorario_id(String horario_id) {
        this.horario_id = horario_id;
    }

    public String getIsUsuario() {
        return isUsuario;
    }

    public void setIsUsuario(String isUsuario) {
        this.isUsuario = isUsuario;
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
