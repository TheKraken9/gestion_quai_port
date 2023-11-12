<%@ page import="java.util.Vector" %>
<%@ page import="sambo.Sambo" %>
<%@ page import="quai.Quai" %>
<%@ page import="prevision.Prevision" %>
<%@ page import="remorquage.Remorquage" %>
<%@ page import="connectBase.ConnectdB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="stationnement.Stationnement" %><%--
  Created by IntelliJ IDEA.
  User: thekraken9
  Date: 2023-06-14
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="bootstrap 5/bootstrap-icons/icons/">
    <link rel="stylesheet" href="fontawesome-free-5.0.2/web-fonts-with-css/css/fontawesome.min.css">
    <link rel="stylesheet" href="bootstrap-5.0.2-dist/css/bootstrap.min.css">

</head>
<style>
    .bateau {
        background-color: #4d709e;
        color: #fff;
        padding: 10px;
        margin-bottom: 10px;
        text-align: center;
    }

    .bateau.small {
        width: 80px;
        height: 80px;
    }

    .bateau.medium {
        width: 120px;
        height: 120px;
    }

    .bateau.large {
        width: 160px;
        height: 160px;
    }

</style>
<body>
<div class="container-fluid">
    <h1 class="text-center">Proposition des quais</h1>
    <div class="row">
        <%
            Vector<Quai> quais = (Vector<Quai>) request.getAttribute("quai");
            Vector<Prevision> prevision = (Vector<Prevision>) request.getAttribute("prevision");
            for(Quai q : quais) {
        %>
        <div class="container">
            <h3>Quai numéro <%= q.getNumero() %></h3>
            <div class="quai" id="quai1">
                <%  for (Prevision p : prevision) {
                    if(p.getNumQuai() == q.getNumero()) {
                        double prix = 0;
                        double prix_stationnement = 0;
                        try{
                            ConnectdB con = new ConnectdB();
                            Connection connection = con.connection();
                            Sambo sambo = new Sambo();
                            Stationnement stationnement = new Stationnement();
                            Remorquage remorquage = new Remorquage();
                            sambo.setNomNature(p.getNomNature());
                            String nature = p.getNomNature();
                            int id = p.getIdType();
                            System.out.println(p.getDureeRemorquage());
                            System.out.println(id);
                            System.out.println(nature);
                            sambo.setIdTypes(p.getIdType());
                            prix = remorquage.tarif_remorquage(connection, p, sambo);
                            prix_stationnement = stationnement.tarif_stationnement(connection, p, sambo);
                            System.out.println(prix);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                %>
                    <div class="bateau w-50">
                        <span><%= p.getNomSambo() %> - <%= p.getDateArrivee() %> <%= p.getHeureArrivee() %> jusqu'à <%= p.getDateDepart() %> <%= p.getHeureDepart() %> | <%= prix %> Ariary | <%= prix_stationnement %> Ariary</span>
                    </div>
                <% } %>
            </div>
        </div>
        <% }} %>
    </div>
</div>
</body>
<script>
</script>
</html>
