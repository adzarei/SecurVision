package com.SecurVision.dataAccess;

import com.SecurVision.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by adrian on 25/10/2016.
 */
public class Accessors {
    private static Accessors ourInstance = new Accessors();
    public PersonaAccessor personaAccessor;
    public CheckeoAccessor checkeoAccessor;
    public UsuarioAccessor usuarioAccessor;
    public ZonaAccessor    zonaAccessor;
    public AutenticationAccessor autenticationAccessor;

    public Connection conn;


    public Accessors() {

        try {
            managerConnections();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.personaAccessor = new PersonaAccessor(conn);
        this.checkeoAccessor = new CheckeoAccessor(conn);
        this.usuarioAccessor = new UsuarioAccessor(conn);
        this.zonaAccessor    = new ZonaAccessor(conn);
        this.autenticationAccessor = new AutenticationAccessor();
    }

    private void managerConnections() throws ClassNotFoundException, SQLException {
            Class.forName(Constants.DRIVER);
            this.conn = DriverManager.getConnection(Constants.CONNECTIONSTRING, Constants.USER,Constants.PASSWORD);
    }

    public static Accessors getInstance() {
        return ourInstance;
    }
}


//TODO: Close all PreparedStatements and ResultSets.