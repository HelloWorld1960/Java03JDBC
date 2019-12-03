/**
 * Patrón Data Transfer Object (DTO/VO)
 * .
 * Este patron representa un objeto del dominio del problema, este prodria ser una clase que se persiste o se guarda en
 * una base de datos.
 * Este patron se encuentra presente en la capa de presentacion, capa de servicio, capa de acceso a datos 
 * debido a que se utiliza para transferir una entidad o una lista de entidades de cierto tipo entre las distintas capas
 * de la aplicacion.
 * Por ejemplo, un usuario puede estar solicitando un listado de personas, entonces la capa de presentacion procesa 
 * la peticion y solicita posteriormente a la capa de servicio que ejecute el metodo encontrar listado de personas, 
 * posteriormente la capa de servicio accede a la capa de datos para poder recuperar el listado de personas y 
 * posteriormente regresar un listado de objetos de persona utilizando los objetos DTO.
 * El objeto DTO en este caso es un objeto de tipo persona.
 * Despues se regresa la informacion con el listado de personas que se ha solicitado.
 * 
 */
package a59_1CapaDeDatosJDBC;

/**
 *
 * @author Panzer01
 */
public class A172PersonaDTO {
    //Atributos.
    private int id_persona;
    private String nombre;
    private String apellido;
    
    //Constructor.
    public A172PersonaDTO() {
    }
    
    public A172PersonaDTO(int id_persona) {
        this.id_persona = id_persona;
    }
    
    //Metodos
    public int getId_persona() {
        return id_persona;
    }
    public void setId_persona(int idPersona) {
        id_persona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Persona{" + "id_persona=" + id_persona + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
}
