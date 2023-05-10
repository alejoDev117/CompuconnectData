package co.edu.uco.compuconnect.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages;
import co.edu.uco.compuconnect.crosscutting.utils.UtilSql;

public class BaseDeDatos {
    private String user;
    private String password;
    private String host;

    public BaseDeDatos(String user, String password, String host) {
        this.user = user;
        this.password = password;
        this.host = host;
    }

    public Connection conectar() {
        Connection conexion = null;
        try {
            String url = "jdbc:postgresql://" + host + ":5432/compuconnect";
            conexion = DriverManager.getConnection(url, user, password);
            if (conexion != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al conectar a PostgreSQL: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        String user = "postgres";
        String password = "Bijuus12";
        String host = "localhost"; 

        BaseDeDatos bd = new BaseDeDatos(user, password, host);
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