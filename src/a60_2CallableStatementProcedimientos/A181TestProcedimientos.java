/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a60_2CallableStatementProcedimientos;

import java.sql.*;

/**
 *
 * @author Panzer01
 */
public class A181TestProcedimientos {

    public static void main(String[] args) {
        int empleadoId = 100;
        //la formula que aplica el Store Procedure es
        //salario=salario*incremento
        double incrementoSalario = 1.1;//incremento del 10%
        Connection con;
        try {
            con = A181Conexion.getConnection();
            Statement stmt = null;
            ResultSet rset = null;
            CallableStatement cstmt = null;

            stmt = con.createStatement();

            //Llamamos al SP para incrementar el salario
            System.out.println("Aumento del 10% al empleado:" + empleadoId);
            cstmt = con.prepareCall("{call set_employee_salary(?,?)}");
            cstmt.setInt(1, empleadoId);
            cstmt.setDouble(2, incrementoSalario);
            cstmt.execute();
            cstmt.close();

            //Obtenemos el nuevo valor del salario para el empleado seleccionado
            String query = "SELECT first_name, salary FROM employees "
                    + " WHERE  employee_id = " + empleadoId;

            rset = stmt.executeQuery(query);
            rset.next();
            System.out.println("Nombre: " + rset.getString(1));
            System.out.println("Salario nuevo:" + rset.getFloat(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
