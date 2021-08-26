<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Adicionales Austro - Gestión</title>
    <style>
    .linkcliente{
        color: blue !important
    }
    </style>
</head>
<body>
<div class="body">
    <a href="#list-permiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="panel panel-border-blue-u">
                    <div class="panel-heading panel-body-blue-u">
                        <h3 class="panel-title" style="color:white"><i class="fa fa-users fa-fw"></i> NOVEDADES (Clic en el nombre del cliente para gestionar)</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table id="clienteslista" class="table">
                                <thead>
                                <tr>

                                    <g:sortableColumn property="descripcion" title="${message(code: 'gestion.descripcion.label', default: 'Nombre')}" />

                                    <g:sortableColumn property="descripcion" title="${message(code: 'gestion.descripcion.label', default: 'Identificacion')}" />

                                    <g:sortableColumn property="descripcion" title="${message(code: 'gestion.descripcion.label', default: 'Base')}" />

                                    <g:sortableColumn property="descripcion" title="${message(code: 'gestion.descripcion.label', default: 'Subestado')}" />

                                    <g:sortableColumn property="descripcion" title="${message(code: 'gestion.descripcion.label', default: 'Fecha Rellamada')}" />

                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${clientesGestionar}" status="i" var="cliente">
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                        <td><g:link action="gestionCliente" class="linkcliente" id="${cliente.id}">${fieldValue(bean: cliente, field: "nombreCompletoTitular")}</g:link></td>

                                        <td><g:link action="gestionCliente" class="linkcliente" id="${cliente.id}">${fieldValue(bean: cliente, field: "idTitular")}</g:link></td>

                                        <td>${fieldValue(bean: cliente, field: "nombreBase")}</td>

                                        <td>${fieldValue(bean: cliente, field: "subestadoGestion.nombre")}</td>

                                        <td>${fieldValue(bean: cliente, field: "fechaRellamada")}</td>

                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                        <%--table responsive--%>

                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div><br>
</div>
</body>
</html>