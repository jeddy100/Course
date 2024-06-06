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
<%@ page import="com.example.cinema.object.RankingPoint" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>

<jsp:include page="../template/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean isAdmin = false;
    for (GrantedAuthority authority : authentication.getAuthorities()) {
        if (authority.getAuthority().equals("ROLE_admin")) {
            isAdmin = true;
        }
    }
%>
<head>
    <title>Classement de l'étape</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .chart-container {
            width: 300px;
            height: 300px;
            margin: auto;
        }
        .same-points {
            color: blue;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Classement de l'étape</h1>
    <% if (isAdmin) { %>
    <a href="/certificat" class="btn btn-primary mt-3">voir certificat</a>
    <% } %>

    <h3>Classement Total</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom d'utilisateur</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RankingPoint> rankingPointListTotal = (List<RankingPoint>) request.getAttribute("rankingPointListTotal");
            int positiont = 1;
            int previousPointst = -1;
            for (RankingPoint row : rankingPointListTotal) {
                boolean samePoints = (previousPointst != -1 && previousPointst == row.getPoint());
                previousPointst = (int) row.getPoint();
        %>
        <tr class="<%= samePoints ? "same-points" : "" %>">
            <td><%= positiont %></td>
            <td><%= row.getUtilisateur().getNom() %></td>
            <td><%= row.getPoint() %></td>
        </tr>
        <%
                positiont++;
            }
        %>
        </tbody>
    </table>
    <div class="chart-container">
        <canvas id="pieChartTotal"></canvas>
    </div>

    <h3>Classement Général</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom d'utilisateur</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RankingPoint> rankingList = (List<RankingPoint>) request.getAttribute("rankingPointList");
            int position = 1;
            int previousPoints = -1;
            for (RankingPoint row : rankingList) {
                boolean samePoints = (previousPoints != -1 && previousPoints == row.getPoint());
                previousPoints = (int) row.getPoint();
        %>
        <tr class="<%= samePoints ? "same-points" : "" %>">
            <td><%= position %></td>
            <td><%= row.getUtilisateur().getNom() %></td>
            <td><%= row.getPoint() %></td>
            <td><a href="pointEquipeEtape/<%=row.getUtilisateur().getId() %>" class="btn btn-info"> voir point par etape</a></td>

        </tr>
        <%
                position++;
            }
        %>
        </tbody>
    </table>
    <div class="chart-container">
        <canvas id="pieChartGeneral"></canvas>
    </div>

    <h3>Classement Homme</h3>
    <table class="table">
        <thead>
        <tr >
            <th>Position</th>
            <th>Nom d'utilisateur</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RankingPoint> rankingPointListHomme = (List<RankingPoint>) request.getAttribute("rankingPointListHomme");
            int positionh = 1;
            int sizeTotal = rankingPointListHomme.size();
            for (int i = 0; i < sizeTotal; i++) {
                RankingPoint row = rankingPointListHomme.get(i);
                int point = (int) row.getPoint();
                boolean samePoints = false;
                if (i > 0) {
                    int previousPoint = (int) rankingPointListHomme.get(i - 1).getPoint();
                    samePoints = previousPoint == point;
                }
                if (i < sizeTotal - 1) {
                    int nextPoint = (int) rankingPointListHomme.get(i + 1).getPoint();
                    samePoints = samePoints || nextPoint == point;
                }
        %>
        <tr class="<%= samePoints ? "same-points" : "" %>">
            <td><%= positionh %></td>
            <td><%= row.getUtilisateur().getNom() %></td>
            <td><%= row.getPoint() %></td>
        </tr>
        <%
                positionh++;
            }
        %>
        </tbody>
    </table>
    <div class="chart-container">
        <canvas id="pieChartHomme"></canvas>
    </div>

    <h3>Classement Dame</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom d'utilisateur</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RankingPoint> rankingPointListDame = (List<RankingPoint>) request.getAttribute("rankingPointListDamme");
            int positiond = 1;
            for (int i = 0; i < rankingPointListDame.size(); i++) {
                RankingPoint row = rankingPointListDame.get(i);
                int point = (int) row.getPoint();
                boolean samePoints = false;
                if (i > 0) {
                    int previousPoint = (int) rankingPointListDame.get(i - 1).getPoint();
                    samePoints = previousPoint == point;
                }
                if (i < sizeTotal - 1) {
                    int nextPoint = (int) rankingPointListDame.get(i + 1).getPoint();
                    samePoints = samePoints || nextPoint == point;
                }
        %>
        <tr class="<%= samePoints ? "same-points" : "" %>">
            <td><%= positiond %></td>
            <td><%= row.getUtilisateur().getNom() %></td>
            <td><%= row.getPoint() %></td>
        </tr>
        <%
                positiond++;
            }
        %>
        </tbody>
    </table>
    <div class="chart-container">
        <canvas id="pieChartDame"></canvas>
    </div>

    <h3>Classement Junior</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Position</th>
            <th>Nom d'utilisateur</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<RankingPoint> rankingPointListJunior = (List<RankingPoint>) request.getAttribute("rankingPointListJunior");
            int positionj = 1;
            for (int i = 0; i < rankingPointListJunior.size(); i++) {
                RankingPoint row = rankingPointListJunior.get(i);
                int point = (int) row.getPoint();
                boolean samePoints = false;
                if (i > 0) {
                    int previousPoint = (int) rankingPointListJunior.get(i - 1).getPoint();
                    samePoints = previousPoint == point;
                }
                if (i < sizeTotal - 1) {
                    int nextPoint = (int) rankingPointListJunior.get(i + 1).getPoint();
                    samePoints = samePoints || nextPoint == point;
                }
        %>
        %>
        <tr class="<%= samePoints ? "same-points" : "" %>">
            <td><%= positionj %></td>
            <td><%= row.getUtilisateur().getNom() %></td>
            <td><%= row.getPoint() %></td>
        </tr>
        <%
                positionj++;
            }
        %>
        </tbody>
    </table>
    <div class="chart-container">
        <canvas id="pieChartJunior"></canvas>
    </div>

    <script>
        function generatePieChartData(rankingPointList) {
            const labels = rankingPointList.map(item => item.utilisateur.nom);
            const data = rankingPointList.map(item => item.point);
            return {
                labels: labels,
                datasets: [{
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            };
        }

        function renderPieChart(context, data) {
            new Chart(context, {
                type: 'pie',
                data: data,
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Répartition des points'
                        }
                    }
                },
            });
        }

        <%-- Convertir les listes en JSON manuellement --%>
        const rankingPointListTotal = JSON.parse('<%= rankingPointListTotal.stream().map(rp -> "{\"utilisateur\":{\"nom\":\"" + rp.getUtilisateur().getNom() + "\"},\"point\":" + rp.getPoint() + "}").collect(java.util.stream.Collectors.joining(",", "[", "]")) %>');
        const rankingPointListGeneral = JSON.parse('<%= rankingList.stream().map(rp -> "{\"utilisateur\":{\"nom\":\"" + rp.getUtilisateur().getNom() + "\"},\"point\":" + rp.getPoint() + "}").collect(java.util.stream.Collectors.joining(",", "[", "]")) %>');
        const rankingPointListHomme = JSON.parse('<%= rankingPointListHomme.stream().map(rp -> "{\"utilisateur\":{\"nom\":\"" + rp.getUtilisateur().getNom() + "\"},\"point\":" + rp.getPoint() + "}").collect(java.util.stream.Collectors.joining(",", "[", "]")) %>');
        const rankingPointListDame = JSON.parse('<%= rankingPointListDame.stream().map(rp -> "{\"utilisateur\":{\"nom\":\"" + rp.getUtilisateur().getNom() + "\"},\"point\":" + rp.getPoint() + "}").collect(java.util.stream.Collectors.joining(",", "[", "]")) %>');
        const rankingPointListJunior = JSON.parse('<%= rankingPointListJunior.stream().map(rp -> "{\"utilisateur\":{\"nom\":\"" + rp.getUtilisateur().getNom() + "\"},\"point\":" + rp.getPoint() + "}").collect(java.util.stream.Collectors.joining(",", "[", "]")) %>');

        renderPieChart(document.getElementById('pieChartTotal'), generatePieChartData(rankingPointListTotal));
        renderPieChart(document.getElementById('pieChartGeneral'), generatePieChartData(rankingPointListGeneral));
        renderPieChart(document.getElementById('pieChartHomme'), generatePieChartData(rankingPointListHomme));
        renderPieChart(document.getElementById('pieChartDame'), generatePieChartData(rankingPointListDame));
        renderPieChart(document.getElementById('pieChartJunior'), generatePieChartData(rankingPointListJunior));
    </script>
</div>
</body>
</html>
