<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Gestion Notes</title>
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
<!-- DASHBOARD -->
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Liste <b>Notes</b> </h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="#addModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Ajouter une note</span></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>ID note</th>
                    <th>Examen  </th>
                    <th>TP </th>
                    <th>CC</th>
                    <th>Code etudiant</th>
                    <th>Id Matiere</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
           <c:forEach items="${listnote}" var="note">
                    <tr>
                        <td>${note.idnote}</td>
                        <td>${note.note_examen}</td>
                        <td>${note.note_pratique}</td>
                        <td>${note.note_controle}</td>
                        <td>${note.id_etu}</td>
                        <td>${note.code_mat}</td>
                        <td>
                            <a href="#deleteModal${note.idnote}" class="delete" data-toggle="modal" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                        </td>
                    </tr>
                    <!-- Delete Modal vol 2 HTML   -->
                    <div id="deleteModal${note.idnote}" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="SupprimerNote" method="post">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Supprimer Note</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Confirmer la suppression de note : <c:out value='${note.idnote}' /></p>
                                        <input type = "hidden" name = "id" value = "${note.idnote}"/>
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


                        </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
<div id="addModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="AjouterNote" method="post" >
                <div class="modal-header">
                    <h4 class="modal-title">Ajouter un Note</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Note examen </label>
                        <input type="text" name="note_examen" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Note Pratique</label>
                        <input type="text" name="note_pratique" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Note Controle</label>
                        <input type="text" name ="note_controle" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>nom etudiant</label>
                        <select name="id_etu" class="form-control" >
                            <option selected>Selectionner une Spécialité :</option>
                            <c:forEach items="${lisetu}" var="etu">
                                <option>${etu.idEtu}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>ID Matiere</label>
                         <select name="code_mat" class="form-control" >
                        <option selected>Selectionner une Matiére:</option>
                        <c:forEach items="${listmat}" var="mat">
                            <option>${mat.code_mat}</option>
                        </c:forEach>
                    </select>
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

<!-- TEST -->

</body>
</html>
