<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Dep�sito</title>

<script language="JavaScript">
	<!--
	function verificaData()
	{
	    var dataExp = /^(\d{2})\/(\d{2})\/(\d{4})$/;
	    var resultado = document.registraDepositoForm.data.value.match(dataExp);
	    if (resultado == null) {
	    	alert("Formato de Data Inv�lido");
	    	document.registraDepositoForm.data.value = "";
	        return false;
	    } else {
	    	document.registraDepositoForm.submit();
	    }
	}
	//-->
</script>

</head>
<body>
	<jsp:include page = "../cabecalho.jsp" flush="true" />

	<form name="registraDepositoForm" action='../../RegistraDepositoServlet' method="post" onsubmit="return verificaData()">
	
		<p> N�mero do Cheque:
		<input type='text' name='numero'>
		
		<p> Data do Desconto (dd/mm/aaaa):
		<input type='text' name='data'>
		
		<p> Devolvido?
		<input type="checkbox" name="devolvido">
		
		<p>
		<input type="submit" value='Registrar'>
		<input type="reset" value="Apagar Dados">
	</form>
	
	<p>
	<a href="../menuPrincipal.jsp" class="linkVoltar"> Menu Principal </a>

</body>
</html>