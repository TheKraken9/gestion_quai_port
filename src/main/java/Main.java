import connectBase.ConnectdB;
import prevision.Prevision;
import proposition.Proposition;
import quai.Quai;
import remorquage.Remorquage;
import sambo.Sambo;
import stationnement.Stationnement;

import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Main {
    public static void main(String [] args){
        Connection connect;
        ConnectdB conn = new ConnectdB();
        connect = conn.connection();
        /*Prevision prevision = new Prevision();
        prevision.setIdSambo(1);
        prevision.setDateArrivee(java.sql.Date.valueOf("2021-10-10"));
        prevision.setHeureArrivee(java.sql.Time.valueOf("10:10"));
        prevision.setDateDepart(java.sql.Date.valueOf("2021-10-10"));
        prevision.setHeureDepart(java.sql.Time.valueOf("10:10"));
        prevision.setDureeRemorquage(10);
        prevision.inserer_prevision(connect, prevision, 1);*/



        /*Sambo sambo = new Sambo();
        Vector<Sambo> sambos = new Vector<>();
        sambos = sambo.liste_sambo(connect);
        for (Sambo sambo1 : sambos) {
            Boolean trouve = false;
            Quai quai = new Quai();
            Vector<Quai> quais = new Vector<>();
            int idSambo = sambo1.getIdSambo();
            int idPrevision = sambo1.getIdPrevision();
            String nom = sambo1.getNom();
            double poids = sambo1.getPoids();
            double longueur = sambo1.getLongueur();
            quais = quai.liste_proposition_quai(connect, poids, longueur);
            for (Quai quai1 : quais) {
                Quai sambo2 = new Quai();
                Vector<Sambo> sambos2 = new Vector<>();
                int idQuai = quai1.getIdQuai();
                int numQuai = quai1.getNumero();
                sambos2 = sambo2.liste_sambo_sur_le_quai(connect, numQuai);
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
                    proposition.inserer_proposition(connect, proposition);
                    break;
                }
            }
            if(!trouve){
                quais = quai.liste_proposition_quai(connect, poids, longueur);
                boolean encoreChange = false;
                int min = 0;
                int numQuai = 0;
                for (Quai quai1 : quais) {
                    int niveauQuai = quai1.niveau(connect, quai1);
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
                        proposition1.inserer_proposition(connect, proposition1);
                        break;
                    } else {
                        quais = quai.liste_proposition_quai(connect, poids, longueur);
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
                        proposition2.inserer_proposition(connect, proposition2);
                    }
                    break;
                }
                trouve = false;
            }
        }*/

        /*
        double nb = 3.8;
        double nb2 = Math.ceil(nb);
        double prix = 0;
        System.out.println(nb2);
        Prevision prevision = new Prevision();
        prevision.setDureeRemorquage(50);
        Sambo sambo = new Sambo();
        sambo.setNomNature("international");
        sambo.setIdTypes(1);
        Remorquage remorquage = new Remorquage();
        prix = remorquage.tarif_remorquage(connect,prevision, sambo);
        System.out.println(prix);
        System.out.println(connect);
        */

        int numQuai = 1;
        Date date_arrive = java.sql.Date.valueOf("2023-05-05");
        Time heure_arrive = Time.valueOf("05:05:00");

        Date date_depart = java.sql.Date.valueOf("2023-06-06");
        Time heure_depart = Time.valueOf("06:06:00");

        Prevision prevision = new Prevision();
        prevision.setDateArrivee((java.sql.Date) date_arrive);
        prevision.setHeureArrivee(heure_arrive);
        prevision.setDateDepart((java.sql.Date) date_depart);
        prevision.setHeureDepart(heure_depart);
        prevision.setIdPrevion(1);


        Sambo sambo = new Sambo();
        Stationnement stationnement = new Stationnement();
        double prix = 0;
        prix = stationnement.tarif_stationnement(connect, prevision, sambo);
        System.out.println(prix);

        /*Date date_arrive1 = java.sql.Date.valueOf("2023-05-05");
        Time heure_arrive1 = Time.valueOf("05:05:00");

        Date date_arrive2 = java.sql.Date.valueOf("2023-05-05");
        Time heure_arrive2 = Time.valueOf("06:10:00");

        Stationnement stationnement = new Stationnement();
        long durree = 0;
        durree = stationnement.duree_stationnement(connect, (java.sql.Date) date_arrive1,heure_arrive1, (java.sql.Date) date_arrive2,heure_arrive2);
        System.out.println(durree);*/
    }

}
