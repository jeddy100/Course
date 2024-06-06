<%@ page import="com.example.cinema.object.RankingPoint" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Certificat du Champion en Cours d'Exécution</title>
    <%RankingPoint gagnant = (RankingPoint) request.getAttribute("gagnant");%>
    <jsp:include page="../template/header.jsp" />

</head>

<body>
<div id="certif">

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .certificate {
            background-color: #ffffff;
            border: 2px solid #8B4513;
            border-left: 15px solid #8B4513;
            border-right: 15px solid #8B4513;
            width: 600px;
            margin: 20px auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center; /* Aligne le texte au centre */
        }

        .title {
            color: #8B4513;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .content {
            font-size: 18px;
            margin-bottom: 20px;
            color: #8B4513;
        }

        .username {
            font-size: 24px;
            color: #8B4513;
        }

        .signature {
            display: block;
            margin-top: 20px;
        }
    </style>
<div class="certificate">
    <div class="title"><h2>Certificat du Champion de la Course</h2></div>
    <div class="content">
        <p>Félicitations à <span class="username"><%= gagnant.getUtilisateur().getNom() %></span> pour avoir remporté le titre de Champion de la course !</p>
        <p>Nombre de points : <%= gagnant.getPoint() %></p>
        <p>Date : <%= new java.util.Date() %></p>
    </div>
    <p>signature du responsable:</p>
    <h3>Admin</h3>

</div>
</div>
<button onclick="generatePDF()">export to pdf</button>

<jsp:include page="../template/footer.jsp" />
<script src="../../public/dist/html2pdf.bundle.min.js"></script>
<script>
    function generatePDF() {
        var content = document.getElementById("certif").innerHTML ;

        var opt = {
            margin:       1,
            filename:     'certificat.pdf',
            image:        {type: 'jpeg', quality: 0.98},
            html2canvas:  {scale: 2},
            jsPDF:        {unit: 'in', format: 'a4', orientation: 'landscape'}
        };
        // Choose the element that our invoice is rendered in.
        // html2pdf().set(opt).from(content).save();
        html2pdf().set(opt).from(content).toPdf().get('pdf').then(function (pdf){
            window.open(pdf.output('bloburl'),'blanc');
        });

    }
    window.onload=function () {
      generatePDF();
    };

</script>
</body>
</html>
