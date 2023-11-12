<%--
  Created by IntelliJ IDEA.
  User: leslie
  Date: 2023-06-13
  Time: 15:01
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
    <title>Facture</title>
</head>
<style>
    body{
        font-family: "Poppins" , sans-serif;
    }
</style>
<body>
    <div class="container" style="margin-top: 15px">
        <div class="card">
            <div class="card-header">
                <center>Facture</center>
            </div>
            <div class="card-body">
                <table class="table table-striped table-hover" style="margin-top: 10px">
                    <thead>
                    <tr>
                        <th scope="col">Nom Sambo</th>
                        <th scope="col">Designation</th>
                        <th scope="col">Durée</th>
                        <th scope="col">Prix</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Sambo 1</td>
                        <td>Remorquage</td>
                        <td>30 min</td>
                        <td>200 000 Ar</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <span class="badge bg-secondary" style="width: 150px; margin-left: 940px; height: 30px; padding-top: 10px; font-size: 13px; margin-bottom: 15px">
                400 000Ar
            </span>
        </div>
    </div>
</body>
</html>
