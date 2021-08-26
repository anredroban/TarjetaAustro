<meta name="layout" content="main" />
<asset:javascript src="gestionNovedades/gestionCliente.js" />
%{--<asset:javascript src="gestion/managementClientHappyCall.js"/>--}%
%{--<asset:javascript src="gestion/adicional.js"/>--}%
%{--<asset:javascript src="gestion/other.js"/>--}%
<asset:stylesheet src="usogeneral/datetimepicker.css" />
<asset:javascript src="usogeneral/datetimepicker.js" />
<asset:javascript src="usogeneral/customdatetimepicker.js" />
<div class="container-fluid">
    <div class="col-lg-12 group-title">Datos del cliente</div>
    <div class="col-lg-12 line"></div>
    <g:if test="${cliente.nombreCompletoTitular != null && cliente.nombreCompletoTitular != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Nombres:</label>
            ${cliente.nombreCompletoTitular}
        </div>
    </g:if>
    <g:if test="${cliente.idTitular != null && cliente.idTitular != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Identificación:</label>
            ${cliente.idTitular}
        </div>
    </g:if>
    <g:if test="${cliente.fecha != null && cliente.fecha != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Fecha:</label>
            ${cliente.fecha}
        </div>
    </g:if>
    <g:if test="${cliente.cantidadTarjetasAdi != null && cliente.cantidadTarjetasAdi != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Cantidad Adicionales:</label>
            ${cliente.cantidadTarjetasAdi}
        </div>
    </g:if>
    <g:if test="${cliente.ciudadTrabajo != null && cliente.ciudadTrabajo != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Ciudad Trabajo:</label>
            ${cliente.ciudadTrabajo}
        </div>
    </g:if>
    <g:if test="${cliente.provinciaTrabajo != null && cliente.provinciaTrabajo != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Provincia Trabajo:</label>
            ${cliente.provinciaTrabajo}
        </div>
    </g:if>
    <g:if test="${cliente.direccionTrabajo != null && cliente.direccionTrabajo != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Dirección Trabajo:</label>
            ${cliente.direccionTrabajo}
        </div>
    </g:if>
    <g:if test="${cliente.ciudadDomicilio != null && cliente.ciudadDomicilio != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Ciudad Domicilio:</label>
            ${cliente.ciudadDomicilio}
        </div>
    </g:if>
    <g:if test="${cliente.provinciaDomicilio != null && cliente.provinciaDomicilio != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Provincia Domicilio:</label>
            ${cliente.provinciaDomicilio}
        </div>
    </g:if>
    <g:if test="${cliente.direccionDomicilio != null && cliente.direccionDomicilio != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Dirección Domicilio:</label>
            ${cliente.direccionDomicilio}
        </div>
    </g:if>
    <g:if test="${cliente.proceso != null && cliente.proceso != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Proceso:</label>
            ${cliente.proceso}
        </div>
    </g:if>
    <g:if test="${cliente.lugarEntrega != null && cliente.lugarEntrega != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Lugar Entrega:</label>
            ${cliente.lugarEntrega}
        </div>
    </g:if>
    <g:if test="${cliente.estado != null && cliente.estado != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Estado:</label>
            ${cliente.estado}
        </div>
    </g:if>
    <g:if test="${cliente.errorInformacion != null && cliente.errorInformacion != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Error Información:</label>
            ${cliente.errorInformacion}
        </div>
    </g:if>
    <g:if test="${cliente.revisionDireccionDomicilio != null && cliente.revisionDireccionDomicilio != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Revisión Dirección Domicilio:</label>
            ${cliente.revisionDireccionDomicilio}
        </div>
    </g:if>
    <g:if test="${cliente.revisionDireccionTrabajo != null && cliente.revisionDireccionTrabajo != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Revisión Dirección Trabajo:</label>
            ${cliente.revisionDireccionTrabajo}
        </div>
    </g:if>

    <div class="col-lg-12 group-title">Teléfonos del Cliente</div>
    <div class="col-lg-12 line"></div>
    <g:if test="${cliente.celular != null && cliente.celular != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Celular:</label>
            ${cliente.celular}
        </div>
    </g:if>
    <g:if test="${cliente.telefonoCasa != null && cliente.telefonoCasa != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Teléfono Casa:</label>
            ${cliente.telefonoCasa}
        </div>
    </g:if>
    <g:if test="${cliente.telefonoTrabajo != null && cliente.telefonoTrabajo != ''}">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Teléfono Trabajo:</label>
            ${cliente.telefonoTrabajo}
        </div>
    </g:if>

    <div class="col-lg-12 group-title">Estados de Gestión</div>
    <div class="col-lg-12 line"></div>
    <g:form action="guardarCliente" name="management-client">
        <input type="hidden" value="${cliente.id}" id="idCliente" name="idCliente">
        <div class="col-lg-4 col-md-6 form-group">
            <label>Estado Gestion</label>
            <g:select class="form-control" name="status" id="status"
                      from="${callcenter.Estado.list()}" optionKey="id"
                      optionValue="${{it.nombre	}}"
                      noSelection="${['': '-- Seleccione --']}"></g:select>
        </div>
        <div id="subStatusDiv" class="col-lg-4 col-md-6 form-group">
            <label>Subestado Gestion</label>
            <g:select class="form-control" name="substatus" id="substatus" from=""></g:select>
        </div>
        <div id="subSubStatusDiv" class="form-group col-lg-4 col-md-6 col-xs-12">
            <label>Sub subestado</label>
            <span class="required-indicator">*</span>
            <g:select id="subSubStatus" class="form-control" name="subSubStatus" from=""></g:select>
        </div>
        <div id="recallDiv" class="col-lg-4 col-md-6 form-group">
            <label>Fecha Rellamada</label>
            <g:textField id="recall" name="recall" class="recall form-control"/>
        </div>

        <div id="productoPrincipalDiv" class="row col-lg-12 col-md-12">
            <div class="col-lg-12 group-title">Datos domicilio</div>
            <div class="col-lg-12 line"></div>
            <div class="col-lg-4 col-md-6 form-group">
                <label>Provincia Domicilio</label>
                <g:select class="form-control" name="provinciaDomic" id="provinciaDomic" from="${callcenter.Provincia.list()}" optionKey="id"
                          optionValue="${{it.nombre	}}"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-6 form-group">
                <label>Ciudad Domicilio</label>
                <g:select class="form-control" name="ciudadDomic" id="ciudadDomic" from="${callcenter.Ciudad.list()}" optionKey="id"
                          optionValue="${{it.nombre	}}"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Parroquia Domicilio</label>
                <g:select class="form-control" name="sectorDomic" id="sectorDomic" from="${callcenter.Parroquia.list()}" optionKey="id"
                          optionValue="${{it.nombre	}}"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-6 form-group">
                <label>Calle Principal</label>
                <g:textField class="form-control" id="callePrincipalDomic" name="callePrincipalDomic"/>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Nomenclatura</label>
                <g:textField class="form-control" id="numeracionDomic" name="numeracionDomic"/>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Calle Transversal</label>
                <g:textField class="form-control" id="calleTransversalDomic" name="calleTransversalDomic"/>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Tipo Vivienda</label>
                <g:select class="form-control" name="tipoVivienda" id="tipoVivienda" from="${['EDIFICIO':'EDIFICIO', 'CASA':'CASA', 'DEPARTAMENTO':'DEPARTAMENTO', 'CONJUNTO':'CONJUNTO']}" optionKey="key"
                          optionValue="value"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-12 col-md-12 form-group">
                <label>Referencia Domicilio</label>
                <g:textArea class="form-control" name="referenciaDomic" id="referenciaDomic"></g:textArea>
            </div>


            <div class="col-lg-12 group-title">Datos laborales</div>
            <div class="col-lg-12 line"></div>
            <div class="col-lg-4 col-md-6 form-group">
                <label>Provincia Trabajo</label>
                <g:select class="form-control" name="provinciaTrab" id="provinciaTrab" from="${callcenter.Provincia.list()}" optionKey="id"
                          optionValue="${{it.nombre	}}"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-6 form-group">
                <label>Ciudad Trabajo</label>
                <g:select class="form-control" name="ciudadTrab" id="ciudadTrab" from="${callcenter.Ciudad.list()}" optionKey="id"
                          optionValue="${{it.nombre	}}"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Parroquia Domicilio</label>
                <g:select class="form-control" name="sectorTrab" id="sectorTrab" from="${callcenter.Parroquia.list()}" optionKey="id"
                          optionValue="${{it.nombre	}}"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Calle Principal</label>
                <g:textField class="form-control" id="callePrincipalTrab" name="callePrincipalTrab"/>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Nomenclatura</label>
                <g:textField class="form-control" id="numeracionTrab" name="numeracionTrab"/>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Calle Transversal</label>
                <g:textField class="form-control" id="calleTransversalTrab" name="calleTransversalTrab"/>
            </div>

            <div class="col-lg-4 col-md-6 form-group">
                <label>Tipo</label>
                <g:select class="form-control" name="tipoTrab" id="tipoTrab" from="${['EDIFICIO':'EDIFICIO', 'CASA':'CASA', 'DEPARTAMENTO':'DEPARTAMENTO', 'CONJUNTO':'CONJUNTO']}" optionKey="key"
                          optionValue="value"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-12 col-md-12 form-group">
                <label>Referencia Trabajo</label>
                <g:textArea class="form-control" name="referenciaTrab" id="referenciaTrab"></g:textArea>
            </div>



            <div class="col-lg-12 group-title">Datos entrega</div>
            <div class="col-lg-12 line"></div>

            <div class="col-lg-4 col-md-4 form-group">
                <label>Entrega</label>
                <g:select class="form-control" name="entrega" id="entrega" from="${['DOMICILIO':'Domicilio', 'TRABAJO':'Trabajo']}" optionKey="key"
                          optionValue="value"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Nombre Contacto</label>
                <g:textField class="form-control" id="nombreContacto" name="nombreContacto"/>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Rango Visista</label>
                <g:select class="form-control" name="rangoVisita" id="rangoVisita" from="${['MANANA':'Mañana', 'TARDE':'Tarde']}" optionKey="key"
                          optionValue="value"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Facturacion</label>
                <g:select class="form-control" name="facturacion" id="facturacion" from="${['SI':'Si', 'NO':'No']}" optionKey="key"
                          optionValue="value"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Estado de cuenta digital</label>
                <g:select class="form-control" name="estadoCtaDigital" id="estadoCtaDigital" from="${['SI':'Si', 'NO':'No']}" optionKey="key"
                          optionValue="value"
                          noSelection="${['': '-- Seleccione --']}"></g:select>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Tlf Contacto Domicilio</label>
                <g:textField class="form-control" id="telefonoDomContacto" name="telefonoDomContacto"/>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Tlf Contacto Trabajo</label>
                <g:textField class="form-control" id="telefonoTrabContacto" name="telefonoTrabContacto"/>
            </div>
            <div class="col-lg-4 col-md-4 form-group">
                <label>Celular Contacto</label>
                <g:textField class="form-control" id="celularContacto" name="celularContacto"/>
            </div>


        </div>
        <br/>
    %{--managementData--}%

    %{--trabajo--}%
        <div id="managementDataWork" class="row col-lg-12 col-md-12">

        </div>
        <br/>

    %{--trabajo--}%
        <div id="managementDataDelivery" class="row col-lg-12 col-md-12">

        </div>

        <div class="col-lg-12 col-md-12 form-group">
            <label>Observaciones</label>
            <g:textArea class="form-control" id="observaciones" name="observaciones" value="${cliente.observaciones}"/>
        </div>

        <div class="col-lg-12 col-md-12 form-group">
            <g:submitButton class="btn btn-primary" name="save" id="btnGuardarGestion" value="Guardar" />
        </div>

    </g:form>

</div>