package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Zona;
import com.SecurVision.dataAccess.Accessors;
import org.json.JSONObject;

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

    public Boolean createZona(String json) throws SQLException {
        JSONObject jo = new JSONObject(json);

        //String id = jo.getString("id");
        String desc = jo.getString("desc");

        return a.zonaAccessor.createZona(desc);
    }

    public Boolean deleteZona(String zid) throws SQLException {
        boolean res = a.zonaAccessor.deleteZona(zid);
        if(res)
            a.zonaAccessor.deleteZonaHAsNivel(zid);
        return res;
    }
}
