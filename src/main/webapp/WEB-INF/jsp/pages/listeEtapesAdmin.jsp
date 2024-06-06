<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cinema.model.Etape" %><%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../template/header.jsp" />
<%
    List<Etape> liste= (List<Etape>) request.getAttribute("etapeList");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
</head>
<body>
<div class="col-md-12  grid-margin stretch-card ">
    <div class="card">
        <div class="card-body">
            <h2>Liste des etapes de la course </h2>
            <h2>size: <% out.print(liste.size()); %></h2>
            <div class="container">
                <table class="table table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">designation</th>
                        <th scope="col">rang etape</th>
                        <th scope="col">date debut</th>
                        <th scope="col">heure de depart</th>
                        <th scope="col">longueur</th>
                        <th scope="col">ajouter coureur</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < liste.size(); i++) { %>
                    <tr>
                        <td><%= liste.get(i).getNom() %></td>
                        <td><%= liste.get(i).getRangEtape() %></td>
                        <td><%= liste.get(i).getDateEtape() %></td>
                        <td><%= liste.get(i).getHeuredepart() %></td>
                        <td><%= liste.get(i).getLongueur() %> km</td>
                        <td><a href="listeCoureurEtape/<%= liste.get(i).getId_etape() %>" class="btn btn-info">Liste des coureurs</a></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>
<jsp:include page="../template/footer.jsp" />


</body>
</html>
