<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Gestion Matiére</title>
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
<hr>
<!-- DASHBOARD -->
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Gestion <b>Matière</b> </h2>
                    </div>
                    <div>
                       <!-- <form action="" method="post" >-->
                            <label>Sélectionner une specialité :</label>
                            <select name="specialite" class="form-select" aria-label="Default select example" required>
                                <option selected>Choisir une specialité: </option>
                                <c:forEach items="${listspec}" var="spc">
                                    <option>${spc.nom_spec}</option>
                                </c:forEach>
                            </select>
                            <label> Sélectionner un niveau :</label>
                            <select name="niveau" class="form-select" aria-label="Default select example" required>
                                <option  selected>Sélectionner un niveau </option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                            </select>

                            <a href="<%=request.getContextPath()%>/ListMatParSpec?specialite
" class="btn btn-primary" role="button" data-bs-toggle="button">Toggle link</a>
                     <!--   </form>-->
                    </div>
                    <div class="col-sm-12">
                        <a href="#addModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Ajouter une Matiére</span></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Code Matière</th>
                    <th>Nom Matière</th>
                    <th>Coéf</th>
                    <th>Specialité</th>
                    <th>Niveau</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listmat}" var="mat">
                    <tr>
                        <td>${mat.code_mat}</td>
                        <td>${mat.nom_mat}</td>
                        <td>${mat.coef}</td>
                        <td>${mat.nom_spec}</td>
                        <td>${mat.id_niv}</td>
                        <td>
                            <a href="#editModal${mat.code_mat}" class="edit " data-toggle="modal" ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                            <a href="#deleteModal${mat.code_mat}" class="delete" data-toggle="modal" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                        </td>
                    </tr>
                    <!-- Delete Modal vol 2 HTML   -->
                    <div id="deleteModal${mat.code_mat}" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="SupprimerMat" method="post">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Supprimer Matiére</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Confirmer la suppression du Matiére :<c:out value='${mat.nom_mat}' /></p>
                                        <input type = "hidden" name = "id" value = "${mat.code_mat}"/>
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
                    <div id="editModal${mat.code_mat}" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="ModifierMat" method="post">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Modifier une matiére</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label>Nom Matiere</label>
                                            <input type="text" class="form-control" name="nom_mat" value="<c:out value='${mat.nom_mat}' />" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Coef</label>
                                            <input type="number" class="form-control" name="coef_mat" value="<c:out value='${mat.coef}' />"  required>
                                        </div>
                                        <div class="form-group">
                                            <label>Specialité</label>
                                            <select name="spec" class="form-control" >
                                                <option selected>Selectionner une Spécialité :</option>
                                                <c:forEach items="${listspec}" var="spc">
                                                    <option>${spc.nom_spec}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Niveau</label>
                                            <select name="niv" class="form-control" >
                                                <option selected>Selectionner un Niveau :</option>
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </select>

                                            <input type = "hidden" name = "id" value = "${mat.code_mat}"/>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                        <input type="submit" class="btn btn-info" value="Save">
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
<!--ADD MODAL -->
<div id="addModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="AjouterMat" method="post" >
                <div class="modal-header">
                    <h4 class="modal-title">Ajouter une Matiere</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Nom matiére</label>
                        <input type="text" name="nom_mat" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Coeffecient</label>
                        <input type="number" name="coef_mat" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Specialité</label>
                        <select name="spec" class="form-control" >
                            <option selected>Selectionner une Spécialité :</option>
                            <c:forEach items="${listspec}" var="spc">
                                <option>${spc.nom_spec}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Niveau d'etudes</label>
                        <select name="niv" class="form-control" >
                            <option selected>Selectionner un niveau :</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
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
</body>
</html>
