package com.SecurVision.dataAccess;
import com.SecurVision.ObjectModel.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        PreparedStatement ps = conn.prepareStatement(query);

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

        PreparedStatement ps = conn.prepareStatement(query);
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

        PreparedStatement ps = conn.prepareStatement(query);
        loadStatementWithPersona(persona,ps);
        try {
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }
        return true;
    }

    public List<Persona> getPersonas() throws SQLException {

        String query = "SELECT * FROM Persona";

        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return readPersonas(rs);
    }

    private List<Persona> readPersonas(ResultSet rs ) throws SQLException {

        ArrayList<Persona> perlist = new ArrayList<>();
        while (rs.next()) {
                Persona p = new Persona();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setApellidos(rs.getString("apellidos"));
                perlist.add(p);
        }
        return perlist;
    }

    public boolean deletePersona(String dni) throws SQLException {

        String query = "DELETE FROM Persona" +
                "               WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,dni);
        int rs = ps.executeUpdate();

        return rs > 0;

    }

    public Boolean updatePersona(Persona persona) throws SQLException {
        String query = "UPDATE Persona" +
                "       SET dni = ?, nombre = ?, apellidos = ?" +
                "           ,Nivel_id = ?, Horario_Evento_id = ?, isUsuario = ? " +
                "           WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        loadStatementWithPersona(persona,ps);
        ps.setString(7, persona.getDni());

        return ps.executeUpdate()>0;
    }

    private void loadStatementWithPersona(Persona persona, PreparedStatement ps) throws SQLException {
        ps.setString(1, persona.getDni().toUpperCase());
        ps.setString(2, persona.getNombre());
        ps.setString(3, persona.getApellidos());
        ps.setString(4, persona.getNivel_id());
        ps.setString(5, persona.getHorario_id());
        ps.setString(6, persona.getIsUsuario());
    }

    public String getNivelId(String dni) throws SQLException {
        String query = "SELECT Nivel_id FROM Persona WHERE dni = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,dni);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getString(1);
        return null;
    }
}

