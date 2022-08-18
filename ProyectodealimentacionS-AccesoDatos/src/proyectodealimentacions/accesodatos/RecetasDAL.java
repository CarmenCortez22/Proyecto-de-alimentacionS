package proyectodealimentacions.accesodatos;
import java.util.*; // Utilizar la utileria de java 
import java.sql.*;
import proyectodealimentacions.entidadesdenegocio.*;
public class RecetasDAL {
    
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
            sql = "INSERT INTO Recetas(Nombre) VALUES(?), Recetas(Ingredientes),Recetas(Preparacion),Recetas(Dificultad)VALUES(?)"; // Definir la consulta INSERT a la tabla de Rol utilizando el simbolo ? para enviar parametros
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
            sql = "UPDATE   Receta SET Nombre=? SET Ingredientes =? SET Preparacion=? SET Dificultad=? WHERE Idcategoria=? WHERE Id=?"; // Definir la consulta UPDATE a la tabla de Rol utilizando el simbolo ? para enviar parametros
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
            sql = "DELETE FROM Recetas WHERE Id=?";  // Definir la consulta DELETE a la tabla de Rol utilizando el simbolo ? para enviar parametros
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
    
    static int asignarDatosResultSet(Recetas eRecetas, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Rol
        pIndex++;
        eRecetas.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        eRecetas.setNombre(pResultSet.getString(pIndex)); 
        pIndex++;
        eRecetas.setIngredientes(pResultSet.getString(pIndex)); 
        pIndex++;
        eRecetas.setPreparacion(pResultSet.getString(pIndex));// index 2
        pIndex++;
        eRecetas.setDificultad(pResultSet.getString(pIndex));
        pIndex++;
        return pIndex;
    }
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Recetas> eRecetas) throws Exception {
        try (ResultSet resultSet = comunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Rol
                Recetas receta = new Recetas(); 
                asignarDatosResultSet(receta, resultSet, 0); // Llenar las propiedaddes de la Entidad Rol con los datos obtenidos de la fila en el ResultSet
                eRecetas.add(receta); // Agregar la entidad Rol al ArrayList de Rol
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    public static Recetas obtenerPorId(Recetas eRecetas) throws Exception {
        Recetas receta = new Recetas();
        ArrayList<Recetas> recetas = new ArrayList();
        try (Connection conn = comunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(eRecetas); // Obtener la consulta SELECT de la tabla Rol
            sql += " WHERE e.Id=?"; // Concatenar a la consulta SELECT de la tabla Rol el WHERE 
            try (PreparedStatement ps = comunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, eRecetas.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, recetas); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (recetas.size() > 0) { // Verificar si el ArrayList de Rol trae mas de un registro en tal caso solo debe de traer uno
            receta = recetas.get(0); // Si el ArrayList de Rol trae un registro o mas obtenemos solo el primero 
        }
        return receta; // Devolver el rol encontrado por Id 
    }
    public static ArrayList<Recetas> obtenerTodos() throws Exception {
        ArrayList<Recetas> recetas;
        recetas = new ArrayList<>();
        try (Connection conn = comunDB.obtenerConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Recetas());  // Obtener la consulta SELECT de la tabla Rol
            sql += agregarOrderBy(new Recetas());  // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id 
            try (PreparedStatement ps = comunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, recetas); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return recetas; // Devolver el ArrayList de Rol
    }
    
    static void querySelect(Recetas eRecetas, comunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (eRecetas.getId() > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Rol
            pUtilQuery.AgregarWhereAnd(" e.Id=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), eRecetas.getId()); 
            }
        }
        if (eRecetas.getIdcategoria() > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Rol
            pUtilQuery.AgregarWhereAnd(" e.Idcategoria=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), eRecetas.getIdcategoria()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Rol
        if (eRecetas.getNombre() != null && eRecetas.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" e.Nombre LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + eRecetas.getNombre() + "%"); 
            }
        }
         if (eRecetas.getIngredientes() != null && eRecetas.getIngredientes().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" e.Ingredientes LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + eRecetas.getIngredientes() + "%"); 
            }
        }
         if (eRecetas.getPreparacion() != null && eRecetas.getPreparacion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" e.Preparacion LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + eRecetas.getPreparacion() + "%"); 
            }
        }
         
         if (eRecetas.getDificultad() != null && eRecetas.getDificultad().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" e.Preparacion LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Rol
                statement.setString(pUtilQuery.getNumWhere(), "%" + eRecetas.getDificultad() + "%"); 
            }
        }
    }
    
        public static ArrayList<Recetas> buscar(Recetas eRecetas) throws Exception {
        ArrayList<Recetas> recetas = new ArrayList();
        try (Connection conn = comunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(eRecetas); // Obtener la consulta SELECT de la tabla Rol
            comunDB comundb = new comunDB();
            comunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(eRecetas, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Rol 
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(eRecetas); // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id
            try (PreparedStatement ps = comunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(eRecetas, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Rol
                obtenerDatos(ps, recetas); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return recetas; // Devolver el ArrayList de Rol
    }
}
