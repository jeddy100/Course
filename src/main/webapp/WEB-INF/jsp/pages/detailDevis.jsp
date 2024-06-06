<%@ page import="org.apache.catalina.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.boot.autoconfigure.cassandra.CassandraProperties" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.cinema.model.devis.Finition_devis" %>
<%@ page import="com.example.cinema.model.devis.TravauxDevis" %>
<%@ page import="com.example.cinema.model.devis.Devis" %>
Created by IntelliJ IDEA.
  User: Jeddy
  Date: 09/12/2023
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../template/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    double prixtotal= (double) request.getAttribute("prixtotal");
    Finition_devis finition_devis= (Finition_devis) request.getAttribute("finition_devis");
    List<TravauxDevis>liste= (List<TravauxDevis>) request.getAttribute("travauxDevis");
    Devis devis= (Devis) request.getAttribute("devis");
%>
<html>
<head>
    <title>detail devis</title>
</head>
<body>
<button onclick="generatePDF()">export to pdf</button>
<div id="card">
<div class="col-md-12  grid-margin stretch-card ">
    <div class="card" >
        <div class="card-body">
            <div class="container">
                <h1>Detail du devis: <%= devis.getId_devis() %><%= devis.getDesignation() %></h1>
                <p>Utilisateur:<%= devis.getUtilisateur().getTelephone() %></p>
                <p>date de creation du devis:<%= devis.getDateCreation() %></p>
                <p>date de debut de la construction:<%= devis.getDateDebutTravaux() %></p>
                <p>date de fin de la construction:<%= devis.getDateFinTravaux() %></p>
                <p>type de batiment:<%= devis.getTypeBatiment().getDesignation() %></p>
                <p>type de finition:<%= finition_devis.getFinition().getDesignation() %></p>
                <p>multiplicateur:<%= finition_devis.getMultiplicateur() %></p>

                <table class="table table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">num</th>
                        <th scope="col">designation</th>
                        <th scope="col">Unite</th>
                        <th scope="col">quantite</th>
                        <th scope="col">prix unitaire</th>
                        <th scope="col">total</th>
                        <th scope="col">etat</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < liste.size(); i++) { %>
                    <tr>
                        <td><%= liste.get(i).getTravaux().getCode() %></td>
                        <td><%= liste.get(i).getTravaux().getDesignation() %></td>
                        <td><%= liste.get(i).getTravaux().getUnite() %></td>
                        <td><%= liste.get(i).getQuantiteTravaux() %></td>
                        <td><%= liste.get(i).getPrixtravaux() %></td>
                        <td><%= liste.get(i).getPrixtravaux()*liste.get(i).getQuantiteTravaux() %></td>
                        <% if (liste.get(i).getEtat() == 1){%>
                        <td>en cours de validation</td>
                        <%} %>
                        <% if (liste.get(i).getEtat() == 3){%>
                        <td>valide</td>
                        <%} %>
                    </tr>

                    <% } %>

                    </tbody>

                </table>
                <h2>TOTAL : <%= prixtotal%></h2>


            </div>
        </div>
    </div>

</div>
</div>
<jsp:include page="../template/footer.jsp" />
<script src="../../public/dist/html2pdf.bundle.min.js"></script>
    <script>
        function generatePDF() {
        var content = document.getElementById("card").innerHTML ;

        var opt = {
        margin:       1,
        filename:     'devis_n_<%=devis.getId_devis()%>.pdf',
        image:        {type: 'jpeg', quality: 0.98},
        html2canvas:  {scale: 2},
        jsPDF:        {unit: 'in', format: 'a4', orientation: 'landscape'}
    };
        // Choose the element that our invoice is rendered in.
        html2pdf().set(opt).from(content).save();
    }

</script>


</body>
</html>
