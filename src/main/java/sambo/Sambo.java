package sambo;

import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class Sambo {
    private int idPrevision;
    private int idSambo;
    private String nom;
    private int idTypes;
    private String nomType;
    private String nomNature;
    private Date dateDepart;
    private Date dateArrivee;
    private int idNature;
    private double poids;
    private double longueur;
    private Time heureDepart;
    private Time heureArrivee;
    private double dureeRemorquage;

    public int getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(int idPrevision) {
        this.idPrevision = idPrevision;
    }

    public int getIdSambo() {
        return idSambo;
    }

    public void setIdSambo(int idSambo) {
        this.idSambo = idSambo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdTypes() {
        return idTypes;
    }

    public void setIdTypes(int idTypes) {
        this.idTypes = idTypes;
    }

    public int getIdNature() {
        return idNature;
    }

    public void setIdNature(int idNature) {
        this.idNature = idNature;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getNomNature() {
        return nomNature;
    }

    public void setNomNature(String nomNature) {
        this.nomNature = nomNature;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getDureeRemorquage() {
        return dureeRemorquage;
    }

    public void setDureeRemorquage(double dureeRemorquage) {
        this.dureeRemorquage = dureeRemorquage;
    }

    public Sambo(int idSambo, String nom, int idTypes, int idNature, double poids, double longueur) {
        this.idSambo = idSambo;
        this.nom = nom;
        this.idTypes = idTypes;
        this.idNature = idNature;
        this.poids = poids;
        this.longueur = longueur;
    }

    public Sambo(int idSambo, String nom, double poids, double longueur) {
        this.idSambo = idSambo;
        this.nom = nom;
        this.poids = poids;
        this.longueur = longueur;
    }

    public Sambo(String nom, int idTypes, int idNature, double poids) {
        this.nom = nom;
        this.idTypes = idTypes;
        this.idNature = idNature;
        this.poids = poids;
    }

    public Sambo(int idprevision, int idSambo, String nom, String nomType, String nomNature, Date dateDepart, Date dateArrivee, double poids, Time heureDepart, Time heureArrivee, double longueur, double dureeRemorquage) {
        this.idPrevision = idprevision;
        this.idSambo = idSambo;
        this.nom = nom;
        this.nomType = nomType;
        this.nomNature = nomNature;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.poids = poids;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.longueur = longueur;
        this.dureeRemorquage = dureeRemorquage;
    }

    public Sambo() {
    }

    public Vector<Sambo> liste_sambo(Connection connection) {
        Vector<Sambo> sambo = new Vector<Sambo>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT P.idprevision as idprevision, sambo.idSambo as idSambo, nomSambo, T.nomTypes as nomTypes, NS.nature as nature, P.date_depart as date_depart, P.date_tonga as date_tonga, sambo.poids as poids, P.heure_tonga as heure_tonga, P.heure_depart as heure_depart, sambo.longueur as longueur, P.duree_remorquage as duree_remorquage FROM Sambo join Types T on T.idType = Sambo.idType join Nature_Sambo NS on NS.idNature = Sambo.idNature join Prevision P on Sambo.idSambo = P.idSambo order by P.date_tonga, P.heure_tonga");
            while (rs.next()) {
                Sambo s = new Sambo(rs.getInt("idprevision"), rs.getInt("idSambo"), rs.getString("nomSambo"), rs.getString("nomTypes"), rs.getString("nature"), rs.getDate("date_depart"), rs.getDate("date_tonga"), rs.getDouble("poids"), rs.getTime("heure_depart"), rs.getTime("heure_tonga"), rs.getDouble("longueur"), rs.getDouble("duree_remorquage"));
                sambo.add(s);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return sambo;
    }

    public int inserer_sambo(Connection connection, Sambo s) {
        try {
            int idSambo = 0;
            String sql = "INSERT INTO Sambo(nomSambo, idType, idNature, poids, longueur) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, s.getNom());
            preparedStatement.setInt(2, s.getIdTypes());
            preparedStatement.setInt(3, s.getIdNature());
            preparedStatement.setDouble(4, s.getPoids());
            preparedStatement.setDouble(5, s.getLongueur());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("Creating user failed, no rows affected.");
            }else{
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    idSambo = rs.getInt(1);
                }
            }
            System.out.println(idSambo);
            return idSambo;
        } catch (Exception e) {
            e.getMessage();
        }
        return idSambo;
    }

    public Sambo ma_sambo(Connection connection, int idPrevision) {
        Sambo s = new Sambo();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT P.idprevision as idprevision, sambo.idSambo as idSambo, nomSambo, T.nomTypes as nomTypes, NS.nature as nature, P.date_depart as date_depart, P.date_tonga as date_tonga, sambo.poids as poids, P.heure_tonga as heure_tonga, P.heure_depart as heure_depart, sambo.longueur as longueur, P.duree_remorquage as duree_remorquage FROM Sambo join Types T on T.idType = Sambo.idType join Nature_Sambo NS on NS.idNature = Sambo.idNature join Prevision P on Sambo.idSambo = P.idSambo where P.idPrevision = "+idPrevision+" order by P.date_tonga");
            while (rs.next()) {
                s = new Sambo(rs.getInt("idprevision"), rs.getInt("idSambo"), rs.getString("nomSambo"), rs.getString("nomTypes"), rs.getString("nature"), rs.getDate("date_depart"), rs.getDate("date_tonga"), rs.getDouble("poids"), rs.getTime("heure_depart"), rs.getTime("heure_tonga"), rs.getDouble("longueur"), rs.getDouble("duree_remorquage"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return s;
    }
}
