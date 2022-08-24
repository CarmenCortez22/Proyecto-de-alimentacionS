package proyectoalimentacion.appweb.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoalimentacion.appweb.utils.Utilidad;
import proyectodealimentacions.entidadesdenegocio.ControlPublicacion;
import proyectodealimentacions.accesodatos.ControlPublicacionDAL;
import java.util.ArrayList;
import proyectoalimentacion.appweb.utils.SessionUser;


@WebServlet(name = "ControlPublicacionServlet", urlPatterns = {"/ControlPublicacionServlet"})
public class ControlPublicacionServlet extends HttpServlet {

    private ControlPublicacion obtenerControlPublicacion(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        ControlPublicacion control = new ControlPublicacion();
        if (accion.equals("create") == false) {
            control.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }

        control.setNombre(Utilidad.getParameter(request, "nombre", ""));
        if (accion.equals("index")) {
            control.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            control.setTop_aux(control.getTop_aux() == 0 ? Integer.MAX_VALUE : control.getTop_aux());
        }
        control.setDescripcion(Utilidad.getParameter(request, "descripcion", ""));
        if (accion.equals("index")) {
            control.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            control.setTop_aux(control.getTop_aux() == 0 ? Integer.MAX_VALUE : control.getTop_aux());
        }
        
        return control;
    }
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ControlPublicacion control = new ControlPublicacion();
            control.setTop_aux(10);
            ArrayList<ControlPublicacion> controles = ControlPublicacionDAL.buscar(control);
            request.setAttribute("controles", controles);
            request.setAttribute("top_aux", control.getTop_aux());             
            request.getRequestDispatcher("Views/control/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ControlPublicacion control = obtenerControlPublicacion(request);
            ArrayList<ControlPublicacion> controles = ControlPublicacionDAL.buscar(control);
            request.setAttribute("controles", controles);
            request.setAttribute("top_aux", control.getTop_aux());
            request.getRequestDispatcher("Views/control/index.jsp").forward(request, response);
        } catch (Exception ex) { 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
      private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Rol/create.jsp").forward(request, response);
    }
      private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ControlPublicacion control = obtenerControlPublicacion(request);
            int result = ControlPublicacionDAL.crear(control);
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
            ControlPublicacion control = obtenerControlPublicacion(request);
            ControlPublicacion control_result = ControlPublicacionDAL.obtenerPorId(control);
            if (control_result.getId() > 0) {
                request.setAttribute("control", control_result);
            } else {
                Utilidad.enviarError("El Id:" + control.getId() + " no existe en la tabla de Rol", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/control/edit.jsp").forward(request, response);
    }
     
     private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ControlPublicacion control = obtenerControlPublicacion(request);
            int result = ControlPublicacionDAL.modificar(control);
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
        request.getRequestDispatcher("Views/control/details.jsp").forward(request, response);
    }
    
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/control/delete.jsp").forward(request, response);
    }
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ControlPublicacion control = obtenerControlPublicacion(request);
            int result = ControlPublicacionDAL.eliminar(control);
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
