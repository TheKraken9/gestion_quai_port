package prevision;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

public class Prevision {
    private int idPrevion;
    private int idSambo;
    private Date dateArrivee;
    private Time heureArrivee;
    private double dureeStation;
    private double dureeRemorquage;

    private Date dateDepart;
    private Time heureDepart;
    private int numQuai;
    private String nomSambo;
    private String nomType;
    private String nomNature;
    private double poids;
    private double longueur;

    private int IdType;
    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        /*String heureArriveeString = heureArrivee.toString();
        String heure = heureArriveeString.concat(":00");
        System.out.println(heure);*/
        this.heureArrivee = Time.valueOf(heureArrivee.toLocalTime());
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        /*String heureDepartString = heureDepart.toString();
        String heure = heureDepartString.concat(":00");
        System.out.println(heure);*/
        this.heureDepart = Time.valueOf(heureDepart.toLocalTime());

    }

    public double getDureeRemorquage() {
        return dureeRemorquage;
    }

    public void setDureeRemorquage(double dureeRemorquage) {
        this.dureeRemorquage = dureeRemorquage;
    }

    public int getIdPrevion() {
        return idPrevion;
    }

    public void setIdPrevion(int idPrevion) {
        this.idPrevion = idPrevion;
    }

    public int getIdSambo() {
        return idSambo;
    }

    public void setIdSambo(int idSambo) {
        this.idSambo = idSambo;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public double getDureeStation() {
        return dureeStation;
    }

    public void setDureeStation(double dureeStation) {
        this.dureeStation = dureeStation;
    }

    public int getNumQuai() {
        return numQuai;
    }

    public void setNumQuai(int numQuai) {
        this.numQuai = numQuai;
    }

    public String getNomSambo() {
        return nomSambo;
    }

    public void setNomSambo(String nomSambo) {
        this.nomSambo = nomSambo;
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

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }

    public Prevision(int idPrevion, int idSambo, Date dateArrivee, double dureeStation) {
        this.idPrevion = idPrevion;
        this.idSambo = idSambo;
        this.dateArrivee = dateArrivee;
        this.dureeStation = dureeStation;
    }

    public Prevision(int idSambo, Date dateArrivee, double dureeStation, double dureeRemorquage) {
        this.idSambo = idSambo;
        this.dateArrivee = dateArrivee;
        this.dureeStation = dureeStation;
        this.dureeRemorquage = dureeRemorquage;
    }

    public Prevision(int idPrevion, Date dateArrivee, Time heureArrivee, Date dateDepart, Time heureDepart) {
        this.idPrevion = idPrevion;
        this.dateArrivee = dateArrivee;
        this.heureArrivee = heureArrivee;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
    }

    public Prevision(int idSambo, Date dateArrivee, Time heureArrivee, double dureeStation, double dureeRemorquage, Date dateDepart, Time heureDepart) {
        this.idSambo = idSambo;
        this.dateArrivee = dateArrivee;
        this.heureArrivee = heureArrivee;
        this.dureeStation = dureeStation;
        this.dureeRemorquage = dureeRemorquage;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
    }

    public Prevision(int numQuai,int idPrevion, Date dateArrivee, Time heureArrivee, Date dateDepart, Time heureDepart, String nomSambo, double poids, double longueur, String nomType, String nomNature, int idType){
        this.numQuai = numQuai;
        this.idPrevion = idPrevion;
        this.dateArrivee = dateArrivee;
        this.heureArrivee = heureArrivee;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.nomSambo = nomSambo;
        this.poids = poids;
        this.longueur = longueur;
        this.nomType = nomType;
        this.nomNature = nomNature;
        this.IdType = idType;
    }

    public Prevision() {
    }

    public void inserer_prevision(Connection connection, Prevision prevision, int idSambo){
        try{
            String query = "INSERT INTO prevision (idSambo, date_tonga, heure_tonga, duree_Remorquage, date_Depart, heure_Depart) VALUES ("+ idSambo +",?,?,?,?,?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, prevision.getDateArrivee());
            preparedStatement.setTime(2, prevision.getHeureArrivee());
            preparedStatement.setDouble(3, prevision.getDureeRemorquage());
            preparedStatement.setDate(4, prevision.getDateDepart());
            preparedStatement.setTime(5, prevision.getHeureDepart());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modifier_prevision(Connection connection, Prevision prevision){
        try{
            String query = "UPDATE prevision SET date_tonga = ?, heure_tonga = ?, date_Depart = ?, heure_Depart = ? WHERE idPrevision = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, prevision.getDateArrivee());
            preparedStatement.setTime(2, prevision.getHeureArrivee());
            preparedStatement.setDate(3, prevision.getDateDepart());
            preparedStatement.setTime(4, prevision.getHeureDepart());
            preparedStatement.setInt(5, prevision.getIdPrevion());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Vector<Prevision> les_previsions(Connection connection){
        Vector<Prevision> previsions = new Vector<>();
        try{
            String sql = "select * from all_prevision";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Prevision prevision = new Prevision();
                prevision.setNumQuai(resultSet.getInt("numQuai"));
                prevision.setIdPrevion(resultSet.getInt("idPrevision"));
                prevision.setDateArrivee(resultSet.getDate("date_tonga"));
                prevision.setHeureArrivee(resultSet.getTime("heure_tonga"));
                prevision.setDateDepart(resultSet.getDate("date_Depart"));
                prevision.setHeureDepart(resultSet.getTime("heure_Depart"));
                prevision.setNomSambo(resultSet.getString("nomSambo"));
                prevision.setPoids(resultSet.getDouble("poids"));
                prevision.setLongueur(resultSet.getDouble("longueur"));
                prevision.setNomType(resultSet.getString("nomType"));
                prevision.setNomNature(resultSet.getString("nature"));
                prevision.setIdType(resultSet.getInt("idType"));
                prevision.setDureeRemorquage(resultSet.getDouble("duree_Remorquage"));
                previsions.add(prevision);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return previsions;
    }
}
