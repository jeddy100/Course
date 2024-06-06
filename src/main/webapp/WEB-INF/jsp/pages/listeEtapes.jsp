<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.cinema.model.Etape" %>
<%@ page import="com.example.cinema.model.TempsCoureurEtape" %>
<jsp:include page="../template/header.jsp" />
<%
    List<Etape> liste = (List<Etape>) request.getAttribute("etapeList");
    Map<Long, List<TempsCoureurEtape>> tempsParEtape = (Map<Long, List<TempsCoureurEtape>>) request.getAttribute("tempsParEtape");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Liste des étapes</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h2>Liste des étapes de la course</h2>
            <div class="container">
                <table class="table table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Désignation</th>
                        <th scope="col">Rang étape</th>
                        <th scope="col">Date début</th>
                        <th scope="col">Heure de départ</th>
                        <th scope="col">Longueur</th>
                        <th scope="col">Coureurs inscrits</th>
                        <th scope="col">Ajouter coureur</th>
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
                        <td>
                            <ul>
                                <%
                                    List<TempsCoureurEtape> tempsCoureurEtapes = tempsParEtape.get(liste.get(i).getId_etape());
                                    if (tempsCoureurEtapes != null) {
                                        for (TempsCoureurEtape tce : tempsCoureurEtapes) {
                                %>
                                <li>
                                    <%= tce.getCoureurEtape().getCoureur().getNom() %> -
                                    Début: <%= tce.getHeuredebut() %> -
                                    Fin: <%= tce.getHeurefin() %> -
<%--                                    Temps total: <%= java.time.Duration.between(tce.getHeuredebut(), tce.getHeurefin()).toHours() %> h <%= java.time.Duration.between(tce.getHeuredebut(), tce.getHeurefin()).toMinutesPart() %> m <%= java.time.Duration.between(tce.getHeuredebut(), tce.getHeurefin()).toSecondsPart() %> s--%>
                                </li>
                                <%
                                    }
                                } else {
                                %>
                                <li>Aucun coureur inscrit</li>
                                <% } %>
                            </ul>
                        </td>
                        <td><a href="ajouterCoureur/<%= liste.get(i).getId_etape() %>" class="btn btn-info">Ajouter coureur</a></td>
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
