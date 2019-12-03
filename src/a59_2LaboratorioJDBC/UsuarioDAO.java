/**
 * Esta interfaz contiene los metodos abstractos con las operaciones basicas
 * sobre la tabla de Persona CRUD (Create, Read, Update y Delete) Se debe crear
 * una clase concreta para implementar el codigo asociado a cada metodo
 * 
 */
package a59_2LaboratorioJDBC;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Panzer01
 */
public interface UsuarioDAO {

    public int insert(UsuarioDTO persona) throws SQLException;

    public int update(UsuarioDTO persona) throws SQLException;

    public int delete(UsuarioDTO persona) throws SQLException;

    public List<UsuarioDTO> select() throws SQLException;
}
