package proposition;
import prevision.Prevision;
import quai.Quai;
import sambo.Sambo;

import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;

public class Proposition extends Prevision {
    private int idSambo;
    private  int idPrevision;
    private int numQuai;
    private double prixPrevision;
    private String nomSambo;


    @Override
    public int getIdSambo() {
        return idSambo;
    }

    @Override
    public void setIdSambo(int idSambo) {
        this.idSambo = idSambo;
    }

    public int getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(int idPrevision) {
        this.idPrevision = idPrevision;
    }

    public int getNumQuai() {
        return numQuai;
    }

    public void setNumQuai(int numQuai) {
        this.numQuai = numQuai;
    }

    public double getPrixPrevision() {
        return prixPrevision;
    }

    public void setPrixPrevision(double prixPrevision) {
        this.prixPrevision = prixPrevision;
    }

    public Proposition(int idPrevion, int idSambo, Date dateArrivee, double dureeStation, int idSambo1, int idPrevision, int numQuai, double prixPrevision) {
        super(idPrevion, idSambo, dateArrivee, dureeStation);
        this.idSambo = idSambo1;
        this.idPrevision = idPrevision;
        this.numQuai = numQuai;
        this.prixPrevision = prixPrevision;
    }
    public Proposition(){}

    public void inserer_proposition(Connection connection, Proposition proposition){
        try{
            String query = "INSERT INTO proposition (idSambo, idPrevision, numQuai, coutPrevision) VALUES (?,?,?,?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, proposition.getIdSambo());
            preparedStatement.setInt(2, proposition.getIdPrevision());
            preparedStatement.setInt(3, proposition.getNumQuai());
            preparedStatement.setDouble(4, proposition.getPrixPrevision());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public double total_cout(Connection connection, int idSambo, int idQuai){
        double total = 0;
        return total;
    }

    public Proposition ma_proposition(Connection connection, int idPrevision) {
        Proposition proposition = new Proposition();
        try {
            String sql = "select * from proposition where idPrevision = " + idPrevision;
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numquai = resultSet.getInt("numquai");
                int idprevision = resultSet.getInt("idprevision");
                int idsambo = resultSet.getInt("idsambo");
                proposition.setNumQuai(numquai);
                proposition.setIdPrevision(idprevision);
                proposition.setIdSambo(idsambo);
            }
            return proposition;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proposition;
    }

    public Vector<Proposition> liste_proposition(Connection connection){
        Vector<Proposition> propositions = new Vector<>();
        try{
            String sql = "select * from proposition";
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int idprevision = resultSet.getInt("idprevision");
                int numquai = resultSet.getInt("numquai");
                double coutprevision = resultSet.getDouble("coutprevision");
                Proposition proposition = new Proposition();
                proposition.setIdPrevision(idprevision);
                proposition.setNumQuai(numquai);
                proposition.setPrixPrevision(coutprevision);
                propositions.add(proposition);
            }
            return propositions;
        }catch (Exception e){
            e.printStackTrace();
        }
        return propositions;
    }

    public void generer_proposition(Connection connection) {
        Sambo sambo = new Sambo();
        Vector<Sambo> sambos = new Vector<>();
        sambos = sambo.liste_sambo(connection);
        for (Sambo sambo1 : sambos) {
            Boolean trouve = false;
            Quai quai = new Quai();
            Vector<Quai> quais = new Vector<>();
            int idSambo = sambo1.getIdSambo();
            int idPrevision = sambo1.getIdPrevision();
            String nom = sambo1.getNom();
            double poids = sambo1.getPoids();
            double longueur = sambo1.getLongueur();
            quais = quai.liste_proposition_quai(connection, poids, longueur);
            for (Quai quai1 : quais) {
                Quai sambo2 = new Quai();
                Vector<Sambo> sambos2 = new Vector<>();
                int idQuai = quai1.getIdQuai();
                int numQuai = quai1.getNumero();
                sambos2 = sambo2.liste_sambo_sur_le_quai(connection, numQuai);
                if(sambos2.size() != 0){
                    continue;
                }else{
                    trouve = true;
                    Proposition proposition = new Proposition();
                    //double cout = proposition.total_cout(connect, idSambo, idQuai);
                    proposition.setIdSambo(idSambo);
                    proposition.setNumQuai(quai1.getNumero());
                    proposition.setIdPrevision(idPrevision);
                    proposition.setPrixPrevision(100);
                    proposition.inserer_proposition(connection, proposition);
                    break;
                }
            }
            if(!trouve){
                quais = quai.liste_proposition_quai(connection, poids, longueur);
                boolean encoreChange = false;
                int min = 0;
                int numQuai = 0;
                for (Quai quai1 : quais) {
                    int niveauQuai = quai1.niveau(connection, quai1);
                    if (min > niveauQuai) {
                        int niveau_min = 0;
                        min = niveauQuai;
                        numQuai = quai1.getNumero();
                        encoreChange = true;
                    }
                    if (encoreChange) {
                        Proposition proposition1 = new Proposition();
                        proposition1.setIdSambo(idSambo);
                        proposition1.setNumQuai(numQuai);
                        proposition1.setIdPrevision(idPrevision);
                        proposition1.setPrixPrevision(100);
                        proposition1.inserer_proposition(connection, proposition1);
                        break;
                    } else {
                        quais = quai.liste_proposition_quai(connection, poids, longueur);
                        int num = 0;
                        for (Quai quai2 : quais) {
                            num = quai2.getNumero();
                            break;
                        }
                        Proposition proposition2 = new Proposition();
                        proposition2.setIdSambo(idSambo);
                        proposition2.setNumQuai(num);
                        proposition2.setIdPrevision(idPrevision);
                        proposition2.setPrixPrevision(100);
                        proposition2.inserer_proposition(connection, proposition2);
                    }
                    break;
                }
                trouve = false;
            }
        }
    }
}
