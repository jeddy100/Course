<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cinema.model.Etape" %>
<%@ page import="com.example.cinema.model.Penalite" %><%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../template/header.jsp" />
<%
    List<Penalite> liste= (List<Penalite>) request.getAttribute("penaliteList");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h2>Liste des pénalités</h2>
            <h2>size: <% out.print(liste.size()); %></h2>
            <div class="container">
                <a href="/insertPenalite" class="btn btn-primary mt-3">Ajouter Pénalité</a>
                <table class="table table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Étape</th>
                        <th scope="col">Équipe</th>
                        <th scope="col">Temps de pénalité</th>
                        <th scope="col">Supprimer</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < liste.size(); i++) {
                        Penalite penalite = liste.get(i);
                        String modalId = "modal-small-" + penalite.getId_penalite();
                    %>
                    <tr>
                        <td><%= penalite.getEtape().getNom() %></td>
                        <td><%= penalite.getUtilisateur().getNom() %></td>
                        <td><%= penalite.getTempsPenalite() %></td>
                        <td>
                            <button class="btn btn-danger" data-toggle="modal" data-target="#<%= modalId %>">
                                <span class="ti ti-trash"></span> Supprimer
                            </button>
                        </td>
                    </tr>

                    <!-- Modal -->
                    <div class="modal fade" id="<%= modalId %>" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <div class="modal-title">Êtes-vous sûr?</div>
                                    <p class="text-wrap">Voulez-vous vraiment effacer la pénalité pour l'équipe <%= penalite.getUtilisateur().getNom() %> ?</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-link link-secondary me-auto" data-dismiss="modal">Non</button>
                                    <form:form action="/supprimerPenalite" method="post">
                                        <input type="hidden" value="<%= penalite.getId_penalite() %>" name="id">
                                        <button type="submit" class="btn btn-danger">Oui</button>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
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
