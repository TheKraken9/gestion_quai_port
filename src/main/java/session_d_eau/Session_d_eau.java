package session_d_eau;

public class Session_d_eau {
    private int idSession;
    private int idEscale;
    private double prixEuro;
    private double prixAr;

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public int getIdEscale() {
        return idEscale;
    }

    public void setIdEscale(int idEscale) {
        this.idEscale = idEscale;
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

    public Session_d_eau(int idSession, int idEscale, double prixEuro, double prixAr) {
        this.idSession = idSession;
        this.idEscale = idEscale;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
    }

    public Session_d_eau(int idEscale, double prixEuro, double prixAr) {
        this.idEscale = idEscale;
        this.prixEuro = prixEuro;
        this.prixAr = prixAr;
    }

    public Session_d_eau() {}
}
