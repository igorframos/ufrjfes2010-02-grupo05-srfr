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
	
	<table id="tableMenu" >
	
		<tr>	
			<th>
			Cheque			
			</th>
		</tr>
	
		<tr>
			<td>
			<a href="inserirCheque/insereChequeForm.jsp" class="menuLink" > Inserir Cheque </a>
			</td>
		</tr>
		
		<tr>
			<td>
			<a href="registrarDeposito/registraDepositoForm.jsp" class="menuLink" > Registrar Depósito </a>
			</td>
		</tr>
	
		<tr>
			<th>
			Cliente
			</th>
		</tr>
		
		<tr>
			<td>
			<a href="inserirCliente/insereClienteForm.jsp" class="menuLink" > Inserir Cliente </a>
			</td>
		</tr>
		
		<tr>
			<td>
			<a href="../ListaClientesServlet" class="menuLink" > Listar Clientes </a>
			</td>
		</tr>
		
		<tr>
			<th>
			Relatórios
			</th>
		</tr>
		
		<tr>
			<td>
			<a href="gerarRelatorioCliente/geraRelatorioClienteForm.jsp" class="menuLink" > Por Cliente </a>
			</td>
		</tr>
		
		<tr>
			<td>
			<a href="gerarRelatorioCpf/geraRelatorioCpfForm.jsp" class="menuLink" > Por CPF </a>
			</td>
		</tr>
		
		<tr>
			<td>
			<a href="../GeraRelatorioDevolvidosServlet" class="menuLink" > Cheques Devolvidos </a>
			</td>
		</tr>
		
		<tr>
			<td>
			<a href="../ListaChequesServlet" class="menuLink" > Todos os Cheques </a>
			</td>
		</tr>
	
	</table>

</body>
</html>