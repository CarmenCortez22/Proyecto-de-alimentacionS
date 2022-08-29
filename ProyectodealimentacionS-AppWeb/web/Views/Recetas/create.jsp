<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Recetas</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Recetas</h5>
            <form action="Recetas" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>            
                    <div class="input-field col l4 s12">
                        <input  id="txtIngredientes" type="text" name="ingredientes" required class="validate" maxlength="30">
                        <label for="txtIngredientes">Ingredientes</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPreparacion" type="text" name="preparacion" required class="validate" maxlength="30">
                        <label for="txtPreparacion">Preparacion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDificultad" type="text" name="dificultad" required class="validate" maxlength="30">
                        <label for="txtDificultad">Difilcultad</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Recetas" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
