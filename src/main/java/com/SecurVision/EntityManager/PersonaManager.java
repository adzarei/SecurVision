package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Persona;
import com.SecurVision.dataAccess.Accessors;

import javax.ws.rs.core.GenericEntity;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 26/10/2016.
 */
public class PersonaManager {
    private Accessors a;
    public PersonaManager(Accessors a){
        this.a = a;
    }

    public Persona getPersonaByDni(String dni) throws Exception {
        return a.personaAccessor.getPersonaByDni(dni);
    }

    public GenericEntity<List<Persona>> getPersonaByZona(int zid) throws SQLException {
        return  new GenericEntity<List<Persona>>(a.personaAccessor.getPersonaByZona(zid)){};
    }

}
