package controller;

import connectBase.ConnectdB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import prevision.Prevision;
import proposition.Proposition;
import quai.Quai;
import sambo.Sambo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

@WebServlet(name = "ServletProposition", value = "/ServletProposition")
public class ServletProposition extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("proposition")) {
            try {
                ConnectdB con = new ConnectdB();
                Connection c = con.connection();
                Sambo s = new Sambo();
                Quai q = new Quai();
                Prevision p = new Prevision();
                Vector<Sambo> samboList = s.liste_sambo(c);
                Vector<Quai> quaiList = q.liste_quai(c);
                Vector<Prevision> previsions = p.les_previsions(c);
                request.setAttribute("sambo", samboList);
                request.setAttribute("quai", quaiList);
                request.setAttribute("prevision", previsions);
                request.getRequestDispatcher("propositions.jsp").forward(request, response);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        if(action.equals("details")){
            try{
                ConnectdB con= new ConnectdB();
                Connection c = con.connection();
                Sambo s= new Sambo();
                s.setIdPrevision(Integer.parseInt(request.getParameter("id")));
                Sambo sambo= s.ma_sambo(c, Integer.parseInt(request.getParameter("id")));
                request.setAttribute("prevision", sambo);
                request.getRequestDispatcher("details_prevision.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (Exception e){
                e.getMessage();
            }
        }
        /*if (action != null && action.equals("proposition")) {
            try {
                Vector<Proposition> propositionList = new Vector<Proposition>();
                ConnectdB con = new ConnectdB();
                Connection c = con.connection();
                Proposition p = new Proposition();
            } catch (Exception e) {
                e.getMessage();
            }
        }*/
        if (action != null && action.equals("effacer")){
            try{
                String sql = "truncate table proposition";
                ConnectdB con = new ConnectdB();
                Connection c = con.connection();
                c.createStatement().executeUpdate(sql);
                response.sendRedirect("index.jsp");
            }catch (Exception e){
                e.getMessage();
            }
        }if(action != null && action.equals("generer")){
            try{
                ConnectdB con = new ConnectdB();
                Connection c = con.connection();
                Proposition p = new Proposition();
                p.generer_proposition(c);
                response.sendRedirect("index.jsp");
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String action = request.getParameter("action");
            if (action != null && action.equals("confirmer_modification")) {
                try {
                    ConnectdB con = new ConnectdB();
                    Connection c = con.connection();
                    Sambo s = new Sambo();
                    Prevision p = new Prevision();
                    int idPrevision = Integer.parseInt(request.getParameter("id"));
                    Date dateArrivee = Date.valueOf(request.getParameter("dateArrivee"));
                    Date dateDepart = Date.valueOf(request.getParameter("dateDepart"));
                    Time heureArrivee = Time.valueOf(request.getParameter("heureArrivee"));
                    Time heureDepart = Time.valueOf(request.getParameter("heureDepart"));
                    p = new Prevision(idPrevision, dateArrivee, heureArrivee, dateDepart, heureDepart);
                    p.modifier_prevision(c, p);
                    response.sendRedirect("Servlet?action=lister");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    }
