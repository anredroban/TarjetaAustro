<meta name="layout" content="main">
<asset:stylesheet src="usogeneral/bootstrap-datepicker.min.css" />
<div class="container-fluid col-lg-12 col-md-12 col-xs-12">
    <div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
        <strong>Fecha Actual de inicio de gestión:</strong> ${callcenter.Configuracion.findById(1).fechaInicioGestion.toString().substring(0, 10)}
    </div>
   <g:form action="cambiarInicioGestion">
       <div class="form-group col-lg-4 col-md-4 col-xs-12">
           <label>Nueva Fecha</label>
           <g:textField required="" class="form-control datepickerdown pointer" name="nuevaFecha" />
       </div>
       <div class=" form-group col-lg-2 col-md-2 col-xs-12">
           <label>&nbsp;</label>
           <g:submitButton name="btnCambiarFecha" class="form-control btn btn-success" value="Cambiar"/>
       </div>
   </g:form>
    <asset:javascript src="usogeneral/bootstrap-datepicker.min.js" />
    <asset:javascript src="usogeneral/bootstrap-datepicker.es.min.js" />
    <asset:javascript src="usogeneral/customdatepicker.js" />
</div>