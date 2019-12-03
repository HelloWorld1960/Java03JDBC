/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a57_2LaboratorioJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Panzer01
 */
public class UsuariosJDBC {
    //Nos apoyamos de la llave primaria autoincrementable de MySql, por lo que se omite el campo de persona_id.
    //Se utiliza un prepareStatement, por lo que podemos, utilizar parametros (signos de ?), los cuales posteriormente 
    //seran sustituidos por el parametro respectivo.

    private final String SQL_INSERT
            = "INSERT INTO usuarios (usuario, password) VALUES (?,?)";

    private final String SQL_UPDATE
            = "UPDATE usuarios SET usuario=?, password=? WHERE id_usuario=?";

    private final String SQL_DELETE
            = "DELETE FROM usuarios WHERE id_usuario = ?";

    private final String SQL_SELECT
            = "SELECT id_usuario, usuario, password FROM usuarios ORDER BY id_usuario";

    /**
     * Metodo que inserta un registro en la tabla de usuarios
     */
    public int insert(String usuario, String password) {
        Connection conn = null; //Crear un objeto de tipo Conexion.
        PreparedStatement stmt = null; /*Se utiliza para hacer cache del query a ejecutar, evitando la re-compilacion de la sentencia SQL*/
        ResultSet rs = null;/*no se utiliza en este ejercicio*/
        int rows = 0; //registros afectados
        try {
            conn = Conexion.getConnection(); //Crear nueva conexion.
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, usuario); //
            stmt.setString(2, password); //
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//Retorna un int, indicando el no. registros afectados.
            System.out.println("Registros afectados:" + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Cerramos el objeto de tipo Statement y Connection.
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    /**
     * Metodo que actualiza un registro existente
     * @return int No. de registros modificados
     */
    public int update(int id_usuario, String usuario, String password) {
        Connection conn = null; //Crear un objeto de tipo Conexion.
        PreparedStatement stmt = null; /*Se utiliza para hacer cache del query a ejecutar, evitando la re-compilacion de la sentencia SQL*/
        int rows = 0; //registros afectados.
        try {
            conn = Conexion.getConnection(); //Crear nueva conexion.
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, usuario); //
            stmt.setString(2, password); //
            stmt.setInt(3, id_usuario);
            rows = stmt.executeUpdate(); /*Para una sentencia DDL/DML: executeUpdate regresa un int*/
            System.out.println("Registros actualizados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Cerramos el objeto de tipo Statement y Connection.
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return (rows);
    }

    /**
     * Metodo que elimina un registro existente
     *
     * @return int No. registros afectados
     */
    public int delete(int id_usuario) {
        Connection conn = null; //Crear un objeto de tipo Conexion.
        PreparedStatement stmt = null; //Creamos un objeto de tipo Statement.
        int rows = 0; //Cantidad de registros afectados.
        try {
            conn = Conexion.getConnection(); //Creamos nueva conexion.
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE); //Llamamos al metodo prepareStatement y proporcionamos la cadena que vamos a jecutar.
            stmt.setInt(1, id_usuario); //Sustituyendo los parametros(indice, valor).
            rows = stmt.executeUpdate(); /*Para una sentencia DDL/DML: executeUpdate regresa un int*/
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Cerramos el objeto de tipo Statement y Connection.
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return (rows);
    }

    /**
     * Metodo que regresa el contenido de la tabla de personas.
     * @return Retorna una lista del tipo Usuario.
     */
    public List<Usuario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; //Servira para procesar los registros que va regresar la consulta del tipo Select.
        Usuario usr = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conn = Conexion.getConnection(); //Creando nueva conexion.
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();/*Para sentencias SELECT: Regresa un objeto ResultSet para procesar los registros.*/
            while (rs.next()) {
                int id_usuario = rs.getInt(1);
                String usuario = rs.getString(2);
                String password = rs.getString(3);
                /*System.out.print(" " + id_persona);
                 System.out.print(" " + nombre);
                 System.out.print(" " + apellido);
                 System.out.println();
                 */
                usr = new Usuario();
                usr.setIdUsuario(id_usuario);
                usr.setUsuario(usuario);
                usr.setPassword(password);
                usuarios.add(usr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }
}
