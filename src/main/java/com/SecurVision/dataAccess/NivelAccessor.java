package com.SecurVision.dataAccess;

import com.SecurVision.ObjectModel.Nivel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 13/12/2016.
 */
public class NivelAccessor {
    private Connection conn;

    public NivelAccessor(Connection conn) {this.conn = conn;}

    public List<Nivel> getNiveles() throws SQLException {
        String query ="SELECT * FROM Nivel";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();


        List<Nivel> nivelList = new ArrayList<>();
        while(rs.next()){
            String id = rs.getString(1);
            String desc = rs.getString(2);
            nivelList.add(new Nivel(id,desc));
        }
        return nivelList;
    }

    public boolean createNivel(String id, String desc) throws SQLException {
        String query = "INSERT INTO `SecureVision`.`Nivel` (`id`, `descripcion`) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,id);
        ps.setString(2,desc);
        boolean inserted = ps.executeUpdate()>0;
        return inserted;
    }

    public boolean deleteNivel(String id) throws SQLException {
        String query ="DELETE FROM Nivel WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,id);

        return ps.executeUpdate()>0;

    }

    public boolean nivelHasZona(String zid, String id) throws SQLException {
        String query2 = "INSERT INTO Zona_has_Nivel(Zona_id,Nivel_id) values (?,?)";
        PreparedStatement ps2 = conn.prepareStatement(query2);
        ps2.setString(1,zid);
        ps2.setString(2,id);
        return ps2.executeUpdate()>0;
    }

    public boolean delteZonaHasNivel(String id) throws SQLException {
        String query = "DELETE FROM Zona_has_Nivel  WHERE Nivel_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,id);
        return ps.executeUpdate() > 0;
    }
}
