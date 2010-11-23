<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insere Cheque</title>
</head>
<body>

	<h3> Cheque inserido com sucesso! </h3>
	
	<h4>CNPJ: <%= request.getAttribute("cnpjEmpresa")%></h4>
	
	<input type="submit" value="Voltar" onClick="this.form.action='index.jsp'">

</body>
</html>