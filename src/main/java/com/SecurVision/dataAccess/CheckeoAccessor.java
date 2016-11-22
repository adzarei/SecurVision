package com.SecurVision.dataAccess;

import com.SecurVision.ObjectModel.Checkeo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by adrian on 26/10/2016.
 */
public class CheckeoAccessor {

    private Connection conn;

    public CheckeoAccessor(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Checkeo> getCheckeosByPersona(String dni) throws SQLException {
        String query = "SELECT hora , Zona_id " +
                        "FROM Checkeo " +
                        "WHERE Persona_dni = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,dni);
        ResultSet rs = ps.executeQuery();
        ArrayList<Checkeo> checkeoList = new ArrayList<>();
        while(rs.next()){
            Checkeo c = new Checkeo();
            c.setHora(rs.getTimestamp("hora").toString());
            c.setZonaId(rs.getInt("Zona_id"));
            checkeoList.add(c);
        }
        return checkeoList;
    }
}
