package controller;

import connectBase.ConnectdB;
import dao.Sambo_dao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import prevision.Prevision;
import quai.Quai;
import sambo.Sambo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.io.FileDescriptor.out;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("lister")){
            try{
                ConnectdB con= new ConnectdB();
                Connection c = con.connection();
                Sambo s= new Sambo();
                List<Sambo> samboList= s.liste_sambo(c);
                request.setAttribute("sambo", samboList);
                request.getRequestDispatcher("listeSambo.jsp").forward(request, response);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("inserer")){
            try {
                ConnectdB con = new ConnectdB();
                Connection c = con.connection();
                Sambo s = new Sambo();
                String nom = request.getParameter("nom");
                int idTypes = Integer.parseInt(request.getParameter("types"));
                int idNature = Integer.parseInt(request.getParameter("nature"));
                double poids = Double.parseDouble(request.getParameter("poids"));
                double longueur = Double.parseDouble(request.getParameter("longueur"));
                s.setNom(nom);
                s.setIdTypes(idTypes);
                s.setIdNature(idNature);
                s.setPoids(poids);
                s.setLongueur(longueur);
                int approved = s.inserer_sambo(c, s);
                System.out.println(approved);
                if (approved != 0) {
                    try{
                        Prevision p = new Prevision();
                        String date_arrivee = request.getParameter("dateArrivee");
                        String heure_arrivee = request.getParameter("heureArrivee");
                        String date_depart = request.getParameter("dateDepart");
                        String heure_depart = request.getParameter("heureDepart");
                        double dureeRemorquage = Double.parseDouble(request.getParameter("dureeRemorquage"));
                        //String sql = "insert into prevision (idSambo, date_tonga, heure_tonga, duree_remorquage, date_Depart, heure_Depart) values("+approved+", '"+date_arrivee+"', '"+heure_arrivee+"', "+dureeRemorquage+", '"+date_depart+"', '"+heure_depart+"')";
                        //System.out.println(sql);
                        p.setIdSambo(approved);
                        p.setDateArrivee(Date.valueOf(date_arrivee));
                        p.setHeureArrivee(Time.valueOf(heure_arrivee));
                        p.setDureeRemorquage(dureeRemorquage);
                        p.setDateDepart(Date.valueOf(date_depart));
                        p.setHeureDepart(Time.valueOf(heure_depart));
                        p.inserer_prevision(c, p, approved);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
