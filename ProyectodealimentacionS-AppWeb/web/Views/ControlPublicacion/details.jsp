<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectodealimentacions.entidadesdenegocio.ControlPublicacion"%>
<% ControlPublicacion control = (ControlPublicacion) request.getAttribute("control");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles de la Publicacion</title>
    </head>
    <body>
         <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalles de la Recetas</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=control.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>         
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtDescripcion" type="text" value="<%=control.getDescripcion()%>">
                    <label for="txtDescripcion">Descripcion</label>
                </div>
                
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="ControlPublicacion?accion=edit&id=<%=control.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="ControlPublicacion" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
