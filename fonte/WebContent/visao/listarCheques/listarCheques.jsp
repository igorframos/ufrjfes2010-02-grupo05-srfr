<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Cheques</title>
</head>
<body>

	<display:table name="tabela">
	  <display:column property="numero" title="N�mero" />
	  <display:column property="data_vencimento" title="Data Vencimento" />
	  <display:column property="CPF" />
	  <display:column property="CNPJ" />
	</display:table>

</body>
</html>