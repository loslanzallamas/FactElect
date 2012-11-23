<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado de validación de comprobantes</title>
</head>
<body>
	<div
		style="border: 1px solid black; padding: 15px; background-color: aqua;">
		<h3>Comprobantes</h3>
		<table border="2">
			<c:forEach items="${fecaeResponses}" var="tipoComp">
				<table border="1">
					<c:forEach items="${fecaeResponse.feDetResp}" var="comp">
						<tr>
							<th>CONCEPTO</th>
							<th>TIPO DOC</th>
							<th>COMP DESDE</th>
							<th>COMP HASTA</th>
							<th>FECHA</th>
							<th>RESULTADO</th>
							<th>FECHA</th>
							<th>CAE</th>
							<th>CAE VTO</th>
							<th>OBSERVACIONES</th>
						</tr>
						<tr>
							<td>${comp.concepto}</td>
							<td>${comp.docTipo}</td>
							<td>${comp.cbteDesde}</td>
							<td>${comp.cbteHasta}</td>
							<td>${comp.cbteFch}</td>
							<td>${comp.resultado}</td>
							<td>${comp.cbteFch}</td>
							<td>${comp.CAE}</td>
							<td>${comp.CAEFchVto}</td>
							<td><table border="1">
									<c:forEach items="${comp.observaciones}" var="obs">
										<tr>
											<td>${obs.msg}</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:forEach>
		</table>

		<br>
		<h5>Último comprobante autorizado</h5>
		<table border="1">
			<tr>
				<th style="font-size: smaller;">Comp. Tipo</th>
				<th style="font-size: smaller;">Pto. Vta.</th>
				<th style="font-size: smaller;">Comp. Nro</th>
				<th style="font-size: smaller;">CAE</th>
				<th style="font-size: smaller;">Fecha vto</th>
			</tr>
			<tr>
				<th>${feRecuperaLastCbteResponse.cbteTipo}</th>
				<th>${feRecuperaLastCbteResponse.ptoVta}</th>
				<th>${feRecuperaLastCbteResponse.cbteNro}</th>
				<th>${feCompConsResponse.codAutorizacion}</th>
				<th>${feCompConsResponse.fchVto}</th>
			</tr>
		</table>
		<br>
		<h5>Log del proceso</h5>
		<c:forEach items="${logSistema}" var="logs">
			<tr>
				<th>${logSistema}</th>
			</tr>
		</c:forEach>
		<br>
		<h5>Errores</h5>
		<c:forEach items="${fecaeResponse.errors}" var="errores">
			<tr>
				<td>${errores.msg}</td>
			</tr>
		</c:forEach>
		<br>
		<h5>Eventos</h5>
		<c:forEach items="${fecaeResponse.events}" var="eventos">
			<tr>
				<td>${eventos.msg}</td>
			</tr>
		</c:forEach>
	</div>
	<a href="\\fpm-2k3srvr\Tractoagro\Tracto\modelo.pdf">Modelo de
		documento para impresión</a>
</body>
</html>