<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório por CPF</title>
</head>
<body>

	<p>
	<h3>Gerar Relatório por CPF</h3>
	<br><br>
	
	<form action='../../GeraRelatorioCpfServlet' method='POST'>
		<p> CPF:
		<input type='text' name='cpf'>
		<p> Mostrar só devolvidos?
		<input type="checkbox" name='devolvido'>
		<p>
		<input type="submit" value="Gerar Relatório">
	</form>
	
	<a href="../menuPrincipal.jsp">Home</a>

</body>
</html>