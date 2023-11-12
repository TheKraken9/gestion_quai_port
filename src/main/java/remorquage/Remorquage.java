package remorquage;

import prevision.Prevision;
import sambo.Sambo;

import java.sql.Connection;
import java.util.Vector;

public class Remorquage {
    private int idRemorquage;
    private int idNatureSambo;
    private int idTypeSambo;
    private double prixEuro;
    private double prixAr;
    private int niveau;

    public int getIdRemorquage() {
        return idRemorquage;
    }

    public void setIdRemorquage(int idRemorquage) {
        this.idRemorquage = idRemorquage;
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

    public Remorquage(int idRemorquage, int idNatureSambo, int idTypeSambo, double prixEuro, double prixAr, int niveau) {
        this.idRemorquage = idRemorquage;
        this.idNatureSambo = idNatureSambo;
        this.idTypeSambo = idTypeSambo;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
        this.niveau = niveau;
    }

    public Remorquage() {
    }

    public Vector<Remorquage> tous_les_tarifs_Remorquages(Connection conn, int idTypeSambo) {
        Vector<Remorquage> remorquages = new Vector<Remorquage>();
        String query = "SELECT * FROM remorquage WHERE idTypeSambo = " + idTypeSambo + " ORDER BY niveau ASC;";
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Remorquage remorquage = new Remorquage();
                remorquage.setIdRemorquage(rs.getInt("idRemorquage"));
                remorquage.setIdTypeSambo(rs.getInt("idTypeSambo"));
                remorquage.setPrixEuro(rs.getDouble("prixEuro"));
                remorquage.setPrixAr(rs.getDouble("prixAr"));
                remorquage.setNiveau(rs.getInt("niveau"));
                remorquages.add(remorquage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return remorquages;
    }

    public double tarif_remorquage(Connection conn, Prevision prevision, Sambo sambo) {
        double tarif = 0;
        Remorquage remorquage = new Remorquage();
        System.out.println(sambo.getIdTypes());
        Vector<Remorquage> remorquages = remorquage.tous_les_tarifs_Remorquages(conn, sambo.getIdTypes());
        double duree_remorquage = prevision.getDureeRemorquage();
        double quota = duree_remorquage/10;
        double quota_arrondi = Math.ceil(quota);
            if(sambo.getNomNature().equals("Internationale")){
                for (int i = 0; i < quota_arrondi; i++) {
                    System.out.println(remorquages.get(i).getPrixEuro());
                    tarif += remorquages.get(i).getPrixEuro();
                }
            }else{
                for (int i = 0; i < quota_arrondi; i++) {
                    tarif += remorquages.get(i).getPrixAr();
                }
            }
        return tarif;
    }
}
