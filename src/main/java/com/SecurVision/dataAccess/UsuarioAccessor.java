package com.SecurVision.dataAccess;

import com.SecurVision.ObjectModel.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteUsuario(String dni) throws SQLException {
        String query = "DELETE FROM Usuario " +
                "               WHERE Persona_dni = ?";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);

        ps.setString(1,dni);
        int rs = ps.executeUpdate();
        ps.close();
        return rs >0;
    }

    public boolean isUsuario(String dni) throws SQLException {
        String query = "SELECT isUsuario FROM Persona" +
                "                        WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,dni);
        ResultSet rs;
        try {
             rs = ps.executeQuery();
        }catch (SQLException e){
            return false;
        }
        boolean res = false;
        if(rs.next())
            res = rs.getInt("isUsuario") == 1;

        rs.close();
        ps.close();
        return res ;
    }

    public List<Usuario> getUsuarios() throws SQLException {
        List<Usuario> usrList = new ArrayList<>();

        String query = "SELECT u.nick, u.tipo, u.Persona_dni ,p.nombre,p.apellidos,u.contrasenya " +
                "               From Usuario u ,Persona p " +
                "               WHERE u.Persona_dni = p.dni";

        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs  = ps.executeQuery();
        while (rs.next()){
            String username = rs.getString(1);
            String tipo =  rs.getString(2);
            String dni =   rs.getString(3);
            String nombre = rs.getString(4);
            String apellidos = rs.getString(5);
            String password = rs.getString(6);
            usrList.add(new Usuario(dni,nombre,apellidos,username,password,tipo));
        }
        return usrList;
    }

}

