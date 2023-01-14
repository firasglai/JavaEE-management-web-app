
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Liste Etudiant Par Groupe</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="../../css/EtuParGrp.css"/>
  <link rel="stylesheet" href="../../css/Dash.css"/>
  <script>
    $(document).ready(function(){
      $('[data-toggle="tooltip"]').tooltip();
    });
  </script>
</head>
<body>
<!-- NAVBAR-->
<nav class="navbar navbar-expand-xl navbar-light bg-light">
  <a href="#" class="navbar-brand"><i class="fa fa-home"></i>Admin<b>Dashboard</b></a>
  <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
    <span class="navbar-toggler-icon"></span>
  </button>
  <!-- Collection of nav links, forms, and other content for toggling -->
  <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
    <div class="navbar-nav">
      <a href="Dashboard.jsp" class="nav-item nav-link active"> <i class="fa fa-home"></i> Home</a>
      <a href="#" class="nav-item nav-link"></a>
      <div class="nav-item dropdown">
        <a href="#" class="nav-link dropdown-toggle active " data-toggle="dropdown"><i class="fa fa-gears"></i>Espace Admin</a>
        <div class="dropdown-menu">
          <a href="<%=request.getContextPath()%>/ListUser" class="dropdown-item">Gestion d'administrateurs</a>
          <a href="<%=request.getContextPath()%>/ListEtu" class="dropdown-item">Gestion d'etudiant</a>
          <a href="<%=request.getContextPath()%>/ListEns" class="dropdown-item">Gestion d'enseignants</a>
          <a href="<%=request.getContextPath()%>/ListMat" class="dropdown-item">Gestion des Matieres </a>
          <a href="<%=request.getContextPath()%>/ListNote" class="dropdown-item">Gestion des Notes </a>
        </div>
      </div>
    </div>

    <div class="navbar-nav ml-auto">
      <a href="#" class="nav-item nav-link notifications"><i class="fa fa-bell-o  "></i><span class="badge" >6</span></a>
      <div class="nav-item dropdown">
        <a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle user-action "><i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i><%=session.getAttribute("username")%> <b class="caret"></b></a>
        <div class="dropdown-menu">
          <a href="#" class="dropdown-item"><i class="fa fa-user-o"></i> Profile</a></a>
          <div class="dropdown-divider"></div>
          <a href="index.jsp" class="dropdown-item"><i class="material-icons">&#xE8AC;</i>Logout</a></a>
        </div>
      </div>
    </div>
  </div>
</nav>
<hr>
<div class="container-xl">
  <div class="table-responsive">
    <div class="table-wrapper">
      <div class="table-title">
        <div class="row">
          <div class="col-sm-4">
            <h2>Liste <b>Etudiant</b></h2>
          </div>
          <div class="col-sm-8">
            <a href="#" class="btn btn-secondary"><i class="material-icons">&#xE24D;</i> <span>Export to Excel</span></a>
          </div>
        </div>
      </div>
      <!-- Table filter  -->
      <div class="table-filter">
        <div class="row">

          <div class="col-sm-12">
            <button type="button" class="btn btn-primary"><i class="fa fa-search"></i></button>
            <div class="filter-group">
              <label>Recherche</label>
              <input type="text" class="form-control">
            </div>


            <span class="filter-icon"><i class="fa fa-filter"></i></span>
          </div>
        </div>
      </div>
      <!-- Table Content  -->
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th>Code Etudiant</th>
          <th>Nom </th>
          <th>Prenom </th>
          <th>Email </th>
          <th>Groupe </th>
          <th>Status Paiments</th>
          <th>Relevé des Notes </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listetugrp}" var="etu">
        <tr>
          <td>${etu.idEtu}</td>
          <td>${etu.nomEtu}</td>
          <td>${etu.prenomEtu}</td>
          <td>${etu.emailEtu}</td>
          <td>${etu.groupNom}</td>
          <td><span class="status text-success">&bull;</span> activé</td>
          <td><a href="<%=request.getContextPath()%>/ListeNoteParId?idetu=${etu.idEtu}" class="view" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE5C8;</i></a></td>
        </tr>
        </c:forEach>
        </tbody>
      </table>

    </div>
  </div>
</div>
</body>
</html>