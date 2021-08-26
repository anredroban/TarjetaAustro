<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <script src="${resource(dir: 'javascripts', file: 'asignarBaseNew.js')}"></script>
    <title>Conversiones - Asignar Novedades</title>
</head>

<body>

<g:form controller="asignarBaseNew" action="indexNovedades">
    <input type="hidden" id="tipoAsignacion" name="tipoAsignacion" value="novedades">

    <div class="row">
        <div class="col-lg-12 col-md-12">
            <div class="panel panel-border-blue-u">
                <div class="panel-heading panel-body-blue-u">
                    <h3 class="panel-title" style="color: white">
                        <i class="fa fa-users fa-fw"></i> Asignar Novedades
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div
                            class="col-lg-6 ${hasErrors(bean: cliente, field: 'asesores', 'error')} required">
    <label for="asesores"><g:message
            code="clientes.cedula.label" default="Asesores"/> <span
            class="required-indicator">*&nbsp;</span>
    </label>
    <g:select name="consultants" size="15" id="consultants" required="true" multiple="multiple"
              from="${utilitarios.Util.getOperadores()}" optionKey="id"
              optionValue="${{ it.nombre }}">
    </g:select>
    </div>

    <div
            class="col-lg-6 ${hasErrors(bean: cliente, field: 'nombreBase', 'error')} required">
        <label for="nombreBase"><g:message
                code="clientes.cedula.label" default="Nombre de base"/> <span
                class="required-indicator">*&nbsp;</span>
        </label>
        <g:select name="dbName" id="dbName" required="true"
                  from="${utilitarios.Util.getNombresBaseNovedades()}"
                  noSelection="['': '-Seleccione-']">
        </g:select>
    </div><br><br><br>

    <div
            class="col-lg-6 ${hasErrors(bean: cliente, field: 'nombreBase', 'error')} required">
        <label for="tipoReg"><g:message
                code="clientes.cedula.label" default="Tipo"/> <span
                class="required-indicator">*&nbsp;</span>
        </label>
        <g:select name="tipoReg" id="tipoReg" required="true"
                  from="${['SIN GESTION', 'REGESTIONABLES']}"
                  noSelection="['': '-Seleccione-']">
        </g:select>
    </div><br><br><br>

    <div
            class="col-lg-6 ${hasErrors(bean: cliente, field: 'allocationNumber', 'error')} required">
        <label for="allocationNumber"><g:message
                code="clientes.allocationNumber.label"
                default="Numero de registros a asignar"/> <span
                class="required-indicator">*&nbsp;</span>
        </label>
        <g:textField required="true" name="allocationNumber"/>
    </div><br><br><br>

    <div id="divMensaje" class="col-lg-6">
        <g:if test="${baseAsignada}">

            <h2>Base Asignada</h2>

        </g:if>
    </div>

    </div>
    </div>
    <input type="submit" value="Asignar" id="bt_assign" name="bt_assign">
</g:form>
</body>
</html>