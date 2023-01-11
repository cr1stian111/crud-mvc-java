package com.modeloDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.Conexion;
import com.interfaces.CRUD;
import com.modelo.Persona;

//logica de negocios, manipulacion bd
public class PersonaDao implements CRUD{
	Conexion laConexion=new Conexion(); 
	Connection connection; 
	PreparedStatement preparedStatement;
	ResultSet resultset;
	Persona persona=new Persona();
	
	@Override
	public List listarPersonas() {
       ArrayList<Persona>list=new ArrayList<>();
        String sql="select * from persona order by id";
        try {
        	Connection connection=laConexion.getConnection();
        	PreparedStatement preparedStatement=connection.prepareStatement(sql);
        	ResultSet resultset=preparedStatement.executeQuery();
            while(resultset.next()){
                Persona per=new Persona();
                per.setId(resultset.getInt("id"));
                per.setDni(resultset.getString("dni"));
                per.setNom(resultset.getString("nombres"));
                list.add(per);
            }
            connection.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return list;
	}

	@Override
	public Persona list(int id) {
        String sql="select * from persona where Id="+id;
        try {
            connection=laConexion.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultset=preparedStatement.executeQuery();
            while(resultset.next()){                
                persona.setId(resultset.getInt("id"));
                persona.setDni(resultset.getString("dni"));
                persona.setNom(resultset.getString("nombres"));  
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return persona;
	}

	@Override
	public boolean add(Persona persona) {
        String sql="insert into persona (id,dni,nombres) values(?,?,?)";
        try {
			connection=laConexion.getConnection();
	        try(PreparedStatement ps=connection.prepareStatement(sql);){
	            System.out.println(generateId());
	            ps.setInt(1, generateId()); //error
	            ps.setString(2, persona.getDni());
	            ps.setString(3, persona.getNom());
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        return false;
	}

	@Override
	public boolean edit(Persona persona) {
		String sql="update persona set dni=?,nombres=? where id=?";
		try {
			connection = laConexion.getConnection();
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, persona.getDni());
				ps.setString(2, persona.getNom());
				ps.setInt(3, persona.getId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean remove(int id) {
		String sql="delete from persona where id=?";
		try {
			connection = laConexion.getConnection();
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void closeConnection(ResultSet rs, Statement stmt, Connection cn) {
		try { 
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(cn != null) cn.close();
			System.out.println("conexiones cerradas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int generateId() {
		int id=-1;
        String sql="select max(id) from persona";
        try {
            connection=laConexion.getConnection();
            preparedStatement=connection.prepareStatement(sql); //CERRADA
            resultset=preparedStatement.executeQuery();
            while(resultset.next()){                
                id = resultset.getInt("max(id)")+1;
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } 
		return id;	
	}
	
	public List search(String texto) {
		List<Persona> lista = new ArrayList<>();
		String sql = "select * from persona where id like ? or dni like ? or nombres like ?";
		try {
			connection = laConexion.getConnection();
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, "%"+texto+"%");
				ps.setString(2, "%"+texto+"%");
				ps.setString(3, "%"+texto+"%");
				resultset = ps.executeQuery();
				while(resultset.next()) {
	                Persona per=new Persona();
	                per.setId(resultset.getInt("id"));
	                per.setDni(resultset.getString("dni"));
	                per.setNom(resultset.getString("nombres"));
	                lista.add(per);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
