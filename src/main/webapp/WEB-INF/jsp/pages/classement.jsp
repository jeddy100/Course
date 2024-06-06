<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.Duration" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.example.cinema.object.Ranking" %>

<jsp:include page="../template/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Classement de l'étape</title>
</head>
<body>
<div class="container">
    <h1>Classement de l'étape</h1>

<%--                                   GENERAL                              --%>
    <h3>Classementgeneral</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom du coureur</th>
            <th>Genre</th>
            <th>equipe</th>
            <th>Heure de début</th>
            <th>Heure de fin</th>
            <th>Temps total</th>
            <th> penalite</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ranking> rankingList = (List<Ranking>) request.getAttribute("rankingList");
            int position = 1;
            for (Ranking row : rankingList) {
        %>

        <tr>
            <td><%= row.getRang() %></td>
            <td><%= row.getTempsCoureurEtape().getCoureurEtape().getCoureur().getNom() %></td>
            <td><%= row.getTempsCoureurEtape().getCoureurEtape().getCoureur().getGenre().getGenre() %></td>
            <td><%= row.getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().getNom() %></td>
            <td><%= row.getTempsCoureurEtape().getHeuredebut()%></td>
            <td><%= row.getTempsCoureurEtape().getHeurefin() %></td>
            <td><%= row.getTempstotal()%></td>
            <td><%= row.getDuration().toSeconds()%></td>
            <td><%= row.getPoint()%></td>

        </tr>
        <% position++;
        } %>
        </tbody>
    </table>

    <%--                                   Junior                              --%>
    <h3>ClassementJunior</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom du coureur</th>
            <th>equipe</th>
            <th>Heure de début</th>
            <th>Heure de fin</th>
            <th>Temps total</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ranking> rankingListJunior = (List<Ranking>) request.getAttribute("rankingListJunior");
            for (Ranking rowj : rankingListJunior) {
        %>

        <tr>
            <td><%= rowj.getRang() %></td>
            <td><%= rowj.getTempsCoureurEtape().getCoureurEtape().getCoureur().getNom() %></td>
            <td><%= rowj.getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().getNom() %></td>
            <td><%= rowj.getTempsCoureurEtape().getHeuredebut()%></td>
            <td><%= rowj.getTempsCoureurEtape().getHeurefin() %></td>
            <td><%= rowj.getTempstotal()%></td>
            <td><%= rowj.getPoint()%></td>
        </tr>
       <% } %>
        </tbody>
    </table>
    <%--                                   Homme                              --%>
    <h3>Classement Homme</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom du coureur</th>
            <th>equipe</th>
            <th>Heure de début</th>
            <th>Heure de fin</th>
            <th>Temps total</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ranking> rankingListHomme = (List<Ranking>) request.getAttribute("rankingListHomme");
            for (Ranking rowh : rankingListHomme) {
        %>

        <tr>
            <td><%= rowh.getRang() %></td>
            <td><%= rowh.getTempsCoureurEtape().getCoureurEtape().getCoureur().getNom() %></td>
            <td><%= rowh.getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().getNom() %></td>
            <td><%= rowh.getTempsCoureurEtape().getHeuredebut()%></td>
            <td><%= rowh.getTempsCoureurEtape().getHeurefin() %></td>
            <td><%= rowh.getTempstotal()%></td>
            <td><%= rowh.getPoint()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <%--                                   Damme                              --%>
    <h3>Classement Damme</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom du coureur</th>
            <th>equipe</th>
            <th>Heure de début</th>
            <th>Heure de fin</th>
            <th>Temps total</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ranking> rankingListDamme = (List<Ranking>) request.getAttribute("rankingListDamme");
            for (Ranking rowd : rankingListDamme) {
        %>

        <tr>
            <td><%= rowd.getRang() %></td>
            <td><%= rowd.getTempsCoureurEtape().getCoureurEtape().getCoureur().getNom() %></td>
            <td><%= rowd.getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().getNom() %></td>
            <td><%= rowd.getTempsCoureurEtape().getHeuredebut()%></td>
            <td><%= rowd.getTempsCoureurEtape().getHeurefin() %></td>
            <td><%= rowd.getTempstotal()%></td>
            <td><%= rowd.getPoint()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
