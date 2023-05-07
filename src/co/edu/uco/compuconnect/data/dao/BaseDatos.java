package co.edu.uco.compuconnect.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
	    private String user;
	    private String password;
	    private String host;

	    public BaseDatos(String user, String password, String host) {
	        this.user = user;
	        this.password = password;
	        this.host = host;
	    }

	    public Connection conectar() {
	        Connection conexion = null;
	        try {
	            String url = "jdbc:postgresql://" + host + ":5432/copuconnect";
	            conexion = DriverManager.getConnection(url, user, password);
	            if (conexion != null) {
	                // La conexión fue exitosa
	            }
	        } catch (SQLException e) {
	            System.out.println("Ocurrió un error al conectar a PostgreSQL: " + e.getMessage());
	        }
	        return conexion;
	    }

	    public static void main(String[] args) {
	        String user = "postgres";
	        String password = "admin";
	        String host = "localhost"; 

	        BaseDatos bd = new BaseDatos(user, password, host);
	        Connection conexion = bd.conectar();

	        if (conexion != null) {
	        }

	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	            }
	        }
	    }

}
