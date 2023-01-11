<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.modelo.Persona"%>
<%@page import="java.util.List"%>
<%@page import="com.modeloDao.PersonaDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<title>Editar</title>
</head>
<body>
	<%String msje = (String) request.getAttribute("msje");
	  int id;
	if(msje != null){
	  //si hay msje es porque hay error de ing asi que hay que recibir  como int
	  //String idd= (String)request.getAttribute("id");
	  id=(int)request.getAttribute("id");
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
	}else{ //caso que no hay msje por tanto esta ok y viene de la lista como string
		id = (int)request.getAttribute("id");
	} %>

	 <div class="container">
            <div class="col-lg-6">
              <%
              PersonaDao dao=new PersonaDao();
              //int id=Integer.parseInt((String)request.getAttribute("id"));
              //int id=(int)request.getAttribute("id");
              Persona p=(Persona)dao.list(id);
              %>
            <h1>Modificar Persona</h1>
            <form action="controlador">
                DNI:<br>
                <input class="form-control" type="text" name="txtDni" value="<%= p.getDni()%>"><br>
                Nombres: <br>
                <input class="form-control" type="text" name="txtNom" value="<%= p.getNom()%>"><br>
                
                <input type="hidden" name="txtId" value="<%= p.getId()%>">
                <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 
                <a href="controlador?accion=listar">Regresar</a>
            </form>
          </div>
          
        </div>
	
</body>
</html>