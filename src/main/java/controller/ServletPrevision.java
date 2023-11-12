package controller;

import connectBase.ConnectdB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import prevision.Prevision;
import sambo.Sambo;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

@WebServlet(name = "ServletPrevision", value = "/ServletPrevision")
public class ServletPrevision extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("modifier")){
            int idPrevision = Integer.parseInt(request.getParameter("id"));
            try{
                ConnectdB con= new ConnectdB();
                Connection c = con.connection();
                Sambo s= new Sambo();
                s.setIdPrevision(idPrevision);
                Sambo sambo= s.ma_sambo(c, idPrevision);
                request.setAttribute("prevision", sambo);
                request.getRequestDispatcher("modifier_prevision.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (Exception e){
                e.getMessage();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
