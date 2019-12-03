/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a60_1CallableStatementFunciones;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Panzer01
 */
public class A179Conexion {
    //Variables de conexion
    private static String JDBC_DRIVER;
    //El puerto es opcional
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver = null;
    //Vamos a crear un archivo .properties de nombre ConexionJDBC
    private static String JDBC_FILE_NAME = "jdbc";

    /**
     * Metodo que lee el archivo de propiedades con los valores a utilizar para
     * conectarnos a la BD
     *
     * @param file
     * @return tipo Properties
     */
    public static Properties loadProperties(String file) {
        Properties prop = new Properties();
        //Permite leer el archivo jdbc 
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        //Solicitamos las llaves del archivo jdbc y almacenamos en un tipo Enumeration para poder iterar 
        //cada uno de los valores de las llaves del archivo jdbc.
        Enumeration e = bundle.getKeys();
        //Iteramos cada una de las llaves en el archivo jdbc
        //hasMoreElements = si tiene mas elementos entonces se va iterar.
        String key = null;
        while (e.hasMoreElements()) {
            //Obtenemos la llave del archivo.
            key = (String) e.nextElement();
            //Agregamos la llave y el valor la variable prop.
            prop.put(key, bundle.getObject(key));
        }

        //Asignamos los valores del archivo de propiedades 
        //a las variables de la clase
        JDBC_DRIVER = prop.getProperty("driver");
        JDBC_URL = prop.getProperty("url");
        JDBC_USER = prop.getProperty("user");
        JDBC_PASS = prop.getProperty("pass");

        //Regresamos el objeto properties con los valores
        //de la conexion a la BD
        return prop;
    }

    //Para que no haya problemas al obtener la conexion de
    //manera concurrente, se usa la palabra synchronized
    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                //Cargamos las propiedades de conexion a la BD
                loadProperties(JDBC_FILE_NAME);
                //Se registra el driver de Oracle
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        //regresa un objeto de tipo Connection
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
