<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="./utils/style.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<script src="./utils/script.js"></script>
	<title>Censo 21 | Censo Argentina 2021</title>
</head>

<body>
	<div class="container-xl main">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-12">
							<h2>
								<b>Censo 21 </b>Listado de Personas
							</h2>
						</div>
						<div class="col-sm-12">
							<form action="person.jsp" method="GET">
								<input type="submit" class="btn btn-success" value="Agregar">
							</form>
						</div>
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Tipo de Documento</th>
							<th>Numero de Documento</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Fecha de Nacimiento</th>
							<th>Sexo</th>
							<th>Direccion</th>
							<th>Telefono</th>
							<th>Ocupacion</th>
							<th>Ingresos Mensuales</th>
							<th>Modificar</th>
							<th>Borrar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="person" items="${listPerson}">
							<tr>
								<td>
									<c:out value="${person.documentType}" />
								</td>
								<td>
									<c:out value="${person.documentNumber}" />
								</td>
								<td>
									<c:out value="${person.firstName}" />
								</td>
								<td>
									<c:out value="${person.lastName}" />
								</td>
								<td>
									<c:out value="${person.dateBirth}" />
								</td>
								<td>
									<c:out value="${person.sex}" />
								</td>
								<td>
									<c:out value="${person.address}" />
								</td>
								<td>
									<c:out value="${person.phone}" />
								</td>
								<td>
									<c:out value="${person.occupation}" />
								</td>
								<td>
									<c:out value="${person.monthlyIncome}" />
								</td>
								<td>
									<form action="PersonServlet" method="POST">
										<input type="hidden" name="requestAction" value="getOne" />
										<input type="hidden" name="documentType"
											value="<c:out value='${person.documentType}'/>" />
										<input type="hidden" name="documentNumber"
											value="<c:out value='${person.documentNumber}'/>" />
										<input type="submit" class="btn btn-warning material-icons" value="&#xE254;">
									</form>
								</td>
								<td>
									<form action="PersonServlet" method="POST">
										<input type="hidden" name="requestAction" value="delete" />
										<input type="hidden" name="documentType"
											value="<c:out value='${person.documentType}'/>" />
										<input type="hidden" name="documentNumber"
											value="<c:out value='${person.documentNumber}'/>" />
										<input type="submit" class="btn btn-danger material-icons" value="&#xE872;">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-ms-6" style="text-align: center;">
					<table class="table table-striped table-hover">
						<thead>
							<h2>Informes</h2>
						</thead>
					</table>
					<tbody>
						<div class="wrapper-report">
							<form action="PersonServlet" method="POST">
								<input type="hidden" name="requestAction" value="reportForLastName" />
								<input type="submit" class="btn btn-primary" value="Personas Ordenados por Apellido">
							</form>
							<form action="PersonServlet" method="POST">
								<input type="hidden" name="requestAction" value="reportAdultList" />
								<input type="submit" class="btn btn-primary" value="Personas Mayores de 18 a&ntilde;os">
							</form>
							<form action="PersonServlet" method="POST">
								<input type="hidden" name="requestAction" value="reportPovertyLine" />
								<input type="submit" class="btn btn-primary" value="Personas Debajo de la Linea de Pobreza">
							</form>
							<form action="PersonServlet" method="POST">
								<input type="hidden" name="requestAction" value="reportCountByGender" />
								<input type="submit" class="btn btn-primary" value="Totales por Sexo">
							</form>
						</div>
					</tbody>
				</div>
			</div>
		</div>
	</div>
</body>

</html>