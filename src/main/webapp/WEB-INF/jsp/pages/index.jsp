<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">
    <link rel="stylesheet" href="../../public/vendors/feather/feather.css">
    <link rel="stylesheet" href="../../public/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../public/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="../../public/vendors/typicons/typicons.css">
    <link rel="stylesheet" href="../../public/vendors/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="../../public/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="../../public/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <link rel="stylesheet" href="../../public/js/select.dataTables.min.css">
    <link rel="shortcut icon" href="../../public/images/favicon.png" />
    <link rel="stylesheet" href="../../public/css/style.css">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'accueil</title>
    <!-- Bootstrap CSS -->
    <style>
        body {
            background-color: grey;
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url("../../public/images/fonddd.png");
        }
        .container {
            text-align: center;
        }
        .btn {
            margin: 10px;
        }
    </style>
</head>
<body>
<div class="container text-white" >
    <h1>Bienvenue sur notre site</h1>
    <p>Connectez-vous pour voir les courses Equipe :</p>
    <a href="/login" class="btn btn-primary">Se connecter</a>
<%--    <a href="/loginclient" class="btn btn-secondary">Se connecter</a>--%>
</div>
</body>
</html>
