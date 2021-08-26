<!DOCTYPE html>
<%@page import="callcenter.Clientes"%>
<%@page import="com.pw.security.*"%>
<%@page import="utilitarios.Util"%>

<html>
	<head>		
		<meta name="layout" content="main"/>
		<title>Netlife - Dashboard</title>
	</head>
	<body>
		<div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Dashboard <small></small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> Dashboard
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

				
                <%--				CUADROS PARA QUITO--%>
                <div class="row col-lg-12">
                    <a href="${createLink(uri:'/usuario/')}">
	                    <div class="col-lg-3 col-md-6 cuadro">
	                        <div class="panel panel-primary">
	                            <div class="panel-heading">
	                                <div class="row">
	                                    <div class="col-xs-3">
	                                        <i class="fa fa-user fa-5x"></i>
	                                    </div>
	                                    <div class="col-xs-9 text-right">
	                                        <div class="huge">${Util.getOperadoresLogueados()[0]}</div>
	                                        <div>¡Agentes QUITO!</div>
	                                    </div>
	                                </div>
	                            </div>                            
	                        </div>
	                    </div>
                    </a>
                    <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 cuadro">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-dollar fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getCantidadVentas()[0]}</div>
                                        <div>¡Ventas QUITO!</div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </a>
                    <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 cuadro">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-phone fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getCantidadRellamadas()[0]}</div>
                                        <div>¡Rellamadas QUITO!</div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </a>
                    <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 cuadro">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-check fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getContactados()[0]}</div>
                                        <div>¡Contactados QUITO!</div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </a>
                    
                </div>
                <!-- /.row -->
                
<%--                CUADROS PARA GUAYAQUIL--%>
                <div class="row col-lg-12">
                    <a href="${createLink(uri:'/usuario/')}">
	                    <div class="col-lg-3 col-md-6 cuadro">
	                        <div class="panel panel-primary">
	                            <div class="panel-heading">
	                                <div class="row">
	                                    <div class="col-xs-3">
	                                        <i class="fa fa-user fa-5x"></i>
	                                    </div>
	                                    <div class="col-xs-9 text-right">
	                                        <div class="huge">${Util.getOperadoresLogueados()[1]}</div>
	                                        <div>¡Agentes GUAYAQUIL!</div>
	                                    </div>
	                                </div>
	                            </div>                            
	                        </div>
	                    </div>
                    </a>
                    <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 cuadro">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-dollar fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getCantidadVentas()[1]}</div>
                                        <div>¡Ventas GUAYAQUIL!</div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </a>
                    <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 cuadro">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-phone fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getCantidadRellamadas()[1]}</div>
                                        <div>¡Rellamadas GUAYAQUIL!</div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </a>
                    <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 cuadro">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-check fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getContactados()[1]}</div>
                                        <div>¡Contactados GUAYAQUIL!</div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </a>
                    
                </div>
                <!-- /.row -->
                
<%--                Grafico de barras--%>
                <div class="row">                    
                    <div class="col-lg-6 col-md-12">
                        <div class="panel panel-border-blue-u">
                            <div class="panel-heading panel-body-blue-u">
                                <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Inicio de sesion</h3>
                                <a class="btn btn-blue-u btn-clickable pull-right" onclick="return false;" href="#">
								<i class="fa fa-chevron-down"></i> 
								</a>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>Agente</th>
                                                <th>Hora Inicio</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${inicioSesion}">
                                            	<tr>
                                            		<td>${it[0]}</td><td>${it[1]}</td>
                                            	</tr>
                                            </g:each>
                                        </tbody>
                                    </table>
                                </div>
                                </div>                                
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-6 col-md-12">
                        <div class="panel panel-border-cyan">
                            <div class="panel-heading panel-body-cyan">
                                <h3 class="panel-title"><i class="fa fa-dollar fa-fw"></i> Ventas por Agente</h3>
                                <a class="btn btn-cyan btn-clickable pull-right" onclick="return false;" href="#">
								<i class="fa fa-chevron-down"></i> 
								</a>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>Agente</th>
                                                <th>Ventas</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${ventasPorUsuario}">
                                            	<tr>
                                            		<td>${it[0]}</td><td>${it[1]}</td>
                                            	</tr>
                                            </g:each>
                                        </tbody>
                                    </table>
                                </div>
                                </div>                                
                            </div>
                        </div>
                    </div>                                                                                
                    
                </div>
                <!-- /.row -->
				
				
                
                <!-- /.row -->                                
				
				<div class="row">
				<div class="col-lg-12 col-md-12">
                        <div class="panel panel-border-black">
                            <div class="panel-heading panel-body-black">
                                <h3 class="panel-title" style="color: white"><i class="fa fa-table fa-fw"></i> Detalles Gestion</h3>
                                <a class="btn btn-black btn-clickable pull-right" onclick="return false;" href="#">
								<i class="fa fa-chevron-down"></i> 
								</a>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>AGENTE</th>
                                                <th>GESTIONADOS</th>
                                                <th>CONTACTADOS</th>
                                                <th>NO CONTACTADOS</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${tablaResult}">
                                            	<tr>
                                            		<td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td>
                                            	</tr>
                                            </g:each>
                                            <tr style="color: blue; background-color: #D3D3D3;">
                                            	<td><strong>TOTAL</strong></td><td><strong>${totalGestionados}</strong></td><td><strong>${totalContactados}</strong></td><td><strong>${totalNoContactados}</strong></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                </div>                                
                            </div>
                        </div>
                    </div>
				</div>	
				<%--row--%>
            </div>
            <!-- /.container-fluid -->
            
            
            
            <script src="https://code.highcharts.com/highcharts.js"></script>
			<script src="https://code.highcharts.com/modules/exporting.js"></script>
			<script src="https://code.highcharts.com/modules/data.js"></script>
			<script src="https://code.highcharts.com/modules/drilldown.js"></script>
	</body>
</html>