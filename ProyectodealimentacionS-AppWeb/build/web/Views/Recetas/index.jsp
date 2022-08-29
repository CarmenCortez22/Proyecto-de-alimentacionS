<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectodealimentacions.entidadesdenegocio.Recetas"%>
 
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Recetas</title>
    </head>
    <body>
         <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Recetas</h5>
            <form action="Recetas" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l6 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=receta.getNombre()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>           
                    <div class="input-field col l6 s12">
                        <input  id="txtIngredientes" type="text" name="ingredientes" value="<%=receta.getIngredientes()%>" required class="validate" maxlength="30">
                        <label for="txtIngredientes">Ingredientes</label>
                    </div> 
                     <div class="input-field col l6 s12">
                        <input  id="txtPreparacion" type="text" name="preparacion" value="<%=receta.getPreparacion()%>" required class="validate" maxlength="30>
                        <label for="txtPreparacion">Preparacion</label>
                    </div>
                    <div class="input-field col l6 s12">
                        <input  id="txtDificultad" type="text" name="dificultad"value="<%=receta.getDificultad()%>" required class="validate" maxlength="30>
                        <label for="txtDificultad">Dificultad</label>
                    </div>
                    <div class="input-field col l3 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Recetas?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />    
    </body>
</html>
