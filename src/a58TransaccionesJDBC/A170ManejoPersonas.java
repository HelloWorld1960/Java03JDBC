/*
 * Todas las operaciones a ejecutar se realizaran con la misma conexion y cuando se haga un commit o un rollback, 
 * entonces se va afectar a todas estas operaciones ejecutadas durante esta transaccion.
 */
package a58TransaccionesJDBC;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Panzer01
 */
public class A170ManejoPersonas {
    public static void main(String[] args) {
        A170PersonasJDBC personasJDBC = new A170PersonasJDBC();
	
        //Creamos un objeto conexion, se va a compartir
        //para todos los queries que ejecutemos
        Connection conn = null;

        try {
            conn = A170Conexion.getConnection();
            //Revisamos si la conexion esta en modo autocommit
            //por default es autocommit == true
            if (conn.getAutoCommit()) {
                //Establecemos setAutoCommit a false, para que no se realice un commit de forma automatica, si no, 
                //para que unicamente en caso de que todos los queries se ejecuten correctamente entonces guardamos la 
                //informacion en la BD haciendo un commit, en caso contrario hacemos un rollback de la transaccion.
                conn.setAutoCommit(false);
            }
            //Creamos el objeto PersonasJDBC
            //proporcionamos la conexion creada
            A170PersonasJDBC personas = new A170PersonasJDBC(conn);

            //empezamos a ejecutar sentencias
            //recordar que una transaccion agrupa varias
            //sentencias SQL
            //si algo falla no se realizan los cambios en 
            //la BD
            //cambio correcto
            personas.update(4, "Regreso2", "Regreso");

            //Provocamos un error superando los 45 caracteres
            //del campo de apellido
            personas.insert("Miguel3",
                    //"Ayala12341234123412341234123412341234123412341234123412341234123412341234123412341234");
                    "Ayala3");
            //guardamos los cambios
            conn.commit();
        } catch (SQLException e) {
            //Hacemos rollback en caso de error
            try {
                System.out.println("Entramos al rollback");
                //Imprimimos la excepcion a la consola
                e.printStackTrace(System.out);
                //Hacemos rollback
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace(System.out);
            }
        }
    }
}
