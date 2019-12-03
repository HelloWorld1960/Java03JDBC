/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a57_2LaboratorioJDBC;

import java.util.List;

/**
 *
 * @author Panzer01
 */
public class ManejoUsuarios {
    public static void main(String[] args) {
        UsuariosJDBC usuariosJDBC = new UsuariosJDBC();
	//Prueba del metodo insert
        //usuariosJDBC.insert("usuario2", "1234");
        
        //Prueba del metodo update
        //usuariosJDBC.update(2, "vChavez", "abcd");
		
        //Prueba del metodo delete
        //usuariosJDBC.delete(2);

        //Prueba del metodo select
        //Uso de un objeto persona para encapsular la informacion
        //de un registro de base de datos
        List<Usuario> usuarios = usuariosJDBC.select();
        for (Usuario elemento : usuarios) {
            System.out.print(elemento);
            System.out.println("");
        }
    }
}
