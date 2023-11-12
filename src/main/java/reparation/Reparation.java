package reparation;

public class Reparation {
    private int idReparation;
    private int idEscale;
    private String typeReparation;
    private double prixEuro;
    private double prixAr;

    public int getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(int idReparation) {
        this.idReparation = idReparation;
    }

    public int getIdEscale() {
        return idEscale;
    }

    public void setIdEscale(int idEscale) {
        this.idEscale = idEscale;
    }

    public String getTypeReparation() {
        return typeReparation;
    }

    public void setTypeReparation(String typeReparation) {
        this.typeReparation = typeReparation;
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

    public Reparation(int idReparation, int idEscale, String typeReparation, double prixEuro, double prixAr) {
        this.idReparation = idReparation;
        this.idEscale = idEscale;
        this.typeReparation = typeReparation;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
    }

    public Reparation(int idEscale, String typeReparation, double prixEuro, double prixAr) {
        this.idEscale = idEscale;
        this.typeReparation = typeReparation;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
    }

    public Reparation() {}
}
