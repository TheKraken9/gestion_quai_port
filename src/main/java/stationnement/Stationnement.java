package stationnement;

import prevision.Prevision;
import proposition.Proposition;
import sambo.Sambo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Calendar;
import java.util.Vector;

public class Stationnement {
    private int id;
    private int idQuai;
    private int idNatureSambo;
    private int idTypeSambo;
    private double prixEuro;
    private double prixAr;
    private int niveau;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(int idQuai) {
        this.idQuai = idQuai;
    }

    public int getIdNatureSambo() {
        return idNatureSambo;
    }

    public void setIdNatureSambo(int idNatureSambo) {
        this.idNatureSambo = idNatureSambo;
    }

    public int getIdTypeSambo() {
        return idTypeSambo;
    }

    public void setIdTypeSambo(int idTypeSambo) {
        this.idTypeSambo = idTypeSambo;
    }

    public double getPrixEuro() {
        return prixEuro;
    }

    public void setPrixEuro(double prixEuro) {
        this.prixEuro = prixEuro;
    }

    public double getPrixAr() {
        return prixAr;
    }

    public void setPrixAr(double prixAr) {
        this.prixAr = prixAr;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stationnement(int id, int idQuai, int idNatureSambo, int idTypeSambo, double prixEuro, double prixAr, int niveau, String type) {
        this.id = id;
        this.idQuai = idQuai;
        this.idNatureSambo = idNatureSambo;
        this.idTypeSambo = idTypeSambo;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
        this.niveau = niveau;
        this.type = type;
    }

    public Stationnement(int idQuai, int idNatureSambo, int idTypeSambo, double prixEuro, double prixAr, int niveau, String type) {
        this.idQuai = idQuai;
        this.idNatureSambo = idNatureSambo;
        this.idTypeSambo = idTypeSambo;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
        this.niveau = niveau;
        this.type = type;
    }

    public Stationnement() {
    }

    public Vector<Stationnement> tous_les_taris_Stationnements(Connection connection, int numQuai, int idSambo) {
        Vector<Stationnement> stationnements = new Vector<Stationnement>();
        try {
            String sql = "select * from stationnement join Quai Q on Q.idQuai = Stationnement.idQuai join Proposition P on Q.idQuai = P.numQuai where Q.numquai = " + numQuai + " and P.idSambo = " + idSambo;
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Stationnement stationnement = new Stationnement();
                stationnement.setId(resultSet.getInt("idStation"));
                stationnement.setIdQuai(resultSet.getInt("idQuai"));
                stationnement.setIdTypeSambo(resultSet.getInt("idTypeSambo"));
                stationnement.setPrixEuro(resultSet.getDouble("prixEuro"));
                stationnement.setPrixAr(resultSet.getDouble("prixAr"));
                stationnement.setNiveau(resultSet.getInt("niveau"));
                stationnement.setType(resultSet.getString("type"));
                stationnements.add(stationnement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationnements;
    }

    public double tarif_stationnement(Connection connection, Prevision prevision, Sambo sambo) {
        double tarif = 0;
        try {
            Vector<Stationnement> stationnements = new Vector<Stationnement>();
            Stationnement stationnement = new Stationnement();
            Date date_arrivee = prevision.getDateArrivee();
            Time heure_arrivee = prevision.getHeureArrivee();
            Date date_depart = prevision.getDateDepart();
            Time heure_depart = prevision.getHeureDepart();
            long durree_stationnement = this.duree_stationnement(connection, date_arrivee, heure_arrivee, date_depart, heure_depart);
            Proposition proposition = new Proposition();
            proposition = proposition.ma_proposition(connection, prevision.getIdPrevion());
            Stationnement stationnement1 = new Stationnement();
            stationnement1 = stationnement1.liste_stationnement_lineaire(connection, proposition.getIdType());
            System.out.println(proposition.getNumQuai() + " Numquai" + proposition.getIdSambo());
            stationnements = stationnement.tous_les_taris_Stationnements(connection, proposition.getNumQuai(), proposition.getIdSambo());
            double quart = (double) durree_stationnement /10;
            if(quart <= 12){
                for (int i = 0; i < quart; i++) {
                    if(sambo.getIdTypes() == 1){
                        System.out.println(stationnements.get(i).getPrixAr());
                        tarif += stationnements.get(i).getPrixAr();
                    }else {
                        if(heure_arrivee.after(Time.valueOf("20:00:00")) && heure_arrivee.before(Time.valueOf("06:00:00"))) {
                            System.out.println(stationnements.get(i).getPrixEuro() * 0.5);
                            tarif += stationnements.get(i).getPrixEuro() * 0.5;
                            heure_arrivee = Time.valueOf(heure_arrivee.toLocalTime().plusMinutes(15));
                        }else{
                            heure_arrivee = Time.valueOf(heure_arrivee.toLocalTime().plusMinutes(15));
                            System.out.println(stationnements.get(i).getPrixEuro());
                            tarif += stationnements.get(i).getPrixEuro();
                        }
                    }
                }
            }else{
                for (int i = 0; i < quart; i++) {
                    if(i<12){
                        for (int j = 0; j < stationnements.size(); j++) {
                            if(sambo.getIdTypes() == 1){
                                System.out.println(stationnements.get(i).getPrixAr());
                                tarif += stationnements.get(i).getPrixAr();
                            }else{
                                if(heure_arrivee.after(Time.valueOf("20:00:00")) && heure_arrivee.before(Time.valueOf("06:00:00"))) {
                                    System.out.println(stationnements.get(i).getPrixEuro());
                                    tarif += stationnements.get(i).getPrixEuro();
                                    heure_arrivee = Time.valueOf(heure_arrivee.toLocalTime().plusMinutes(15));
                                }else{
                                    System.out.println(stationnements.get(i).getPrixEuro());
                                    tarif += stationnements.get(i).getPrixEuro();
                                    heure_arrivee = Time.valueOf(heure_arrivee.toLocalTime().plusMinutes(15));
                                }
                            }
                        }
                    }else{
                            if(sambo.getIdTypes() == 1){
                                System.out.println(stationnements.get(i).getPrixAr());
                                tarif += stationnement1.getPrixAr();
                            }else{
                                if(heure_arrivee.after(Time.valueOf("20:00:00")) && heure_arrivee.before(Time.valueOf("06:00:00"))) {
                                    System.out.println(stationnements.get(i).getPrixEuro());
                                    tarif += stationnements.get(i).getPrixEuro();
                                    heure_arrivee = Time.valueOf(heure_arrivee.toLocalTime().plusMinutes(15));
                                }else{
                                    System.out.println(stationnements.get(i).getPrixEuro());
                                    tarif += stationnements.get(i).getPrixEuro();
                                    heure_arrivee = Time.valueOf(heure_arrivee.toLocalTime().plusMinutes(15));
                                }
                            }
                    }
                }
            }
            return tarif;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tarif;
    }

    public long duree_stationnement(Connection connection, Date date_arrivee, Time heure_arrivee, Date date_depart, Time heure_depart) {
        long durree = 0;
        Timestamp arrivee = this.combineDateTime(date_arrivee, heure_arrivee);
        Timestamp depart = this.combineDateTime(date_depart, heure_depart);
        Duration duration = Duration.between(arrivee.toInstant(), depart.toInstant());
        long diff_en_minutes = duration.toMinutes();
        //System.out.println(diff_en_minutes+" durree");
        return diff_en_minutes;
    }

    public Timestamp combineDateTime(Date date, Time temps) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(temps);

        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, timeCalendar.get(Calendar.MILLISECOND));

        return new Timestamp(calendar.getTimeInMillis());
    }

    public Stationnement liste_stationnement_lineaire(Connection connection, int idTypeSambo) {
        Stationnement stationnement = new Stationnement();
        try {
            String sql = "select * from stationnement where type = 'lineaire'";
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                stationnement.setId(resultSet.getInt("idStation"));
                stationnement.setIdQuai(resultSet.getInt("idQuai"));
                stationnement.setIdTypeSambo(resultSet.getInt("idTypeSambo"));
                stationnement.setPrixEuro(resultSet.getDouble("prixEuro"));
                stationnement.setPrixAr(resultSet.getDouble("prixAr"));
                stationnement.setNiveau(resultSet.getInt("niveau"));
                stationnement.setType(resultSet.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return stationnement;
        }
    }

