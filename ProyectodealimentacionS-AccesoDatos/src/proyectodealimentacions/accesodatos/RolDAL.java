package proyectodealimentacions.accesodatos;
import java.util.*; // Utilizar la utileria de java https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
import java.sql.*;
import proyectodealimentacions.entidadesdenegocio.*;

public class RolDAL {
    
    static String obtenerCampos() {
        return "r.Id,r.Nombre";
    }
    
    private static String obtenerSelect(Rol pRol) {
        String sql;
        sql = "SELECT ";
        if (pRol.getTop_aux() > 0 && comunDB.TIPODB == comunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pRol.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Rol r"); // Agregar los campos de la tabla de Rol mas el FROM a la tabla Rol con su alias "r"
        return sql;
    }
}
