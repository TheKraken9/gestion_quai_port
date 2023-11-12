package quai;

import sambo.Sambo;

import java.sql.Connection;
import java.util.Vector;

public class Quai {
    private int idQuai;
    private int numero;
    private double profondeur;
    private double longueur;

    public int getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(int idQuai) {
        this.idQuai = idQuai;
    }

    public double getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Quai(int idQuai, int numero, double profondeur, double longueur) {
        this.idQuai = idQuai;
        this.numero = numero;
        this.profondeur = profondeur;
        this.longueur = longueur;
    }

    public Quai(int idQuai, double profondeur, double longueur) {
        this.idQuai = idQuai;
        this.profondeur = profondeur;
        this.longueur = longueur;
    }

    public Quai(double profondeur, double longueur) {
        this.profondeur = profondeur;
        this.longueur = longueur;
    }

    public Quai() {
    }

    public Vector<Quai> liste_quai(Connection connection) {
        Vector<Quai> listeQuai = new Vector<Quai>();
        try {
            String query = "SELECT * FROM quai";
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int idQuai = resultSet.getInt("idQuai");
                int numero = resultSet.getInt("numquai");
                double profondeur = resultSet.getDouble("profondeur");
                double longueur = resultSet.getDouble("longueur");
                Quai quai = new Quai(idQuai, numero, profondeur, longueur);
                listeQuai.add(quai);
            }
            return listeQuai;
        } catch (Exception e) {
            e.printStackTrace();
            return listeQuai;
        }
    }

    public Vector<Quai> liste_proposition_quai(Connection connection,double profondeur, double longueur){
        Vector<Quai> listeQuai = new Vector<Quai>();
        try {
            String query = "SELECT * FROM quai WHERE profondeur >= "+profondeur+" AND longueur >= "+longueur+" order by longueur";
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int idQuai = resultSet.getInt("idQuai");
                int numero = resultSet.getInt("numQuai");
                double profondeurQ = resultSet.getDouble("profondeur");
                double longueurQ = resultSet.getDouble("longueur");
                Quai quai = new Quai(idQuai, numero, profondeurQ, longueurQ);
                listeQuai.add(quai);
            }
            return listeQuai;
        } catch (Exception e) {
            e.printStackTrace();
            return listeQuai;
        }
    }

    public Vector<Sambo> liste_sambo_sur_le_quai(Connection connection, int numQuai){
        Vector<Sambo> listeSambo = new Vector<Sambo>();
        try {
            String sql = "select S.idsambo as idsambo, nomsambo, poids, longueur from Proposition join Sambo S on S.idSambo = Proposition.idSambo join Prevision P on P.idPrevision = Proposition.idPrevision where P.numQuai = "+numQuai;
            String query = "select S.idSambo as idsambo, S.nomSambo as nomsambo, S.poids as poids, S.longueur as longueur, Proposition.numQuai as numquai from Proposition join Sambo S on S.idSambo = Proposition.idSambo join Prevision P on P.idPrevision = Proposition.idPrevision WHERE numQuai = "+numQuai;
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int idSamboQ = resultSet.getInt("idSambo");
                String nomQ = resultSet.getString("nomsambo");
                double poidsQ = resultSet.getDouble("poids");
                double longueurQ = resultSet.getDouble("longueur");
                Sambo sambo = new Sambo(idSamboQ, nomQ, poidsQ, longueurQ);
                listeSambo.add(sambo);
            }
            return listeSambo;
        } catch (Exception e) {
            e.printStackTrace();
            return listeSambo;
        }
    }

    public int niveau(Connection connection, Quai quai){
        int niveau_min = 0;
        try {
            String sql = "select count(numquai) as len from proposition where numquai="+quai.getNumero();
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                niveau_min = resultSet.getInt("len");
            }
            return niveau_min;
        }catch (Exception e){
            e.printStackTrace();
        }
        return niveau_min;
    }
}
