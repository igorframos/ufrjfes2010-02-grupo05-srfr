<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerador de Relatórios por Cliente</title>
</head>
<body>

	<form action = "../../GeraRelatorioClienteServlet" method="post">
	
		<p> CNPJ:
		<input type="text" name="cnpj">
		
		<p> Mostrar só devolvidos:
		<input type="checkbox" name="devolvidos" value="Devolvidos">
		
		<p>
		<input type="submit" value="Gerar Relatório">
	
	</form>
	
	<a href = "../menuPrincipal.jsp">Menu Principal</a>

</body>
</html>