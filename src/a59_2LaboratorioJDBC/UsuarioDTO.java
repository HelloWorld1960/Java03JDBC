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
package a59_2LaboratorioJDBC;


/**
 *
 * @author Panzer01
 */
public class UsuarioDTO {
    //Atributos.
    private int id_usuario;
    private String usuario;
    private String password;
    
    //Constructor.
    public UsuarioDTO() {
    }
    
    public UsuarioDTO(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    //Metodos
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "id_usuario=" + id_usuario + ", usuario=" + usuario + ", password=" + password + '}';
    }

}
