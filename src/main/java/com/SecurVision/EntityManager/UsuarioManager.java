package com.SecurVision.EntityManager;

import com.SecurVision.ObjectModel.Usuario;
import com.SecurVision.dataAccess.Accessors;

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
        return a.usuarioAccessor.getUsuarioByDni(dni);
    }

    public GenericEntity<List<Usuario>> getUsuarioByZona(int zid) throws SQLException {
        return new GenericEntity<List<Usuario>> (a.usuarioAccessor.getUsuarioByZona(zid)){};
    }

    public Boolean login(String nick, String password) throws SQLException {
        return a.usuarioAccessor.login(nick,password);
    }
}


