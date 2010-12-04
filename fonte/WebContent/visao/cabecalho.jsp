<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cabeçalho Factoring 2010</title>
</head>
<body>

	<link rel=stylesheet type="text/css" href="estiloDefault.css">  

	<% 
		String login = "visitante";
		Cookie biscoitos[] = request.getCookies();
		
		if(biscoitos != null) {
			for(Cookie c : biscoitos) {
				if( c.getName().equalsIgnoreCase("nomeUsuario")) {
					login = "Logado como: " + c.getValue();
				}
			}
		}
	%>
	
	<p> <%= login  %>

</body>
</html>