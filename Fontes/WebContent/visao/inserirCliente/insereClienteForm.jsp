<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserir Cliente</title>
</head>
<body>

	<jsp:include page = "../cabecalho.jsp" flush="true" />

	<% 
		String msgErro = "";
		
		Cookie biscoitos[] = request.getCookies();
		
		if(biscoitos != null) {
			for(Cookie c : biscoitos) {
				if( c.getName().equals("mensagemErroInsereCliente") ) {
					msgErro = c.getValue();
				}
			}
		}
	%>
	
	<p>
	<h3>Inserir Cliente</h3>
	<br><br>
	
	<h4>Dados:</h4>
	
	<p>
	<%= msgErro %>
	
	<form action='../../InsereClienteServlet' method='POST'>
		<p> Raz�o Social:
		<input type='text' name='nome'>
		<p> CNPJ:
		<input type='text' name='cnpj'>
		<p> Endere�o:
		<input type='text' name='endereco'>
		<p> Contato:
		<input type='text' name='contato'>
		<p>
		<input type='submit' value='Inserir'>
	</form>
	
	<p>
	<a href="../menuPrincipal.jsp" class="linkVoltar">Menu Principal</a>

</body>
</html>