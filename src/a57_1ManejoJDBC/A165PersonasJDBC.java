/*
 * Clase que contiene los metodos de SELECT, INSERT, UPDATE y DELETE para la
   tabla de PERSONAS en MYSQL
 */
package a57_1ManejoJDBC;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Panzer01
 */
public class A165PersonasJDBC {
    //Nos apoyamos de la llave primaria autoincrementable de MySql, por lo que se omite el campo de persona_id.
    //Se utiliza un prepareStatement, por lo que podemos, utilizar parametros (signos de ?), los cuales posteriormente 
    //seran sustituidos por el parametro respectivo.

    private final String SQL_INSERT
            = "INSERT INTO persona (nombre, apellido) VALUES (?,?)";

    private final String SQL_UPDATE
            = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";

    private final String SQL_DELETE
            = "DELETE FROM persona WHERE id_persona = ?";

    private final String SQL_SELECT
            = "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";

    /**
     * Metodo que inserta un registro en la tabla de Persona
     *
     * @param nombre
     * @param apellido
     * @return numero de registros afectados.
     */
    public int insert(String nombre, String apellido) {
        Connection conn = null; //Crear un objeto de tipo Conexion.
        PreparedStatement stmt = null; /*Se utiliza para hacer cache del query a ejecutar, evitando la re-compilacion de la sentencia SQL*/
        ResultSet rs = null;/*no se utiliza en este ejercicio*/
        int rows = 0; //registros afectados
        try {
            conn = A165Conexion.getConnection(); //Crear nueva conexion.
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1; //contador de columnas (1 es id_persona en la BD)
            stmt.setString(index++, nombre); //(2 es nombre en la BD) param 1 => ?
            stmt.setString(index++, apellido); //(3 es apellido en la BD) param 2 => ?
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//Retorna un int, indicando el no. registros afectados.
            System.out.println("Registros afectados:" + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Cerramos el objeto de tipo Statement y Connection.
            A165Conexion.close(stmt);
            A165Conexion.close(conn);
        }
        return rows;
    }

    /**
     * Metodo que actualiza un registro existente
     *
     * @param id_persona Es la llave primaria
     * @param nombre Nuevo Valor
     * @param apellido Nuevo Valor
     * @return int No. de registros modificados
     */
    public int update(int id_persona, String nombre, String apellido) {
        Connection conn = null; //Crear un objeto de tipo Conexion.
        PreparedStatement stmt = null; /*Se utiliza para hacer cache del query a ejecutar, evitando la re-compilacion de la sentencia SQL*/
        int rows = 0; //registros afectados.
        try {
            conn = A165Conexion.getConnection(); //Crear nueva conexion.
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1; //(1 es id_persona en la BD)
            stmt.setString(index++, nombre); //(2 es nombre en la BD)
            stmt.setString(index++, apellido); //(3 es apellido en la BD)
            stmt.setInt(index, id_persona);
            rows = stmt.executeUpdate(); /*Para una sentencia DDL/DML: executeUpdate regresa un int*/
            System.out.println("Registros actualizados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Cerramos el objeto de tipo Statement y Connection.
            A165Conexion.close(stmt);
            A165Conexion.close(conn);
        }
        return (rows);
    }

    /**
     * Metodo que elimina un registro existente
     *
     * @param id_persona Es la llave primaria
     * @return int No. registros afectados
     */
    public int delete(int id_persona) {
        Connection conn = null; //Crear un objeto de tipo Conexion.
        PreparedStatement stmt = null; //Creamos un objeto de tipo Statement.
        int rows = 0; //Cantidad de registros afectados.
        try {
            conn = A165Conexion.getConnection(); //Creamos nueva conexion.
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE); //Llamamos al metodo prepareStatement y proporcionamos la cadena que vamos a jecutar.
            stmt.setInt(1, id_persona); //Sustituyendo los parametros(indice, valor).
            rows = stmt.executeUpdate(); /*Para una sentencia DDL/DML: executeUpdate regresa un int*/
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Cerramos el objeto de tipo Statement y Connection.
            A165Conexion.close(stmt);
            A165Conexion.close(conn);
        }
        return (rows);
    }

    /**
     * Metodo que regresa el contenido de la tabla de personas.
     * @return Retorna una lista del tipo A165Persona.
     */
    public List<A165Persona> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; //Servira para procesar los registros que va regresar la consulta del tipo Select.
        A165Persona persona = null;
        List<A165Persona> personas = new ArrayList<A165Persona>();
        try {
            conn = A165Conexion.getConnection(); //Creando nueva conexion.
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();/*Para sentencias SELECT: Regresa un objeto ResultSet para procesar los registros.*/
            while (rs.next()) {
                int id_persona = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                /*System.out.print(" " + id_persona);
                 System.out.print(" " + nombre);
                 System.out.print(" " + apellido);
                 System.out.println();
                 */
                persona = new A165Persona();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                personas.add(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            A165Conexion.close(rs);
            A165Conexion.close(stmt);
            A165Conexion.close(conn);
        }
        return personas;
    }
}