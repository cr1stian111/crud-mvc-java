<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<title>Agregar</title>
</head>
<body>
	<%String msje = (String) request.getAttribute("msje");
	if(msje != null){
	  if(msje.equals("dniVacio")){ %>
	  	<div class="alert alert-warning">
		  <strong>¡Alerta!</strong> Ingrese un dni valido por favor.
		</div>
	  <%}else if(msje.equals("nombreVacio")){ %>
	  <div class="alert alert-warning">
		  <strong>¡Alerta!</strong> Ingrese un nombre valido por favor.
		</div>
	  <%}else if(msje.equals("todoVacio")){%>
	  	 <div class="alert alert-warning">
		  <strong>¡Alerta!</strong> Ingrese un dni y nombre valido por favor.
		</div>
	  <%}
	 }%>
	    <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Persona</h1>
                <form action="controlador">
                    DNI:<br>
                    <input class="form-control" type="text" name="txtDni" maxlength="10"><br>
                    Nombres: <br>
                    <input class="form-control" type="text" name="txtNom" maxlength="50"><br>
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
                    <a href="controlador?accion=listar">Regresar</a>
                </form>
            </div>

        </div>
</body>
</html>