/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a57_1ManejoJDBC;

import java.util.List;

/**
 *
 * @author Panzer01
 */
public class A165ManejoPersonas {
    public static void main(String[] args) {
        A165PersonasJDBC personasJDBC = new A165PersonasJDBC();
	//Prueba del metodo insert
        //personasJDBC.insert("Jorge", "Hdz");
        
        //Prueba del metodo update
        //personasJDBC.update(2, "Nombre3", "Apellido3");
	
        //Prueba del metodo delete
        //personasJDBC.delete(7);
      
        //Prueba del metodo select
        //Uso de un objeto persona para encapsular la informacion
        //de un registro de base de datos
        List<A165Persona> personas = personasJDBC.select();
        for (A165Persona persona : personas) {
            System.out.print(persona);
            System.out.println("");
        }
    }

}