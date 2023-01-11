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
<title>Listar</title>
</head>
<body>
	<div class="container">
            <h1>Personas</h1>
            <div class="container">
            	<div class="navbar">
            		<a class="btn btn-success navbar-brand" href="controlador?accion=add" style="margin-right:38em; margin-left:0em;">Agregar Nuevo</a>
            		<form class="form-inline">
            			<input type="search" name="txtBuscar" class="form-control" >
            			<input type="submit" name="accion" value="Buscar" class="btn btn-outline-success">
            		</form>
            	</div>
            </div>
          
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">DNI</th>
                        <th class="text-center">NOMBRES</th>
                        <th class="text-center">ACCIONES</th>
                    </tr>
                </thead>
                <%
                    PersonaDao dao=new PersonaDao();
                    List<Persona> list = dao.listarPersonas();
                    Iterator<Persona>iter=list.iterator();
                    Persona per=null;
                    while(iter.hasNext()){
                        per=iter.next();
                    
                %>
                <tbody>
                    <tr>
                        <td class="text-center"><%= per.getId()%></td>
                        <td class="text-center"><%= per.getDni()%></td>
                        <td><%= per.getNom()%></td>
                        <td class="text-center">
                            <a class="btn btn-primary" href="controlador?accion=editar&id=<%= per.getId()%>">Editar</a>
                            <a class="btn btn-danger" onclick="return confirm('Estás seguro que deseas eliminar el registro?');" href="controlador?accion=eliminar&id=<%= per.getId()%>">Eliminar</a>
                        </td>
                    </tr>
                <%}%>
                </tbody>
            </table>

        </div>
</body>
</html>