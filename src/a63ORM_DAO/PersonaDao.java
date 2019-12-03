/**
 * Patron DAO (Data Access Object) Este patron de la capa de Acceso a Datos se
 * encarga de extraer y almacenar informacion de la base de datos Esta interfaz
 * contiene los metodos abstractos con las operaciones basicas sobre la tabla de
 * Persona CRUD (Create, Read, Update y Delete) Se debe crear una clase concreta
 * para implementar el codigo asociado a cada metodo
 **/
package a63ORM_DAO;

import a63ORM_DTO.Persona;
import a63ORM_DTO.PersonaPk;
import a63ORM_Exceptions.PersonaDaoException;

public interface PersonaDao {

    /**
     * Inserts a new row in the persona table.
     */
    public PersonaPk insert(Persona dto) throws PersonaDaoException;

    /**
     * Updates a single row in the persona table.
     */
    public void update(PersonaPk pk, Persona dto) throws PersonaDaoException;

    /**
     * Deletes a single row in the persona table.
     */
    public void delete(PersonaPk pk) throws PersonaDaoException;

    /**
     * Returns the rows from the persona table that matches the specified
     * primary-key value.
     */
    public Persona findByPrimaryKey(PersonaPk pk) throws PersonaDaoException;

    /**
     * Returns all rows from the persona table that match the criteria
     * 'id_persona = :idPersona'.
     */
    public Persona findByPrimaryKey(int idPersona) throws PersonaDaoException;

    /**
     * Returns all rows from the persona table that match the criteria ''.
     */
    public Persona[] findAll() throws PersonaDaoException;

    /**
     * Returns all rows from the persona table that match the criteria
     * 'id_persona = :idPersona'.
     */
    public Persona[] findWhereIdPersonaEquals(int idPersona) throws PersonaDaoException;

    /**
     * Returns all rows from the persona table that match the criteria 'nombre =
     * :nombre'.
     */
    public Persona[] findWhereNombreEquals(String nombre) throws PersonaDaoException;

    /**
     * Returns all rows from the persona table that match the criteria 'apellido
     * = :apellido'.
     */
    public Persona[] findWhereApellidoEquals(String apellido) throws PersonaDaoException;

    /**
     * Sets the value of maxRows
     */
    public void setMaxRows(int maxRows);

    /**
     * Gets the value of maxRows
     */
    public int getMaxRows();

    /**
     * Returns all rows from the persona table that match the specified
     * arbitrary SQL statement
     */
    public Persona[] findByDynamicSelect(String sql, Object[] sqlParams) throws PersonaDaoException;

    /**
     * Returns all rows from the persona table that match the specified
     * arbitrary SQL statement
     */
    public Persona[] findByDynamicWhere(String sql, Object[] sqlParams) throws PersonaDaoException;

}
