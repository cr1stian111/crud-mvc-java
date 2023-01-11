package com.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.modelo.Persona;

public interface CRUD {
	public List listarPersonas();
	public Persona list(int id);
	public boolean add(Persona persona);
	public boolean edit(Persona persona);
	public boolean remove(int id);
	public void closeConnection(ResultSet rs,Statement stmt, Connection cn);
}
