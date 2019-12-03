/*
 * Clase que representa un registro de la tabla de Persona.
 *
 */
package a57_1ManejoJDBC;

/**
 *
 * @author Panzer01
 */
public class A165Persona {
    //Atributos
    private int id_persona;
    private String nombre;
    private String apellido;

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

    //Metodo para conocer el estado del objeto.
    @Override
    public String toString() {
        return "Persona{" + "id_persona=" + id_persona + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
}