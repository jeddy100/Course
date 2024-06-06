<%@ page import="org.springframework.boot.web.servlet.server.Session" %><%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
</head>
<body>
<div class="col-md-12  grid-margin stretch-card ">
    <div class="card">
        <div class="card-body">
<h1>Inscription</h1>
<form action="/inscrire" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="text" name="nom" placeholder="nom" required>
    <input type="text" name="role" placeholder="role" required>
    <input type="password" name="mdp" placeholder="" required>
    <input type="submit" value="valider">
</form>
        </div>
    </div>
</div>


</body>
</html>
