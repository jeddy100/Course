<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="com.example.cinema.model.Etape" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.zip.CheckedOutputStream" %>
<%@ page import="com.example.cinema.model.Coureur" %>
<%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../template/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Etape etape = (Etape) request.getAttribute("etape");
    int nbcoureur = (int) request.getAttribute("nbcoureur");
    List<Coureur> liste = (List<Coureur>) request.getAttribute("list");
    boolean canAddCoureur = (boolean) request.getAttribute("canAddCoureur");
%>
<html>

<head>
    <title>Ajouter Coureur</title>
    <!-- Ajout des fichiers CSS de Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h2>Ajouter un coureur pour l'étape <%= etape.getNom() %></h2>
            <p> debut : <%= etape.getDateEtape() %> <%=etape.getHeuredepart()%></p>
            <p>Nombre de coureurs pour l'étape : <%= etape.getNbCoureurEquipe() %></p>
            <p>Nombre de coureurs de l'équipe déjà inscrit : <%= nbcoureur %></p>
            <div class="container">
                <% if (canAddCoureur) { %>
                <form action="/insererCoureurEtape" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" name="etape" value="<%= etape.getId_etape() %>" />
                    <p>Sélectionner un coureur:</p>
                    <select name="coureur" class="form-control">
                        <% for (int i = 0; i < liste.size(); i++) { %>
                        <option value="<%= liste.get(i).getId_coureur() %>"><%= liste.get(i).getNom() %></option>
                        <% } %>
                    </select>
                    <button type="submit" class="btn btn-primary mt-3">Valider</button>
                </form>
                <% } else { %>
                <p class="alert alert-warning">Le nombre maximum de coureurs pour cette étape a été atteint.</p>
                <% } %>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../template/footer.jsp" />

<!-- Ajout des fichiers JavaScript de Bootstrap -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmMxrT/1p2b5iD5Jpg6a1R6jB5GvVYb9hZ/1m5DcknheJJ" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-omA7Hk/l0SdjpWpiORfRmFnpDoFdyk2RGUDwO8DknvU8xwZ7Y5I0L3Jo3Swx0jC" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
