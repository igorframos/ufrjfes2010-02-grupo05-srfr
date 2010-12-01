<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Depósito</title>
</head>
<body>

	<form action='../../RegistraDepositoServlet' method="post">
	
		<p> Número do Cheque:
		<input type='text' name='numero'>
		
		<p> Data do Desconto (dd/mm/aaaa):
		<input type='text' name='data'>
		
		<p>
		<input type="submit" value='Registrar'>
		<input type="reset" value="Apagar Dados">
	</form>
	
	<a href="../menuPrincipal.jsp"> Home </a>

</body>
</html>