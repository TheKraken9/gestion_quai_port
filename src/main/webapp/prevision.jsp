<%--
  Created by IntelliJ IDEA.
  User: leslie
  Date: 2023-06-13
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="bootstrap-5.0.2-dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="bootstrap 5/bootstrap-icons/icons/">
    <link rel="stylesheet" href="fontawesome-free-5.0.2/web-fonts-with-css/css/fontawesome.min.css">
    <title>Prevision</title>
</head>
<style>
    body{
        font-family: "Poppins" , sans-serif;
    }
</style>
<body>
<div class="container">
    <div class="row">
        <table class="table table-striped table-hover" style="margin-top: 10px">
            <thead>
            <tr>
                <th scope="col">quai</th>
                <th scope="col">Prix Station</th>
                <th scope="col">Prix Remorquage</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>2 000 000 Ar</td>
                <td>500 000 Ar</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="col-md-6" style="margin-left: 350px">
        <form method="" action="facture.jsp">

            <div class="mb-3">
                Remorquage : <a class="btn btn-primary" href="" role="button" style="margin-left: 12px">Début</a> <a class="btn btn-primary" href="" role="button">Fin</a>
            </div>

            <div class="mb-3">
                Stationnement : <a class="btn btn-primary" href="" role="button">Début</a> <a class="btn btn-primary" href="" role="button">Fin</a>
            </div>

            <div class="mb-3">
                Session d'Eau : <input type="number" class="form-control" placeholder="" name="eau">
            </div>

            <div class="mb-3">
                Réparation : <input type="number" class="form-control" placeholder="" name="reparation">
            </div>

            <input type="submit" value="Facturer" class="btn btn-success">
        </form>
    </div>
</div>
</body>
</html>
