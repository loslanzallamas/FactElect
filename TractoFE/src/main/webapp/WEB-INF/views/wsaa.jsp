<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WSAA</title>
</head>
<body>
	<h1>AFIP Web Service de Autenticación y Autorización</h1>
	<br>
	<table border="1">
	<tr><td style="background-color:#00FF00"><b>Token:</b></td></tr> 
	<tr><td>${token}</td></tr>
	<tr><td style="background-color:#00FF00"><b>Sign:</b></td></tr>
	<tr><td>${sign}</td></tr>
	<tr><td style="background-color:#00FF00"><b>Respuesta:</b></td></tr>
	<tr><td>${respuesta}</td></tr>
	</table>
	<br>
	<br>
	<h3>Último comprobante autorizado</h3>
	<table border="1">
		<tr>
			<th>Comp. Tipo</th>
			<th>Pto. Vta.</th>
			<th>Comp. Nro</th>
		</tr>
		<tr>
			<th>${feRecuperaLastCbteResponse.cbteTipo}</th>
			<th>${feRecuperaLastCbteResponse.ptoVta}</th>
			<th>${feRecuperaLastCbteResponse.cbteNro}</th>
		</tr>
	</table>
</body>
</html>