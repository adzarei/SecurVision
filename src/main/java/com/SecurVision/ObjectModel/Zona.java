package com.SecurVision.ObjectModel;

/**
 * Created by adrian on 31/10/2016.
 */
public class Zona {
    private Integer zid;

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String descripcion;

    public Zona(){
        this.zid = null;
        this.descripcion = null;
    }

}
