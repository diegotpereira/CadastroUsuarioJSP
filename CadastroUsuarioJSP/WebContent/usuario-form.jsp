<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listar"
					class="nav-link">Usuarios</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${usuario != null}">
					<form action="atualizar" method="post">
				</c:if>
				<c:if test="${usuario == null}">
					<form action="inserir" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${usuario != null}">
            			Editar usuario
            		</c:if>
						<c:if test="${usuario == null}">
            			Adicionar Novo usuario
            		</c:if>
					</h2>
				</caption>

				<c:if test="${usuario != null}">
					<input type="hidden" name="id" value="<c:out value='${usuario.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nome</label> <input type="text"
						value="<c:out value='${usuario.nome}' />" class="form-control"
						name="nome" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${usuario.email}' />" class="form-control"
						name="email">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Telefone</label> <input type="text"
						value="<c:out value='${usuario.telefone}' />" class="form-control"
						name="telefone">
				</fieldset>

				<fieldset class="form-group">
					<label>Nacionalidade</label> <input type="text"
						value="<c:out value='${usuario.nacionalidade}' />" class="form-control"
						name="nacionalidade">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
