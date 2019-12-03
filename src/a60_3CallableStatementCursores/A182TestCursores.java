/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a60_3CallableStatementCursores;

import java.sql.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Panzer01
 */
public class A182TestCursores {

    public static void main(String[] args) {
        //Utilizamos una clase de oracle para poder procesar el
        //cursor que regresa la funcion de Oracle
        OracleCallableStatement oraCallStmt = null;
        OracleResultSet deptResultSet = null;
        try {
            Connection con = A182Conexion.getConnection();
            //Tiene dos parametros que posteriormente se definiran
            oraCallStmt = (OracleCallableStatement) con.prepareCall("{? = call ref_cursor_package.get_dept_ref_cursor(?)}");
            //Indicamos el tipo de regreso, el cual es un cursor
            oraCallStmt.registerOutParameter(1, OracleTypes.CURSOR);//Parametro 1
            oraCallStmt.setInt(2, 200);//establecemos departamento_id, parametro 2
            oraCallStmt.execute();
            //Recuperamos el resultSet y lo convertimos a un tipo Oracle
            deptResultSet = (OracleResultSet) oraCallStmt.getCursor(1);
            while (deptResultSet.next()) {
                System.out.print(" Id_departamento: " + deptResultSet.getInt(1));
                System.out.print(", Nombre_departamento: " + deptResultSet.getString(2));
                System.out.print(", UbicaciÃ³n_id: " + deptResultSet.getString(3));
                System.out.println();
            }
            oraCallStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
