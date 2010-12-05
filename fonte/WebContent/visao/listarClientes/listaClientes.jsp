<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Clientes</title>
</head>
<body>

	<jsp:include page = "../cabecalho.jsp" flush="true" />

	<display:table name="tabela" class="tabelaJSP">
	  <display:column property="cnpj" title="CNPJ" />
	  <display:column property="nome" title="Razão Social" />
	  <display:column property="endereco" title="Endereço" />
	  <display:column property="contato" title="Contato" />
	  <display:column property="num_operacoes_realizadas" title="Operações Realizadas" />
	  <display:column property="num_operacoes_atuais" title="Operações Atuais" />
	</display:table>
	
	<p>
	<a href="visao/menuPrincipal.jsp" class="linkVoltar"> Menu Principal </a>
	

</body>
</html>