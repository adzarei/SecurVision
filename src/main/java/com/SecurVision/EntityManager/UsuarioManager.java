package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Usuario;
import com.SecurVision.dataAccess.Accessors;
import org.json.JSONObject;

import javax.ws.rs.core.GenericEntity;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by adrian on 26/10/2016.
 */
public class UsuarioManager {
    private Accessors a;
    public UsuarioManager(Accessors a){
       this.a = a;
    }


    public Usuario getUsuarioByDni(String dni){
        return null;//a.usuarioAccessor.getUsuarioByDni(dni);
    }

    public GenericEntity<List<Usuario>> getUsuarioByZona(int zid) throws SQLException {
        return new GenericEntity<List<Usuario>> (a.usuarioAccessor.getUsuarioByZona(zid)){};
    }

    public Boolean login(String nick, String password) throws SQLException {
        return a.usuarioAccessor.login(nick,password);
    }


    public boolean createUsuario(String json) throws SQLException {
        JSONObject jo = new JSONObject(json);

        String dni = jo.getString("dni");
        String nombre = jo.getString("nombre");
        String apellidos = jo.getString("apellidos");
        String username = jo.getString("username");
        String password = jo.getString("password");
        String tipo = jo.getString("tipo");

        Usuario usr = new Usuario(dni,nombre,apellidos,username,password,tipo);

        try {
            return a.usuarioAccessor.createUsuario(usr);
        } catch (SQLException e) {
            throw new SQLException(e);//Debugg.
            //return false;
        }
    }

}


