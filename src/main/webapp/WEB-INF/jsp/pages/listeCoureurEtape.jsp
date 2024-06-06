<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cinema.model.Etape" %>
<%@ page import="com.example.cinema.model.CoureurEtape" %><%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../template/header.jsp" />
<%
    List<CoureurEtape> liste= (List<CoureurEtape>) request.getAttribute("coureurEtapeList");
    Etape etape= (Etape) request.getAttribute("etape");
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
            <h2>Liste des coureurs pour l'etape <%=etape.getNom()%> </h2>
            <h2>depart:  <%=etape.getDateEtape()%><%=etape.getHeuredepart()%> </h2>
            <h2>size: <% out.print(liste.size()); %></h2>
            <div class="container">
                <table class="table table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">designation</th>
                        <th scope="col">numero</th>
                        <th scope="col">nom d equipe</th>
                        <th scope="col">ajouter un temps</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < liste.size(); i++) { %>
                    <tr>
                        <td><%= liste.get(i).getCoureur().getNom() %></td>
                        <td><%= liste.get(i).getCoureur().getNumDorssard() %></td>
                        <td><%= liste.get(i).getCoureur().getUtilisateur().getNom() %></td>
                        <td><a href="ajouterHeureArrivee/<%= liste.get(i).getId_coureurEtape() %>" class="btn btn-info">ajouter un temps</a></td>
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
