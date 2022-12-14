<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectoalimentacions.entidadesdenegocio.Categoria"%>
<% Categoria categoria = (Categoria) request.getAttribute("categoria");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Categoria</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Categoria</h5>
            <form action="Categoria" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=Categoria.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=Categoria.getdesayuno()%>" required class="validate" maxlength="30">
                        <label for="txtDesayuno">desayuno</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=Categoria.getporProteina()%>" required class="validate" maxlength="30">
                        <label for="txtPorProteina">porProteina</label>
                    </div>
                        <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=Categoria.getporTiempo()%>" required class="validate" maxlength="30">
                        <label for="txtPorTiempo">porTiempo</label>
                    </div>
                        <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=Categoria.getbebidas()%>" required class="validate" maxlength="30">
                        <label for="txtBebidas">bebidas</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Rol" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>