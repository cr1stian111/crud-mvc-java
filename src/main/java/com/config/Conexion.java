package com.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection conection;
    String jdbc = "jdbc:oracle:thin:@localhost:1521:orcl";
    public Conexion(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conection=DriverManager.getConnection(jdbc,"crud","crud");
            System.out.println("conectado a bd");
        } catch (Exception e) {
        	System.out.println("fallo conexion");
            System.err.println("Error"+e);
        }
    }
    public Connection getConnection(){
        return conection;
    }
}
