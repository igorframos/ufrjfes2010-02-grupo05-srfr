<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Principal</title>
</head>
<body>

	<jsp:include page = "cabecalho.jsp" flush="true" />

	<p>
	<a href="inserirCheque/insereChequeForm.jsp"> Inserir Cheque </a>
	
	<p>
	<a href="inserirCliente/insereClienteForm.jsp"> Inserir Cliente </a>
	
	<p>
	<a href="registrarDeposito/registraDepositoForm.jsp"> Registrar Depósito </a>
	
	<p>
	<a href="relatorios.jsp"> Gerar Relatórios </a>

</body>
</html>