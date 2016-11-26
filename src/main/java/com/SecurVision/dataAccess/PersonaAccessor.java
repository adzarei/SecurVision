package com.SecurVision.dataAccess;
import com.SecurVision.ObjectModel.Persona;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by adrian on 25/10/2016.
 */
public class PersonaAccessor {

    private final Connection conn;

    public PersonaAccessor(Connection conn) {
        this.conn = conn;
    }

    public Persona getPersonaByDni(String dni) throws Exception {


        String query = "SELECT * FROM SecureVision.Persona WHERE dni = ?";
        PreparedStatement ps;
            ps = conn.prepareStatement(query);

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            ArrayList<Persona> perlist = readResultSet(rs);
            Persona p = new Persona();

            if (!perlist.isEmpty())
                p = perlist.get(0);
            return p;
    }

    public ArrayList<Persona> getPersonaByZona(int zid) throws SQLException {
        String query = "SELECT * FROM( SELECT p.dni,p.nombre,p.apellidos,c.idChekeo,c.hora,c.Zona_id" +
                "                        FROM Persona p " +
                "                        JOIN Checkeo c " +
                "                        ON p.dni = c.Persona_dni" +
                "                        WHERE c.valido = 1 AND NOT p.isUsuario" +
                "                        ORDER BY c.idChekeo DESC" +
                "                        ) AS x" +
                "                 GROUP BY x.dni";

        PreparedStatement ps;
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Persona> perlist = new ArrayList<>();
        while (rs.next()) {
            if (rs.getInt("Zona_id") == zid) {
                Persona p = new Persona();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setApellidos(rs.getString("apellidos"));
                p.setHoraUltimoCheckeo(rs.getTimestamp("hora").toString());
                perlist.add(p);
            }

        }
            return perlist;
    }

    public ArrayList<Persona> readResultSet(ResultSet rs) throws SQLException {
        ArrayList<Persona> perlist = new ArrayList<>();
        while (rs.next()) {
            Persona p = new Persona();
            p.setDni(rs.getString("dni"));
            p.setNombre(rs.getString("nombre"));
            p.setApellidos(rs.getString("apellidos"));

            try{
                p.setHoraUltimoCheckeo(rs.getTimestamp("hora").toString());
            }catch (Exception e){}

            perlist.add(p);
        }
        return perlist;
    }

    public boolean createPerson(Persona persona) throws SQLException {
        String query = "INSERT INTO Persona (dni, nombre, apellidos, Nivel_id, Horario_Evento_id, isUsuario) " +
                "              VALUES (?, ?, ?, ?, ? , ?)";

        PreparedStatement ps;
        ps = conn.prepareStatement(query);
        ps.setString(1, persona.getDni());
        ps.setString(2, persona.getNombre());
        ps.setString(3, persona.getApellidos());
        ps.setString(4, persona.getNivel_id());
        ps.setString(5, persona.getHorario_id());
        ps.setString(6, persona.getIsUsuario());

        try {
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }
        return true;
    }

}

