<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectoalimentacions.entidadesdenegocio.Categoria"%>
<% Categoria categoria = (Categoria) request.getAttribute("categoria");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles de Categoria</title>
    </head>
    <body>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles de Categoria</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Categoria</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtdesayuno" type="text" value="<%=Categoria.getdesayuno()%>">
                    <label for="txtDesayuno">desayuno</label>
                </div>                                         
            </div>
                    <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtporproteina" type="text" value="<%=Categoria.getporproteina()%>">
                    <label for="txtPorproteina">porproteina</label>
                </div>      
               <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtporTiempo" type="text" value="<%=Categoria.getporTiempo()%>">
                    <label for="txtPorTiempo">porTiempo</label>
                </div>             
                <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtbebidas" type="text" value="<%=Categoria.getbebidas()%>">
                    <label for="txtBebidas">bebidas</label>
                </div>  
            </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Rol?accion=edit&id=<%=Categoria.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Rol" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
