package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Checkeo;
import com.SecurVision.dataAccess.Accessors;

import javax.ws.rs.core.GenericEntity;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 26/10/2016.
 */
public class CheckeoManager {

    private Accessors a;

    public CheckeoManager(Accessors a) {
        this.a = a;
    }

    public GenericEntity<List<Checkeo>> getCheckeosByPersona(String dni) throws SQLException {
        return new  GenericEntity<List<Checkeo>>(a.checkeoAccessor.getCheckeosByPersona(dni)){};
    }
}
