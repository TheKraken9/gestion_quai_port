package escale;
import prevision.Prevision;

import java.sql.Date;

public class Escale extends Prevision {
    private int idEscale;
    private int idSambo;
    private Date dateArrivee;
    private Date dateDepart;

    public int getIdEscale() {
        return idEscale;
    }

    public void setIdEscale(int idEscale) {
        this.idEscale = idEscale;
    }

    @Override
    public int getIdSambo() {
        return idSambo;
    }

    @Override
    public void setIdSambo(int idSambo) {
        this.idSambo = idSambo;
    }

    @Override
    public Date getDateArrivee() {
        return dateArrivee;
    }

    @Override
    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Escale(int idPrevion, int idSambo, Date dateArrivee, double dureeStation, int idEscale, int idSambo1, Date dateArrivee1, Date dateDepart) {
        super(idPrevion, idSambo, dateArrivee, dureeStation);
        this.idEscale = idEscale;
        this.idSambo = idSambo1;
        this.dateArrivee = dateArrivee1;
        this.dateDepart = dateDepart;
    }

    public Escale(){}
}
