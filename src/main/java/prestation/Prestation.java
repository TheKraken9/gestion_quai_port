package prestation;

import java.sql.Date;

public class Prestation {
    private int idPres;
    private int idEscale;
    private int idNaturePres;
    private Date date;
    private String remarque;

    public int getIdPres() {
        return idPres;
    }

    public void setIdPres(int idPres) {
        this.idPres = idPres;
    }

    public int getIdEscale() {
        return idEscale;
    }

    public void setIdEscale(int idEscale) {
        this.idEscale = idEscale;
    }

    public int getIdNaturePres() {
        return idNaturePres;
    }

    public void setIdNaturePres(int idNaturePres) {
        this.idNaturePres = idNaturePres;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Prestation(int idPres, int idEscale, int idNaturePres, Date date, String remarque) {
        this.idPres = idPres;
        this.idEscale = idEscale;
        this.idNaturePres = idNaturePres;
        this.date = date;
        this.remarque = remarque;
    }

    public Prestation(int idEscale, int idNaturePres, Date date, String remarque) {
        this.idEscale = idEscale;
        this.idNaturePres = idNaturePres;
        this.date = date;
        this.remarque = remarque;
    }

    public Prestation(){}
}
