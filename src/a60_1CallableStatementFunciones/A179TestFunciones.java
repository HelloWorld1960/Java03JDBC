/**
 * Los Store Procedures son consultas que se ejecutan en la base de datos. La intencion es delegar a la base de datos 
 * procesos que serian muy pesados o muy dificles de procesar utilizando la aplicacion Java.
 * La diferencia es que en lugar de usar un preparedstatement se usa la interface callablestatement.
 * Al ejecutar un Store Procedures que regrese informacion, esta se referencia por medio de un cursor el cual 
 * es muy similar a un resultset, el cual contiene las filas y las columnas que son el resultado 
 * de la ejecucion de un store procedure.
 * Una de las grandes diferencias entre preparedstatement y callablestatement.
 * preparedstatement solo regresa dos tipos de objetos, un primitivo int y un objeto result set.
 * callablestatement regresa parametros de tipo out y parametros que son de entrada y salida que son in, out.
 * Un store procedure puede regresar varios valores ademas de que un callablestatement puede regresar todo tipo de datos 
 * como son numbers, date, integer, 
 * 
 */
package a60_1CallableStatementFunciones;

import java.sql.*;

/**
 *
 * @author Panzer01
 */
public class A179TestFunciones {

    public static void main(String[] args) {
        int empleadoId = 100; // indentificador a recuperar salario
        try {
            Connection con = A179Conexion.getConnection();
            // Objeto que permite ejecutar una funcion o store procedure
            CallableStatement cstmt = null;
            double salarioMensual;

            // Llamamos a una funcion de Oracle
            cstmt = con.prepareCall("{ ? = call get_employee_salary(?) }");
            // Una funcion regresa un valor
            // por lo que lo registramos como el parametro 1
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            // registrmos el segundo parametro
            cstmt.setInt(2, empleadoId);
            cstmt.execute();
            salarioMensual = cstmt.getDouble(1);
            cstmt.close();
            System.out.println("Empleado con id:" + empleadoId);
            System.out.println("Salario $" + salarioMensual);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
