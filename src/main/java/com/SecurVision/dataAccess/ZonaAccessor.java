package com.SecurVision.dataAccess;

import com.SecurVision.ObjectModel.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by adrian on 01/11/2016.
 */
public class ZonaAccessor {

    public Connection conn;
    public ZonaAccessor(Connection conn){this.conn = conn;}

    public ArrayList<Zona> getZonas() throws SQLException {
        String query = "SELECT * FROM Zona";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ArrayList<Zona> zonas = readResultSet(rs);
        return zonas;
    }
    public ArrayList<Zona> readResultSet(ResultSet rs) throws SQLException {
        ArrayList<Zona> zonas = new ArrayList<>();
        while (rs.next()) {
            Zona z = new Zona();
            z.setZid(rs.getInt("id"));
            z.setDescripcion(rs.getString("descripcion"));
            zonas.add(z);
        }
        return zonas;
    }
}
