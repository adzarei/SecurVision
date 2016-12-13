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

    public GenericEntity<List<Nivel>> getNiverles() throws SQLException {
        return new GenericEntity<List<Nivel>>(a.nivelAccessor.getNiveles()){};
    }

    public boolean createNivel(String json) throws SQLException {
        JSONObject jo = new JSONObject(json);
        String id = jo.getString("id");
        String desc = jo.getString("descripcion");

        return a.nivelAccessor.createNivel(id,desc);
    }
    public boolean deleteNivel(String id) throws SQLException {
        return a.nivelAccessor.deleteNivel(id);
    }
}
