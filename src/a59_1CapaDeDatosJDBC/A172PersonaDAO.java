/**
 * Esta interfaz contiene los metodos abstractos con las operaciones basicas
 * sobre la tabla de Persona CRUD (Create, Read, Update y Delete) Se debe crear
 * una clase concreta para implementar el codigo asociado a cada metodo
 * 
 */
package a59_1CapaDeDatosJDBC;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Panzer01
 */
public interface A172PersonaDAO {

    public int insert(A172PersonaDTO persona) throws SQLException;

    public int update(A172PersonaDTO persona) throws SQLException;

    public int delete(A172PersonaDTO persona) throws SQLException;

    public List<A172PersonaDTO> select() throws SQLException;
}
