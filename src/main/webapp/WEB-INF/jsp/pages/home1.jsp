<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cinema.model.Utilisateur" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %><%--
  Created by IntelliJ IDEA.
  Utilisateur: Jeddy
  Date: 19/12/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../template/header.jsp" />
<%
    Utilisateur utilisateur= (Utilisateur) request.getAttribute("utilisateur");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    boolean isAdmin=false;
    for(GrantedAuthority authority: authentication.getAuthorities()){
        if(authority.getAuthority().equals("ROLE_admin")){
            isAdmin=true;
        }
    }
%>
<html>

<head>
    <title>Title</title>
</head>
<body>
<div class="col-md-12  grid-margin stretch-card ">
    <div class="card">
        <div class="card-body">
<h1>Bienvenu <%=utilisateur.getRole()%></h1>
            <%

                 if (isAdmin) {%>
            <h1>insertion import etape</h1>
            <form action="/importdonneespost" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="file" name="file" placeholder="imp">
                <input type="submit" value="valider" class="btn btn-primary mt-3">
            </form>
            <h1>insertion import points</h1>
            <form action="/importdonneespointpost" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="file" name="file" placeholder="imp">
                <input type="submit" value="valider" class="btn btn-primary mt-3">
            </form>
            <h1>insertion import resultat</h1>
            <form action="/importdonneesresultatpost" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="file" name="file" placeholder="imp">
                <input type="submit" value="valider" class="btn btn-primary mt-3">
            </form>

            <h1>reset des donnees</h1>
            <form action="/resetdonnees" method="post" >
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="submit" value="valider" class="btn btn-primary mt-3">
            </form>
            <% }%>
        </div>
    </div>
</div>
<jsp:include page="../template/footer.jsp" />


</body>
</html>
