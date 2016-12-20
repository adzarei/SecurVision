package com.SecurVision.ObjectModel;

/**
 * Created by adrian on 13/12/2016.
 */
public class Nivel {

    String id;
    String descripcion;

    public Nivel() {}

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public  Nivel(String id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
    }


}
