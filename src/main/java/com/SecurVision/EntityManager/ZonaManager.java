package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Zona;
import com.SecurVision.dataAccess.Accessors;

import javax.ws.rs.core.GenericEntity;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 01/11/2016.
 */
public class ZonaManager {
    public Accessors a;
    public ZonaManager(Accessors acc){this.a = acc;}


    public GenericEntity<List<Zona>> getZonas() throws SQLException {
        return new GenericEntity<List<Zona>>(a.zonaAccessor.getZonas()){};
    }

    public Boolean createZona(String json) {


    }
}
