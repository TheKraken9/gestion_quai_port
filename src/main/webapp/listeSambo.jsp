<%@ page import="java.util.List" %>
<%@ page import="sambo.Sambo" %>
<%@ page import="java.util.Vector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="bootstrap-5.0.2-dist/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link rel="stylesheet" href="bootstrap 5/bootstrap-icons/icons/">
  <link rel="stylesheet" href="fontawesome-free-5.0.2/web-fonts-with-css/css/fontawesome.min.css">
  <title>Listes Sambo</title>
  <style>
    body{
      font-family: "Poppins" , sans-serif;
    }
  </style>
</head>
<body>
  <div class="container">
  <div class="row">
    <table class="table table-striped table-hover" style="margin-top: 10px">
      <thead>
        <tr>
          <th scope="col">Nom</th>
          <th scope="col">Type</th>
          <th scope="col">Nature</th>
          <th scope="col">Date d'arrivée</th>
          <th scope="col">Heure d'arrivée</th>
          <th scope="col">Date de depart</th>
          <th scope="col">Heure de depart</th>
          <th scope="col">Poids</th>
          <th scope="col">Longueur</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
      <% Vector<Sambo> sambo = (Vector<Sambo>) request.getAttribute("sambo");
        for (Sambo sambo1 : sambo) { %>
        <tr>
          <td><a href="ServletProposition?action=proposition&idSambo=<%= sambo1.getIdSambo() %>"><%= sambo1.getNom() %></a></td>
          <td><%= sambo1.getNomType() %></td>
          <td><%= sambo1.getNomNature() %></td>
          <td><%= sambo1.getDateArrivee() %></td>
          <td><%= sambo1.getHeureArrivee() %></td>
          <td><%= sambo1.getDateDepart() %></td>
          <td><%= sambo1.getHeureDepart() %></td>
          <td><%= sambo1.getPoids() %></td>
          <td><%= sambo1.getLongueur() %></td>
          <td class="nav-link"><a href="ServletPrevision?action=modifier&id=<%= sambo1.getIdPrevision()%>">Modifier</a></td>
        </tr>
      <% } %>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
