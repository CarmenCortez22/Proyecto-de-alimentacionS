package proyectoalimentacion.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import proyectoalimentacion.appweb.utils.SessionUser;
import proyectoalimentacion.appweb.utils.Utilidad;
import proyectodealimentacions.accesodatos.RecetasDAL;
import proyectodealimentacions.accesodatos.RolDAL;
import proyectodealimentacions.entidadesdenegocio.Recetas;
import proyectodealimentacions.entidadesdenegocio.Rol;

@WebServlet(name = "RecetasServlet", urlPatterns = {"/RecetasServlet"})
public class RecetasServlet extends HttpServlet {

    private Recetas obtenerRecetas(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Recetas receta = new Recetas();
        if (accion.equals("create") == false) {
            receta.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }

        receta.setNombre(Utilidad.getParameter(request, "nombre", ""));
        if (accion.equals("index")) {
            receta.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            receta.setTop_aux(receta.getTop_aux() == 0 ? Integer.MAX_VALUE : receta.getTop_aux());
        }
        receta.setIngredientes(Utilidad.getParameter(request, "ingrediente", ""));
        if (accion.equals("index")) {
            receta.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            receta.setTop_aux(receta.getTop_aux() == 0 ? Integer.MAX_VALUE : receta.getTop_aux());
        }
        receta.setPreparacion(Utilidad.getParameter(request, "preparacion", ""));
        if (accion.equals("index")) {
            receta.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            receta.setTop_aux(receta.getTop_aux() == 0 ? Integer.MAX_VALUE : receta.getTop_aux());
        }
        receta.setDificultad(Utilidad.getParameter(request, "dificultad", ""));
        if (accion.equals("index")) {
            receta.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            receta.setTop_aux(receta.getTop_aux() == 0 ? Integer.MAX_VALUE : receta.getTop_aux());
        }
        return receta;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Recetas receta = new Recetas();
            receta.setTop_aux(10);
            ArrayList<Recetas> recetas = RecetasDAL.buscar(receta);
            request.setAttribute("recetas", recetas);
            request.setAttribute("top_aux", receta.getTop_aux());             
            request.getRequestDispatcher("Views/Recetas/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Recetas receta = obtenerRecetas(request);
            ArrayList<Recetas> recetas = RecetasDAL.buscar(receta);
            request.setAttribute("recetas", recetas);
            request.setAttribute("top_aux", receta.getTop_aux());
            request.getRequestDispatcher("Views/Recetas/index.jsp").forward(request, response);
        } catch (Exception ex) { 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
      private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Recetas/create.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Recetas receta = obtenerRecetas(request);
            int result = RecetasDAL.crear(receta);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Recetas receta = obtenerRecetas(request);
            Recetas recetas_result = RecetasDAL.obtenerPorId(receta);
            if (recetas_result.getId() > 0) {
                request.setAttribute("rol", recetas_result);
            } else {
                Utilidad.enviarError("El Id:" + receta.getId() + " no existe en la tabla de Receta", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Receta/edit.jsp").forward(request, response);
    }
     private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Recetas receta = obtenerRecetas(request);
            int result = RecetasDAL.modificar(receta);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Recetas/details.jsp").forward(request, response);
    }
    
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Recetas/delete.jsp").forward(request, response);
    }
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Recetas receta = obtenerRecetas(request);
            int result = RecetasDAL.eliminar(receta);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }

}
