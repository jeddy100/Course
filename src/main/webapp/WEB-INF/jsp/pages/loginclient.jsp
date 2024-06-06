<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Équipe</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS (optional) -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            margin-top: 100px;
        }
        .card {
            border-radius: 10px;
        }
        .card-body {
            padding: 2rem;
        }
        h1 {
            margin-bottom: 2rem;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .btn-primary {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container login-container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h1 class="text-center">Login Équipe</h1>
                    <form action="/login" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="form-group">
                            <label for="nom">Nom d'Équipe</label>
                            <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom d'Équipe">
                        </div>
                        <div class="form-group">
                            <label for="mdp">Mot de Passe</label>
                            <input type="password" class="form-control" id="mdp" name="mdp" placeholder="Mot de Passe">
                        </div>
                        <input type="hidden" name="role" value="client">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../template/footer.jsp" />

<!-- Bootstrap JS and dependencies (optional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
