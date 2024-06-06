<%@ page import="com.example.cinema.object.pointEtape" %>
<%@ page import="java.util.List" %>

<%
    List<pointEtape> pointsParEtape = (List<pointEtape>) request.getAttribute("pointsParEtape");
%>

<h1>Points de l'équipe par étape</h1>
<table>
    <thead>
    <tr>
        <th>Nom de l'étape</th>
        <th>Nombre de points</th>
    </tr>
    </thead>
    <tbody>
    <% for (pointEtape point : pointsParEtape) { %>
    <tr>
        <td><%= point.getNometape() %></td>
        <td><%= point.getNbpoints() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
