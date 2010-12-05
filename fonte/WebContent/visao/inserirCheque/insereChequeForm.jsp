<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insere Cheque</title>

<script language="JavaScript">
	<!--
	function verificaData()
	{
	    var data = /^(\d{2})\/(\d{2})\/(\d{4})$/;
	    var resultado = document.form1.text1.value.match(data);
	    if (resultado == null) {
	    	document.insereChequeForm.dataVencimento.value = "";
	        return false;
	    } else {
	    	return true;
	    }
	}
	function verificaDecimal()
	{
	    var decimal = /^(\d*)\.(\d*)$/;
	    var resultadoBruto = document.insereChequeForm.valorBruto.value.match(decimal);
	    var resultadoDescontado = document.insereChequeForm.valorDescontado.value.match(decimal);
	    if (resultadoBruto == null || resultadoDescontado == null) {
	        return false;
	    } else {
	        return true;
	    }
	}
	
	function verificaForm()
	{
		if( !verificaDecimal() ) {
			alert("O formato das entradas de valor deve ser respeitado");
			return false;
		}
		
		if( !verificaData() ) {
			alert("Data no formato inválido");
			return false;
		}
		
		if( verificaDecimal() && verificaData() ) {
			document.insereChequeForm.submit();
		}
	}
	//-->
</script>

</head>
<body>

<jsp:include page = "../cabecalho.jsp" flush="true" />

<h3>Inserir Cheque</h3>
<h4>Dados:</h4>

<form name="insereChequeForm" action='../../InsereChequeServlet' method='POST' onsubmit="return verificaForm()">
	<p> Número:
	<input type='text' name='numero' id="caixaEntrada">
	<p> CPF Cheque:
	<input type='text' name='cpf'>
	<p> CNPJ Empresa:
	<input type='text' name='cnpj'>
	<p> Valor Bruto (XX.YY):
	<input type='text' name='valorBruto'>
	<p> Valor Descontado (XX.YY):
	<input type='text' name='valorDescontado'>
	<p> Data de Vencimento (dd/mm/aaaa):
	<input type='text' name='dataVencimento'>
	<p>
	<input type='submit' value='Inserir'>
</form>

<a href="../menuPrincipal.jsp" class="linkVoltar">Menu Principal</a>

</body>
</html>