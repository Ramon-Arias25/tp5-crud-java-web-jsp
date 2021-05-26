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
			<title>Person | Censo Argentina 2021</title>
		</head>

		<body>
			<div class="container-xl main">
				<div class="table-responsive">
					<div class="table-wrapper">
						<div class="table-title">
							<div class="row">
								<div class="col-sm-12">
									<c:if test="${person != null}">
										<h2><b>Censo 21 </b>Actualizar datos Persona</h2>
									</c:if>
									<c:if test="${person == null}">
										<h2><b>Censo 21 </b>Agregar Persona</h2>
									</c:if>
								</div>
								<div class="col-sm-12">
									<form action="PersonServlet" method="post">
											<input type="hidden" name="requestAction" value="getAll" />
											<input type="submit" class="btn btn-danger" value="Volver">
									</form>
								</div>
							</div>
						</div>

						<form action="PersonServlet" method="post">
							<div class="from-body">
								<c:if test="${person != null}">
									<input type="hidden" name="requestAction" value="update" />
								</c:if>
								<c:if test="${person == null}">
									<input type="hidden" name="requestAction" value="create" />
								</c:if>
								<div class="row">
									<div class="col-sm-12 wrapper-person">
										<div class="form-group">
											<label class="form-group-label">Tipo de Documento</label>
											<input type="text" class="form-control" name="documentType"
												placeholder="DNI" pattern="[A-Z]{3}"
												value="<c:out value='${person.documentType}' />"
												required />
											<p>3 Letras en mayusculas (*)</p>
										</div>
										<div class="form-group">
											<label class="form-group-label">Numero de Documento</label>
											<input type="text" class="form-control" name="documentNumber"
												placeholder="00000000" pattern="[0-9]{1-9}"
												value="<c:out value='${person.documentNumber}' />"
												required />
											<p>Solo numeros (*)</p>
											</div>
											<div class="form-group">
												<label class="form-group-label">Primer Nombre</label>
												<input type="text" class="form-control" name="firstName"
												value="<c:out value='${person.firstName}' />"
												placeholder="NOMBRE" required />
											<p>(*)</p>
											</div>
											<div class="form-group">
												<label class="form-group-label">Apellido</label>
												<input type="text" class="form-control" name="lastName"
												value="<c:out value='${person.lastName}' />"
												placeholder="APELLIDO" required />
											<p>(*)</p>
											</div>
										</div>
										<div class="col-sm-10 wrapper-person">
											<div class="form-group">
												<label class="form-group-label">Fecha de Nacimiento</label>
												<input type="text" class="form-control" name="dateBirth"
												value="<c:out value='${person.dateBirth}' />"
												placeholder="YYYY-MM-DD" required />
											<p>Formato YYYY-MM-DD (*)</p>
										</div>
										<div class="form-group">
											<label class="form-group-label">Sexo</label>
											<input type="text" class="form-control" name="sex"
											value="<c:out value='${person.sex}' />"
											placeholder="M" required />
											<p>Una letra F &oacute; M (*)</p>
										</div>
										<div class="form-group">
											<label class="form-group-label">Direccion</label>
											<input type="text" class="form-control" name="address"
											value="<c:out value='${person.address}' />"
											placeholder="Direccion" />
											<p>(-)</p>
										</div>
										<div class="form-group">
											<label class="form-group-label">Numero de telefono</label>
											<input type="text" class="form-control" name="phone"
											value="<c:out value='${person.phone}' />"
											placeholder="01112341234" pattern="[0-9]{1-12}" />
											<p>Solo numeros (-)</p>
										</div>
									</div>
									<div class="col-sm-10 wrapper-person">
										<div class="form-group">
											<label class="form-group-label">Ocupacion</label>
											<input type="text" class="form-control" name="occupation"
											value="<c:out value='${person.occupation}' />"
											placeholder="Ocupacion" />
											<p>(-)</p> 
										</div>
										<div class="form-group">
											<label class="form-group-label">Ingreso Mensual</label>
											<input type="text" class="form-control" name="monthlyIncome"
											value="<c:out value='${person.monthlyIncome}' />"
											placeholder="0000.00" pattern="[0-9]{8,2}" required />
											<p>Solo numeros (*)</p>
										</div>
									</div>
								</div>
							</div>
							<p class="table-responsive-p"> (*) Campos Obligatorios | (-) Campos Opcional</p>
							<div class="footer">
								<input type="submit" class="btn btn-success" value="Enviar!">
							</div>
						</form>
					</div>
				</div>
			</div>
		</body>

		</html>