<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectodealimentacions.entidadesdenegocio.Recetas"%>
<% Recetas receta = (Recetas) request.getAttribute("receta");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles de la Recetas</title>
    </head>
    <body>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles de la Recetas</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalles de la Recetas</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=receta.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>         
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtIngredientes" type="text" value="<%=receta.getIngredientes()%>">
                    <label for="txtIngredientes">Ingredientes</label>
                </div>
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtPreparacion" type="text" value="<%=receta.getPreparacion()%>">
                    <label for="txtPreparacion">Preparacion</label>
                </div>
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtDificultad" type="text" value="<%=receta.getDificultad()%>">
                    <label for="txtDificultad">Dificultad</label>
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Recetas?accion=edit&id=<%=receta.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Recetas" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />

    </body>
</html>
