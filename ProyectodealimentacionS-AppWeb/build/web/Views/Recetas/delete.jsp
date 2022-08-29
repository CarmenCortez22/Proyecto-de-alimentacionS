<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectodealimentacions.entidadesdenegocio.Recetas"%>
<% Recetas receta = (Recetas) request.getAttribute("receta");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Receta</title>
    </head>
    <body>
         <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Receta</h5>          
            <form action="Recetas" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=recetas.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=recetas.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>                                  
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtIngredientes" type="text" value="<%=recetas.getIngredientes()%>">
                    <label for="txtNombre">Ingredientes</label>
                </div>
                   <div class="input-field col l4 s12">
                    <input disabled  id="txtPreparacion" type="text" value="<%=recetas.getPreparacion()%>">
                    <label for="txtNombre">Preparacion</label>
                </div>
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtDificultad" type="text" value="<%=recetas.getDifilcultad()%>">
                    <label for="txtNombre">Difilcultad</label>
                </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Recetas" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />

    </body>
</html>
