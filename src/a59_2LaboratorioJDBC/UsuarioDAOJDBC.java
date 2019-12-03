/**
 * Esta clase implementa la clase PersonaDao es una implementacion con la
 * tecnologia JDBC podra haber otro tipo de implementaciones con tecnologias
 * como Hibernate, iBatis, SpringJDBC, etc.
 * Patron DAO (Data Access Object)
 * Este patron de la capa de Acceso a Datos se encarga de extraer y almacenar informacion de la base de datos
 *
 */
package a59_2LaboratorioJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Panzer01
 */
public class UsuarioDAOJDBC implements UsuarioDAO{
    //Atributos.
    private Connection userConn;

    private final String SQL_INSERT = "INSERT INTO usuarios (usuario, password) VALUES(?,?)";

    private final String SQL_UPDATE = "UPDATE usuarios SET usuario=?, password=? WHERE id_usuario=?";

    private final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuario = ?";

    private final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuarios";

    /**
     * Constructor vacio
     */
    public UsuarioDAOJDBC() {

    }

    /**
     * Constructor con que recibe una conexion
     *
     * @param conn
     */
    public UsuarioDAOJDBC(Connection conn) {
        this.userConn = conn;
    }

    /**
     * El metodo insert recibe como argumento un objeto DTO el cual viene de
     * otra capa, y se extraen sus valores para crear un nuevo registro
     */
    @Override
    public int insert(UsuarioDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, persona.getUsuario());
            stmt.setString(2, persona.getPassword());
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    /**
     * El metodo update recibe un objeto personaDTO el cual encapsula la
     * informacion en un solo objeto y evitamos pasar los 3 parametros de manera
     * aislada Despues extraemos la informacion del objeto y actualizamos el
     * registro seleccionado.
     * "UPDATE usuarios SET usuario=?, password=? WHERE id_usuario=?"
     */
    @Override
    public int update(UsuarioDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, persona.getUsuario());
            stmt.setString(2, persona.getPassword());
            stmt.setInt(3, persona.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    /**
     * Recibimos un objeto PersonaDTO no necesariamente debe venir lleno, sino
     * solo nos importa el atributo id_persona.
     * "DELETE FROM usuarios WHERE id_usuario = ?"
     */
    @Override
    public int delete(UsuarioDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    /**
     * En este metodo utilizamos el objeto PersonaDTO para llenar una lista y
     * regresarla.
     * "SELECT id_usuario, usuario, password FROM persona"
     */
    @Override
    public List<UsuarioDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO personaDTO = null;
        List<UsuarioDTO> personas = new ArrayList<UsuarioDTO>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Por cada registro se recuperan los valores
                //de las columnas y se crea un objeto DTO
                int idUsuarioTemp = rs.getInt(1);
                String usuarioTemp = rs.getString(2);
                String passwordTemp = rs.getString(3);

                //Llenamos el DTO y lo agregamos a la lista
                personaDTO = new UsuarioDTO();
                personaDTO.setId_usuario(idUsuarioTemp);
                personaDTO.setUsuario(usuarioTemp);
                personaDTO.setPassword(passwordTemp);
                personas.add(personaDTO);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return personas;
    }
}
