<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório de Cheques por Cliente</title>
</head>
<body>

	<display:table name="cheques">
	  <display:column property="numero" title="Número" />
	  <display:column property="CPF" />
	  <display:column property="CNPJ" title="CNPJ do Cliente" />
	  <display:column property="data_vencimento" title="Data Vencimento" />
	  <display:column property="data_desconto" title="Data Desconto" />
	  <display:column property="valor_bruto" title="Valor Bruto" />
	  <display:column property="valor_descontado" title="Valor Descontado" />
	  <display:column property="taxa_efetiva" title="Taxa Efetiva" />
	  <display:column property="taxa_desconto" title="Taxa Desconto" />
	  <display:column property="receita" title="Receita" />
	  <display:column property="vencido" title="Vencido?" />
	  <display:column property="devolvido" title="Devolvido?" />
	</display:table>

	<a href = '../menuPrincipal.jsp'>Menu Principal</a>

</body>
</html>