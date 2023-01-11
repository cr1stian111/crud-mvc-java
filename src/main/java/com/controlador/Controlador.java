package com.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modelo.Persona;
import com.modeloDao.PersonaDao;

@WebServlet("/controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String vista_listar="vistas/list.jsp";
    String vista_add="vistas/add.jsp";
    String vista_edit="vistas/edit.jsp";
    String vista_search="vistas/search.jsp";
    Persona persona = new Persona();
    PersonaDao dao = new PersonaDao();
    
    public Controlador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceso="";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=vista_listar;            
        }else if(action.equalsIgnoreCase("add")) {
        	acceso=vista_add;	
        }else if(action.equalsIgnoreCase("Agregar")){
        	String dni = request.getParameter("txtDni");
        	String nom = request.getParameter("txtNom");
        	if(dni.equals("") || dni.equals("null") || dni==null) {
        		request.setAttribute("msje", "dniVacio");
        		acceso=vista_add;
        	}
        	else if(nom.equals("") || nom==null || nom.equals("null")) {
        		request.setAttribute("msje", "nombreVacio");
        		acceso=vista_add;
        	}else if((dni.equals("") || dni.equals("null") || dni==null) && (nom.equals("") || nom==null || nom.equals("null"))){
        		request.setAttribute("msje", "todoVacio");
        		acceso=vista_add;
        	}else{
            	persona.setDni(dni);
            	persona.setNom(nom);
            	dao.add(persona);
            	acceso=vista_listar;
        	} 
        }else if(action.equalsIgnoreCase("editar")) {
        	int id= Integer.parseInt(request.getParameter("id"));
        	request.setAttribute("id", id);
        	acceso=vista_edit;
        	
        }else if(action.equalsIgnoreCase("Actualizar")) {
        	String dni = request.getParameter("txtDni");
        	String nom = request.getParameter("txtNom");
        	int id = Integer.parseInt(request.getParameter("txtId"));
        	if(dni.equals("") || dni.equals("null") || dni==null) {
        		//msje
        		request.setAttribute("msje", "dniVacio");
        		request.setAttribute("id", id);
        		acceso=vista_edit;
        	}
        	else if(nom.equals("") || nom==null || nom.equals("null")) {
        		//msje
        		request.setAttribute("msje", "nombreVacio");
        		request.setAttribute("id", id);
        		acceso=vista_edit;
        	}else if((dni.equals("") || dni.equals("null") || dni==null) && (nom.equals("") || nom==null || nom.equals("null"))){
        		request.setAttribute("msje", "todoVacio");
        		request.setAttribute("id", id);
        		acceso=vista_edit;
        	}else{
            	persona.setId(id);
            	persona.setDni(dni);
            	persona.setNom(nom);
            	dao.edit(persona);
            	acceso=vista_listar;
        	}	
        }else if(action.equalsIgnoreCase("eliminar")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	request.setAttribute("id", id);
        	dao.remove(id);
        	acceso=vista_listar;
        }else if(action.equalsIgnoreCase("Buscar")) {
        	String  txtBuscar = request.getParameter("txtBuscar");
        	if(txtBuscar.equals("")) {
        		acceso=vista_listar;
        	}else {
        		List<Persona> lista = dao.search(txtBuscar);
        		request.setAttribute("lista", lista);
        		acceso=vista_search;
        	}
        } 
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(acceso);
        requestDispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
