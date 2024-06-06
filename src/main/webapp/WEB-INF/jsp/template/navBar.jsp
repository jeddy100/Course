<%@ page import="com.example.cinema.model.Utilisateur" %>
<%@ page import="org.springframework.boot.autoconfigure.neo4j.Neo4jProperties" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Utilisateur utilisateur= (Utilisateur) request.getAttribute("utilisateur");
  Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
  boolean isAdmin=false;
  for(GrantedAuthority authority: authentication.getAuthorities()){
    if(authority.getAuthority().equals("ROLE_admin")){
      isAdmin=true;
    }
  }
%>

<div class="container-fluid page-body-wrapper">

  <nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
      <li class="nav-item">
        <a class="nav-link" href="">
          <i class="mdi mdi-grid-large menu-icon"></i>
          <span class="menu-title">Dashboard</span>
        </a>
      </li>
<%--      Devis--%>

      <% if (!isAdmin) {%>
      <li class="nav-item nav-category">Etapes course</li>
      <li class="nav-item">
        <a class="nav-link" data-bs-toggle="collapse" href="#Devis" aria-expanded="false"
           aria-controls="Devis">
          <i class="menu-icon mdi mdi-card-text-outline"></i>
          <span class="menu-title">Stats</span>
          <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="Devis">
          <ul class="nav flex-column sub-menu">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/listeEtapes">liste des etapes</a></li>
<%--            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/classement">classsement general</a></li>--%>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/classementGeneral">classsement general des equipes</a></li>
          </ul>
        </div>
      </li>



    <%}

      else if (isAdmin) {%>
      <li class="nav-item nav-category">Etapes Admin</li>
      <li class="nav-item">
        <a class="nav-link" data-bs-toggle="collapse" href="#stats" aria-expanded="false"
           aria-controls="stats">
          <i class="menu-icon mdi mdi-card-text-outline"></i>
          <span class="menu-title">Admin</span>
          <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="stats">
          <ul class="nav flex-column sub-menu">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/listeEtapesAdmin">Ajouter temps</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/listeEtapesClassement">classement</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/classementGeneral">classsement des equipes</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/insertionCategorie">generer categorie</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ListePenalite">Penalites</a></li>

          </ul>
        </div>
      </li>
      <%} %>


        <
    </ul>
  </nav>
  

