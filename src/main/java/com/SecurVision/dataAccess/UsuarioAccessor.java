package com.SecurVision.dataAccess;

import com.SecurVision.ObjectModel.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by adrian on 26/10/2016.
 */
public class UsuarioAccessor {
    private final Connection conn;

    public UsuarioAccessor(Connection conn){
        this.conn = conn;
    }

    public ArrayList <Usuario> getUsuarioByZona(int zid) throws SQLException {
        String query = "SELECT * FROM( SELECT p.dni,p.nombre,p.apellidos,c.idChekeo,c.hora,c.Zona_id" +
                "                        FROM Persona p " +
                "                        JOIN Checkeo c " +
                "                        ON p.dni = c.Persona_dni" +
                "                        WHERE c.valido = 1 AND p.isUsuario" +
                "                        ORDER BY c.idChekeo DESC" +
                "                        ) AS x" +
                "                        GROUP BY x.dni";
        PreparedStatement ps;

        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Usuario> usrlist = new ArrayList<>();
        while (rs.next()) {
            if (rs.getInt("Zona_id") == zid) {
                Usuario u = new Usuario();
                u.setDni(rs.getString("dni"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setHoraUltimoCheckeo(rs.getTimestamp("hora").toString());
                usrlist.add(u);
            }
        }
        return usrlist;
    }

    public Boolean login(String nick, String password) throws SQLException {
        String query = "SELECT * FROM Usuario " +
                "       WHERE nick = ? AND contrasenya = ?";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);

        ps.setString(1,nick);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        try{
            if(rs.next())
                return true;
            else return false;
        }catch(Exception e){
            return false;
        }
    }

    public boolean createUsuario(Usuario usr) throws SQLException{
        String query = "INSERT INTO `SecureVision`.`Usuario` (`nick`, `contrasenya`, `tipo`, `Persona_dni`) " +
                "              VALUES (?, ?, ?, ?)";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);

        ps.setString(1,usr.getUsername());
        ps.setString(2,usr.getPassword());
        ps.setString(3,usr.getTipo());
        ps.setString(4,usr.getDni());

        return ps.executeUpdate() > 0; //si es > 0 se ha borrado algún usuario.
    }
}

