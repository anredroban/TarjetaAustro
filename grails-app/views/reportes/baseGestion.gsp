<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Netlife - Reporte de base gestionada</title>
<asset:javascript src="reporteBaseGestionada.js" />
<asset:javascript src="moment.min.js" />
<asset:javascript src="daterangepicker.js" />
<asset:stylesheet src="daterangepicker.css" />
</head>
<body>
  <div class="body">
  	<div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-border-black">
                            <div class="panel-heading panel-body-black">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-table fa-fw"></i> Reporte Base Gestionada</h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                	<g:form action="baseGestion">
                                		
		                                <div class="col-lg-4 col-md-6 componentereporte">                                
		                                	<label for="fechas">Fecha: </label>
		  									<input id="reportrange" name="fechas" class="" style="background: #fff; cursor: pointer; padding: 0px 10px; border: 1px solid #ccc; width: 50%">
		  								</div>  
		  								
		  								<div class="col-lg-8 col-md-12 componentereporte">
		                                		<strong>Nombre Base: </strong>
		                                		<g:select name="nombrebase" from="${utilitarios.Util.getNombresBase()}" noSelection="${['': 'TODOS']}"></g:select>
		                                </div>	       
		                                
		                                <div class="col-lg-12 col-md-6 componentereporte">
		                                		<strong>Usuario: </strong>
		                                		<g:select name="usuario" optionKey="id" from="${com.pw.security.Usuario.findAll{rol.nombre == 'OPERADOR'; estado == 'ACTIVO'}}" optionValue="${{it.nombre}}" noSelection="${['': 'TODOS']}"></g:select>
		                                </div>                         		                                
		                                
		                                <div class="col-lg-4 col-md-6 componentereporte">
		                                	<input type="submit" id="btnreportebasegestionada" name="btnreportebasegestionada" class="botonreporte" style="background-color: white; border-style:solid" value="Generar Reporte">
		                                </div>		                                		                                
	                                
	                                </g:form>	                                
	                                									
  								</div>
								<%--table responsive--%>
									
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
  </div>
</body>
</html>