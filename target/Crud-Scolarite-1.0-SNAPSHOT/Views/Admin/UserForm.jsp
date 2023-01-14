<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ajout utilisateur</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #eeeeee;
        }
        .form-inline {
            display: inline-block;
        }
        .navbar-header.col {
            padding: 0 !important;
        }
        .navbar {
            background: #fff;
            padding-left: 16px;
            padding-right: 16px;
            border-bottom: 1px solid #d6d6d6;
            box-shadow: 0 0 4px rgba(0,0,0,.1);
        }
        .nav-link img {
            border-radius: 50%;
            width: 36px;
            height: 36px;
            margin: -8px 0;
            float: left;
            margin-right: 10px;
        }
        .navbar .navbar-brand {
            color: #555;
            padding-left: 0;
            padding-right: 50px;
            font-family: 'Merienda One', sans-serif;
        }
        .navbar .navbar-brand i {
            font-size: 20px;
            margin-right: 5px;
        }
        .search-box {
            position: relative;
        }
        .search-box input {
            box-shadow: none;
            padding-right: 35px;
            border-radius: 3px !important;
        }
        .search-box .input-group-addon {
            min-width: 35px;
            border: none;
            background: transparent;
            position: absolute;
            right: 0;
            z-index: 9;
            padding: 7px;
            height: 100%;
        }
        .search-box i {
            color: #a0a5b1;
            font-size: 19px;
        }
        .navbar .nav-item i {
            font-size: 18px;
        }
        .navbar .dropdown-item i {
            font-size: 16px;
            min-width: 22px;
        }
        .navbar .nav-item.open > a {
            background: none !important;
        }
        .navbar .dropdown-menu {
            border-radius: 1px;
            border-color: #e5e5e5;
            box-shadow: 0 2px 8px rgba(0,0,0,.05);
        }
        .navbar .dropdown-menu a {
            color: #777;
            padding: 8px 20px;
            line-height: normal;
        }
        .navbar .dropdown-menu a:hover, .navbar .dropdown-menu a:active {
            color: #333;
        }
        .navbar .dropdown-item .material-icons {
            font-size: 21px;
            line-height: 16px;
            vertical-align: middle;
            margin-top: -2px;
        }
        .navbar .badge {
            color: #fff;
            background: #f44336;
            font-size: 11px;
            border-radius: 20px;
            position: absolute;
            min-width: 10px;
            padding: 4px 6px 0;
            min-height: 18px;
            top: 5px;
        }
        .navbar a.notifications, .navbar a.messages {
            position: relative;
            margin-right: 10px;
        }
        .navbar a.messages {
            margin-right: 20px;
        }
        .navbar a.notifications .badge {
            margin-left: -8px;
        }
        .navbar a.messages .badge {
            margin-left: -4px;
        }
        .navbar .active a, .navbar .active a:hover, .navbar .active a:focus {
            background: transparent !important;
        }
        @media (min-width: 1200px){
            .form-inline .input-group {
                width: 300px;
                margin-left: 30px;
            }
        }
        @media (max-width: 1199px){
            .form-inline {
                display: block;
                margin-bottom: 10px;
            }
            .input-group {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-xl navbar-light bg-light">
    <a href="#" class="navbar-brand"><i class="fa fa-cube"></i>Admin<b>Dashboard</b></a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Collection of nav links, forms, and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
        <div class="navbar-nav">
            <a href="<%=request.getContextPath()%>/ListEns" class="nav-item nav-link active"> <i class="fa fa-home"></i> Liste </a>

            </div>
        </div>

        <div class="navbar-nav ml-auto">
            <a href="#" class="nav-item nav-link notifications"><i class="fa fa-bell-o  "></i><span class="badge" >6</span></a>
            <div class="nav-item dropdown">
                <a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle user-action "><i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i><%=session.getAttribute("username")%> <b class="caret"></b></a>
                <div class="dropdown-menu">
                    <a href="#" class="dropdown-item"><i class="fa fa-user-o"></i> Profile</a></a>
                    <a href="#" class="dropdown-item"><i class="fa fa-sliders"></i> Settings</a></a>
                    <div class="dropdown-divider"></div>
                    <a href="../../index.jsp" class="dropdown-item"><i class="material-icons">&#xE8AC;</i>Logout</a></a>
                </div>
            </div>
        </div>
    </div>
</nav>

<div>
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
</body>
</html>
