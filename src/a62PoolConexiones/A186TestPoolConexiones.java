/**
 * Crear una nueva conexion tiene un impacto en recursos bastante alto, por lo que lo mas 
 * conveniente es manejar el concepto de pool de conexiones.
 * Un pool de conexiones nos permite obtener una conexion por cada clase java que necesita de una conexion, 
 * El objetivo de un pool de conexiones es tener listas varias conexiones a la base de datos, de manera que sea mucho mas 
 * eficiente el proceso de obtener y liberar una conexion a la base de datos.
 * 
 */
package a62PoolConexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Panzer01
 */
public class A186TestPoolConexiones {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //Probamos el pool de MySql
            //y ejecutamos una consulta
            conn = A186PoolConexionesMySQL.getConexion();
            System.out.println("Utilizamos el pool de conexiones de MySql");
            stmt = conn.prepareStatement("SELECT * FROM persona");
            rs = stmt.executeQuery();
            while(rs.next()){
                System.out.print(" " + rs.getInt(1));//id_persona
                System.out.print(" " + rs.getString(2));//nombre
                System.out.println(" " + rs.getString(3));//apellido
            }
            //Cerramos la conexion para regresarla al pool
            conn.close();
            
             //Probamos el pool de Oracle
            //y ejecutamos una consulta
            conn = A186PoolConexionesOracle.getConexion();
            System.out.println("Utilizamos el pool de conexiones de Oracle");
            stmt = conn.prepareStatement("SELECT * FROM employees WHERE employee_id in(100,101,102)");
            rs = stmt.executeQuery();
            while(rs.next()){
                System.out.print(" " + rs.getInt(1));//empleado_id
                System.out.print(" " + rs.getString(2));//nombre
                System.out.println(" " + rs.getString(3));//apellido
            }
                        //Cerramos la conexion para regresarla al pool
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}
