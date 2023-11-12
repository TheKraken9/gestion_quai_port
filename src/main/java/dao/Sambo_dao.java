package dao;
import connectBase.ConnectdB;
import prevision.Prevision;
import sambo.Sambo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sambo_dao {
    public static int insertSambo(String nom, int idTypes, int idNature, double poids, Connection c) {
        String sql = "INSERT INTO Sambo(nomSambo, idType, idNature, poids) " + " VALUES ('" + nom + "', '" + idTypes + "', '" + idNature + "', '" + poids + "')";
        PreparedStatement ps = null;
        int id = 0;
        try {
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int x = ps.executeUpdate();
            if (x > 0) {
                ResultSet generateKey = ps.getGeneratedKeys();
                if (generateKey.next()) {
                    id = generateKey.getInt(1);
                }
            }
            c.close();
            return id;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return id;
        }
    }

    public static boolean insertPrevision(int idSambo, Date dateArrivee, Time heureArrivee , double dureeRemorquage, Date dateDepart, Time heureDepart) {
        ConnectdB con = new ConnectdB();
        Connection co = con.connection();
        String sql = "INSERT INTO Prevision(idSambo, date_tonga, heure_tonga ,duree_remorquage, dateDepart, heureDepart) " + " VALUES ('" + idSambo + "', '" + dateArrivee + "', '" + heureArrivee +"', '" + dureeRemorquage + "', '" + dateDepart + "', '" + heureDepart + "')";
        PreparedStatement ps = null;
        System.out.println(sql);
        try {
            ps = co.prepareStatement(sql);
            int x = ps.executeUpdate();
            co.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Sambo> listSambo() {
        List<Sambo> s = new ArrayList<>();
        ConnectdB con = new ConnectdB();
        try (Connection c = con.connection();
             PreparedStatement ps = c.prepareStatement("SELECT* FROM Sambo")) {
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nomSambo");
                int types = Integer.parseInt(rs.getString("idType"));
                int nature = Integer.parseInt(rs.getString("idNature"));
                double poids = rs.getDouble("poids");
                s.add(new Sambo(nom, types, nature, poids));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
