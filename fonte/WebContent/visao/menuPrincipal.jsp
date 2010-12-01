<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="controle.UsuarioLogado" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Principal</title>
</head>
<body>

	<% 
		UsuarioLogado usuario = UsuarioLogado.pegaUsuario();
	%>
	
	<p> Bem-vindo, <%= usuario.getLogin() %>

	<p>
	<a href="inserirCheque/insereChequeForm.jsp"> Inserir Cheque </a>
	
	<p>	
	<a href='../ListaChequesServlet'> Listar Cheques </a>
	
	<p>
	<a href="inserirCliente/insereClienteForm.jsp"> Inserir Cliente </a>
	
	<p>
	<a href="registrarDeposito/registraDepositoForm.jsp"> Registrar Depósito </a>
	

</body>
</html>