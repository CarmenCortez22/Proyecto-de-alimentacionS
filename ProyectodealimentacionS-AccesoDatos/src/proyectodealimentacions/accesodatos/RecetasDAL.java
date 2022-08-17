package proyectodealimentacions.accesodatos;
import java.util.*; // Utilizar la utileria de java 
import java.sql.*;
import proyectodealimentacions.entidadesdenegocio.*;
public class RecetasDAL {
    
    public static String encriptarMD5(String txt) throws Exception {
        try {
            StringBuffer sb;
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw ex;
        }
    }
    static String obtenerCampos() {
        return "e.Id,e.IdCategoria,e.Nombre,e.Descripcion,e.Ingredientes,e.Preparacion,e.Dificultad";
    }

    // Metodo para obtener el SELECT a la tabla Rol y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Recetas eRecetas) {
        String sql;
        sql = "SELECT ";
        if (eRecetas.getTop_aux() > 0 && comunDB.TIPODB == comunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + eRecetas.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Receta e"); // Agregar los campos de la tabla de Rol mas el FROM a la tabla Rol con su alias "r"
        return sql;
    }
    private static String agregarOrderBy(Recetas eRecetas) {
        String sql = " ORDER BY u.Id DESC";
        if (eRecetas.getTop_aux() > 0 && comunDB.TIPODB == comunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Usuario en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + eRecetas.getTop_aux() + " ";
        }
        return sql;
    }
    public static int crear(Recetas eRecetas) throws Exception {
        int result;
        String sql;
        try (Connection conn = comunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Control(Nombre) VALUES(?), Control(Descripcion) VALUES(?)"; // Definir la consulta INSERT a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = comunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, eRecetas.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setString(2, eRecetas.getIngredientes());
                ps.setString(3, eRecetas.getPreparacion());
                ps.setString(4, eRecetas.getDificultad());// Agregar el parametro a la consulta donde estan el simbolo ? #1  
                result = ps.executeUpdate();// Ejecutar la consulta INSERT en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }
    public static int modificar(Recetas eRecetas) throws Exception {
        int result;
        String sql;
        try (Connection conn = comunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE   Control SET Nombre=? SET dESCRIPCION =? WHERE Id=?"; // Definir la consulta UPDATE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = comunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, eRecetas.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setString(2, eRecetas.getIngredientes());
                ps.setString(3, eRecetas.getPreparacion());
                ps.setString(4, eRecetas.getDificultad());
                ps.setInt(5, eRecetas.getIdcategoria()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(6, eRecetas.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                result = ps.executeUpdate(); // Ejecutar la consulta UPDATE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }
    public static int eliminar(Recetas eRecetas) throws Exception {
        int result;
        String sql;
        try (Connection conn = comunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Control WHERE Id=?";  // Definir la consulta DELETE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = comunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, eRecetas.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate();  // Ejecutar la consulta DELETE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    }
}
