package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Nivel;
import com.SecurVision.dataAccess.Accessors;
import org.json.JSONObject;

import javax.ws.rs.core.GenericEntity;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 13/12/2016.
 */
public class NivelManager {

    private final Accessors a;

    public NivelManager(Accessors accessors) {
        this.a = accessors;
    }

    public GenericEntity<List<Nivel>> getNiveles() throws SQLException {
        return new GenericEntity<List<Nivel>>(a.nivelAccessor.getNiveles()){};
    }

    public boolean createNivel(String json) throws SQLException {
        JSONObject jo = new JSONObject(json);
        String id = jo.getString("id");
        String desc = jo.getString("descripcion");
        String zid = jo.getString("id_zona");

        boolean res = a.nivelAccessor.createNivel(id,desc);
        return res && a.nivelAccessor.nivelHasZona(zid,id);
}
    public boolean deleteNivel(String id) throws SQLException {
        boolean res = a.nivelAccessor.deleteNivel(id);
        if (res)
            a.nivelAccessor.delteZonaHasNivel(id);
        return res;
    }
}
