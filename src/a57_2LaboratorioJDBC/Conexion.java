/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a57_2LaboratorioJDBC;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Panzer01
 */
public class Conexion {
    //Valores de conexion a MySql
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //Cargar en memoria la clase del driver de MySQL.
    //El puerto es opcional
    private static String JDBC_URL = "jdbc:mysql://localhost/sga2?useSSL=false"; //Cadena de conexion de MySql.
    private static String JDBC_USER = "root"; //Usuario de la BD
    private static String JDBC_PASS = ""; //Contraseña de la BD.
    private static Driver driver = null;

    /*Para que no haya problemas al obtener la conexion de
    manera concurrente, se usa la palabra synchronized./*
    /*Unicamente un hilo puede estar ejecutando este metodo a la vez y por eso usa la palabra synchronized, para que 
      este metodo unicamente pueda ser utilizado por un hilo a la vez.*/
    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                //Se registra el driver, es decir poner en memoria la clase del driver de JDBC que queremos utilizar.
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                //Creamos instancia de la clase jdbcDriverClass.
                driver = (Driver) jdbcDriverClass.newInstance();
                //Registramos la variable de tipo Driver.
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        /*Retorna un objeto de tipo Conexion ya instanciado para poder crear la conexion a la BD.*/
        return (DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS));
    }

    //Cierre del resultSet
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre del PrepareStatement
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre de la conexion
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
