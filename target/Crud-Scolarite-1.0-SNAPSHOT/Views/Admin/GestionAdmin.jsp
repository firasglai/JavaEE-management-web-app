<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Gestion administrateur</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="../../css/List.css"/>
  <link rel="stylesheet" href="../../css/Dash.css"/>
  <script src ="../../js/Dash.js"></script>

</head>
<body>
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
        </div>
      </div>
    </div>

    <div class="navbar-nav ml-auto">
      <a href="#" class="nav-item nav-link notifications"><i class="fa fa-bell-o  "></i><span class="badge" >6</span></a>
      <div class="nav-item dropdown">
        <a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle user-action "><i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i><b><%=session.getAttribute("username")%></b> <b class="caret"></b></a>
        <div class="dropdown-menu">
          <a href="#" class="dropdown-item"><i class="fa fa-user-o"></i> Profile</a></a>
          <div class="dropdown-divider"></div>
          <a href="index.jsp" class="dropdown-item"><i class="material-icons">&#xE8AC;</i>Logout</a></a>
        </div>
      </div>
    </div>
  </div>
</nav>
<div class="container-xl">
  <div class="table-responsive">
    <div class="table-wrapper">
      <div class="table-title">
        <div class="row">
          <div class="col-sm-6">
            <h2>Gestion <b>Administrateur</b> </h2>
          </div>
          <div class="col-sm-6">
            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Ajouter un utilisateur</span></a>
          </div>
        </div>
      </div>
      <table class="table table-striped table-hover">
        <thead>
        <tr>

          <th>Nom d'utilisateur</th>
          <th>Mot de passe</th>
          <th>Email</th>
          <th>Telephone</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="user">
        <tr>
          <td>${user.uname}</td>
          <td>${user.password}</td>
          <td>${user.email}</td>
          <td>${user.phone}</td>
          <td>
            <a href="#editEmployeeModal${user.id} " class="edit" data-toggle="modal" ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
            <a href="#deleteEmployeeModal${user.id} " class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
          </td>
        </tr>
          <!-- Delete Modal vol 2 HTML -->
          <div id="deleteEmployeeModal${user.id}" class="modal fade">
            <div class="modal-dialog">
              <div class="modal-content">
                <form action="SupprimerUser" method="post">
                  <div class="modal-header">
                    <h4 class="modal-title">Supprimer Utilisateur</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  </div>
                  <div class="modal-body">
                    <p>Confirmer la suppression de l'utilsateur <c:out value='${user.uname}' /></p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                    <input type = "hidden" name = "id" value = "${user.id}"/>
                  </div>
                  <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" value="Delete" >
                  </div>
                </form>
              </div>
            </div>
          </div>
          <!-- Edit Modal HTML vol 2 -->
          <div id="editEmployeeModal${user.id}" class="modal fade">
            <div class="modal-dialog">
              <div class="modal-content">
                <form action="ModifierUser" method="post">
                  <div class="modal-header">
                    <h4 class="modal-title">Modifier un utilisateur</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  </div>
                  <div class="modal-body">
                    <div class="form-group">
                      <label>Nom utilisateur</label>
                      <input type="text" class="form-control" name="username" value="<c:out value='${user.uname}' />" required>
                    </div>
                    <div class="form-group">
                      <label>Mot de passe </label>
                      <input type="text" class="form-control" name="password" value="<c:out value='${user.password}' />"  required>
                    </div>
                    <div class="form-group">
                      <label>Email</label>
                      <input type="email" class="form-control"  name ="email" value="<c:out value='${user.email}' />" required>
                    </div>
                    <div class="form-group">
                      <label>Phone</label>
                      <input type="text" class="form-control"  name="phone" value="<c:out value='${user.phone}' />"  required>

                      <input type = "hidden" name = "id" value = "${user.id}"/>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Save"  >
                  </div>
                </form>
              </div>
            </div>
          </div>
        </c:forEach>



        </tbody>
      </table>

    </div>
  </div>
</div>
<!-- ADD Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="AjouterUser" method="post" >
        <div class="modal-header">
          <h4 class="modal-title">Ajouter un utilisateur</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>Nom d'utilisateur</label>
            <input type="text" name="username" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Mot de passe</label>
            <input type="text" name="password" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" name ="email" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Telephone</label>
            <input type="text" name="phone" class="form-control" required>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-success" value="Add">
        </div>
      </form>
    </div>
  </div>
</div>
<!-- Edit Modal HTML -->

<!-- Delete Modal HTML -->

</body>
</html>

</html>