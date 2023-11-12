<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <div class="" style="margin-top: 10px">
        <p><a class="btn btn-primary" href="ServletProposition?action=effacer&idSambo=all" role="button">Initialiser des Propositions</a></p>
    </div>
    <div class="" style="margin-top: 10px">
        <p><a class="btn btn-primary" href="ServletProposition?action=generer&idSambo=all" role="button">Générer des Propositions</a></p>
    </div>
    <div class="col-md-6" style="margin-left: 350px; position:relative; top:-225px;">
        <h2>Nouvelle Prévision</h2>
        <form method="post" action="Servlet?action=inserer">
            <div class="mb-3">
                Nom <input type="text" class="form-control" value="Sambo 1" name="nom">
            </div>

            <div class="mb-3">
                <div class="col-md">
                        Type
                        <select class="form-select" name="types">
                            <option selected>Type</option>
                            <option value="1">Pêches</option>
                            <option value="2">Pétrole</option>
                            <option value="3">Marchandises</option>
                            <option value="4">Paquebot</option>
                        </select>
                </div>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="inlineCheckbox1" value="1" name="nature">
                <label class="form-check-label" for="inlineCheckbox1">National</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="inlineCheckbox2" value="2" name="nature">
                <label class="form-check-label" for="inlineCheckbox2">International</label>
            </div>

            <div class="mb-3">
                Poids : <input type="number" class="form-control"name="poids" value="500">
            </div>
            <div class="mb-3">
                Longueur : <input type="number" class="form-control" name="longueur" value="250">
            </div>
            Le
            <div class="input-group mb-3">
                <input type="date" class="form-control" placeholder="" aria-label="Username" name="dateArrivee">
                <span class="input-group-text"> à </span>
                <input type="time" class="form-control" placeholder="" aria-label="Server" name="heureArrivee">
            </div>
            Jusqu'à
            <div class="input-group mb-3">
                <input type="date" class="form-control" placeholder="" aria-label="" name="dateDepart">
                <span class="input-group-text"> à </span>
                <input type="time" class="form-control" placeholder="" aria-label="" name="heureDepart">
            </div>
            <div class="mb-4">
                Durée du Remorquage: <input type="number" class="form-control" value="30" name="dureeRemorquage">
            </div>
            <input type="submit" value
                    ="Valider" class="btn btn-success">
        </form>
    </div>
</div>
</body>
</html>