<%@ page import="sambo.Sambo" %><%--
  Created by IntelliJ IDEA.
  User: thekraken9
  Date: 2023-06-15
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Port</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link rel="stylesheet" href="bootstrap 5/bootstrap-icons/icons/">
  <link rel="stylesheet" href="fontawesome-free-5.0.2/web-fonts-with-css/css/fontawesome.min.css">
  <link rel="stylesheet" href="bootstrap-5.0.2-dist/css/bootstrap.min.css">
</head>
<style>
  body{
    font-family: "Poppins" , sans-serif;
  }
</style>
<body>
<div class="container">
  <div class="" style="margin-top: 10px">
    <p><a class="btn btn-primary" href="Servlet?action=lister" role="button">Listes des bateaux</a></p>
  </div>
  <div class="" style="margin-top: 10px">
    <p><a class="btn btn-primary" href="ServletProposition?action=proposition&idSambo=all" role="button">Listes des Propositions</a></p>
  </div>
  <div class="col-md-6" style="margin-left: 350px; position:relative; top:-110px;">
    <%
      Sambo sambo = (Sambo) request.getAttribute("prevision");
    %>
    <h2>Nouvelle Prévision</h2>
    <form method="post" action="ServletProposition?action=confirmer_modification&id=<%= sambo.getIdPrevision() %>" >
      <div class="mb-3">
        Nom <input type="text" class="form-control" value="Sambo 1" name="nom" disabled>
      </div>
      <div class="mb-3">
        Poids : <input type="number" class="form-control"name="poids" value="<%= sambo.getPoids() %>" disabled>
      </div>
      <div class="mb-3">
        Longueur : <input type="number" class="form-control" name="longueur" value="<%= sambo.getLongueur() %>" disabled>
      </div>
      Le
      <div class="input-group mb-3">
        <input type="date" class="form-control" placeholder="" aria-label="Username" name="dateArrivee" value="<%= sambo.getDateArrivee() %>">
        <span class="input-group-text"> à </span>
        <input type="time" class="form-control" placeholder="" aria-label="Server" name="heureArrivee" value="<%= sambo.getHeureArrivee() %>">
      </div>
      Jusqu'à
      <div class="input-group mb-3">
        <input type="date" class="form-control" placeholder="" aria-label="" name="dateDepart" value="<%= sambo.getDateDepart() %>">
        <span class="input-group-text"> à </span>
        <input type="time" class="form-control" placeholder="" aria-label="" name="heureDepart" value="<%= sambo.getHeureDepart() %>">
      </div>
      <div class="mb-4">
        Durée du Remorquage: <input type="number" class="form-control" value="<%= sambo.getDureeRemorquage() %>" name="dureeRemorquage" disabled>
      </div>
      <input type="submit" value="Valider" class="btn btn-success">
    </form>
  </div>
</div>
</body>
</html>
