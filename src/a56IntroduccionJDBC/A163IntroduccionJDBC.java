/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a56IntroduccionJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Panzer01
 */
public class A163IntroduccionJDBC {
    public static void main(String[] args) throws SQLException {
        //Cadena de conexion de MySql, el parametro useSSL es opcional
        //sga=es la BD a la que queremos hacer conexion.
        String url = "jdbc:mysql://localhost:3306/sga?useSSL=false";

        // Cargamos el driver de mysql
        try {
            //Cargar en memoria la clase del driver de MySQL.
            Class.forName("com.mysql.jdbc.Driver"); 
            // Creamos el objeto conexion.
            Connection conexion = (Connection) DriverManager.getConnection(url, "root", "");
            // Creamos un objeto Statement.
            // Satement: Se utiliza para cualquier tipo de sentencias SQL, pero no hace cache del SQL ejecutado.
            Statement instruccion = conexion.createStatement();
            // Creamos el query
            String sql = "Select id_persona, nombre, apellido FROM persona";
            /*executeQuery regresa un objeto ResultSet para procesar los registros.*/
            /*ResultSet almacena la informacion en un array de 2 dimenciones(renglones y columnas).*/
            ResultSet result = instruccion.executeQuery(sql);
            //result.next() Obtiene la primer fila.
            //next() Obtiene el valor siguiente.
            while (result.next()) {
                System.out.print("Id: " + result.getInt(1));//Obtiene la primer columna.
                System.out.print(" Nombre: " + result.getString(2));//Obtiene la segunda columna.
                System.out.println(" Apellido: " + result.getString(3));//Obtiene la tercer columna.
            }
            // Cerrar cada uno de los objetos utilizados
            result.close();
            instruccion.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}