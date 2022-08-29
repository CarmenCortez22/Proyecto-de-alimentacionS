%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectoalimentacion.entidadesedenegocio.Rol"%>
<% Categoria categoria = (Categoria) request.getAttribute("Categoria");%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Categoria</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Categoria</h5>          
            <form action="Rol" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=Categoria.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtdesayuno" type="text" value="<%=Categoria.getdesayuno()%>">
                    <label for="txtNombre">desayuno</label>
                </div> 
                     <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtProteina" type="text" value="<%=Categoria.getProteina()%>">
                    <label for="txtNombre">porProteina</label>
                </div>
                     <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtporTiempo" type="text" value="<%=Categoria.getporTiempo()%>">
                    <label for="txtNombre">porTiempo</label>
                </div>
                     <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtbebidas" type="text" value="<%=Categoria.getbebidas()%>">
                    <label for="txtNombre">bebidas</label>
                </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Rol" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
