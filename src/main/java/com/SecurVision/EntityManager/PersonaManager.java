package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Persona;
import com.SecurVision.dataAccess.Accessors;
import org.json.JSONObject;

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

    public boolean createPersonax(String jsonString) throws SQLException {
        JSONObject jo = new JSONObject(jsonString);

        String dni = jo.getString("dni");
        String nombre = jo.getString("nombre");
        String apellidos = jo.getString("apellidos");
        String nivel_id = jo.getString("nivel_id");
        String horario_id = jo.getString("horario_id");
        String isUsuario = jo.getString("isUsuario");

        Persona persona = new Persona(dni,nombre,apellidos,nivel_id,horario_id,isUsuario);

        a.personaAccessor.createPerson(persona);
        return true;
    }

}
