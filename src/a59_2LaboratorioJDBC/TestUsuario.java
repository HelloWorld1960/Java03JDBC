/**
 * Este ejemplo realiza una capa de Datos utilizando JDBC.
 * Tambien se utiliza el manejo de transacciones, es decir todas las operaciones a ejecutar se realizaran con 
 * la misma conexion y cuando se haga un commit o un rollback, 
 * entonces se va afectar a todas estas operaciones ejecutadas durante esta transaccion.
 */
package a59_2LaboratorioJDBC;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Panzer01
 */
public class TestUsuario {
    public static void main(String[] args) {
        //Utilizamos el tipo interface como referencia
        //a una clase concreta, por ello se define la variable personaDao pero es de tipo interface 
        //y este tipo interface puede apuntar a un objeto de tipo en concreto, por ejemplo el 
        //objeto A172PersonaDAOJDBC, lo cual es una buena practica en programacion, debido a que cambiar 
        //el tipo de tecnologia JDBC no afectara el resto del codigo.
        UsuarioDAO personaDao = new UsuarioDAOJDBC();

        //Creamos un nuevo registro
        //Hacemos uso de la clase persona DTO la cual se usa
        //para transferiri la informacion entre las capas
        //no es necesario especificar la llave primaria
        //ya que en este caso es de tipo autonumerico y la BD se encarga
        //de asignarle un nuevo valor
        UsuarioDTO persona = new UsuarioDTO();
        persona.setUsuario("Mario");
        persona.setPassword("contraseña");
        //Utilizamos la capa DAO para persistir el objeto DTO
        try {
            //Ingresar un registro.
            //personaDao.insert(persona);

            //Eliminamos un registro, el id 3
            //personaDao.delete(new UsuarioDTO(6));
            
            //Actualizamos un registro
             UsuarioDTO personaTmp= new UsuarioDTO();
//             personaTmp.setId_usuario(5); //actualizamos el registro 5
//             personaTmp.setUsuario("Mario");
//             personaTmp.setPassword("contraseña");
//             personaDao.update(personaTmp);
            //Seleccionamos todos los registros
            List<UsuarioDTO> personas = personaDao.select();
            for (UsuarioDTO personaDTO : personas) {
                System.out.print(personaDTO);
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Excepcion en la capa de prueba");
            e.printStackTrace();
        }
    }
}
