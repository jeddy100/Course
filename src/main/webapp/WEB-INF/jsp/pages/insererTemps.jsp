<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="com.example.cinema.model.Etape" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.zip.CheckedOutputStream" %>
<%@ page import="com.example.cinema.model.Coureur" %>
<%@ page import="com.example.cinema.model.CoureurEtape" %>
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
    CoureurEtape coureurEtape = (CoureurEtape) request.getAttribute("coureurEtape");
    String errorMessage = (String) request.getAttribute("errorMessage");

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
            <h2>Ajouter un temps pour le coureur <%= coureurEtape.getCoureur().getNom() %></h2>
            <div class="container">
                <form action="/insererTemps" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="coureurEtape" value=<%=coureurEtape.getId_coureurEtape()%> />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <p>Ajouter un temps au coureur:</p>
                    <input type="datetime-local" name="temps" step="1"  class="form-control" placeholder="YYYY/MM/DD HH:MM:SS">

                    <button type="submit" class="btn btn-primary mt-3">Valider</button>
                    <% if (errorMessage != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                </form>

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
