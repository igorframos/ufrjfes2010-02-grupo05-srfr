<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insere Cheque</title>
</head>
<body>

<h3>Inserir Cheque</h3>
<br><br>
<h4>Dados:</h4>

<form action='InsereChequeServlet' method='POST'>
	<p> N�mero:
	<input type='text' name='numero'>
	<p> CNPJ:
	<input type='text' name='cnpj'>
	<p>
	<input type='submit' value='Inserir'>
</form>

</body>
</html>