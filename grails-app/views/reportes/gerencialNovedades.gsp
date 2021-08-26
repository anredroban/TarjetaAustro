<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Conversiones - Reporte Gerencial</title>
    <asset:javascript src="reporteVentasSubdistribuidor.js" />
    <asset:javascript src="moment.min.js" />
    <asset:javascript src="daterangepicker.js" />
    <asset:stylesheet src="daterangepicker.css" />
    <style>
    .tr-blue-strong{
        background-color: #1b6d85;
        color: white
    }
    .tr-yellow-strong{
        background-color: yellow;
        color: blue
    }
    .tr-red{
        color: red;
    }
    .tr-blue{
        color: blue;
    }
    </style>
</head>
<body>
<div class="body">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-border-blue-u">
                <div class="panel-heading panel-body-blue-u">
                    <h3 class="panel-title" style="color:white"><i class="fa fa-table fa-fw"></i> Reporte Gerencial Novedades</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <g:form action="gerencialNovedades">

                            <div class="col-lg-4 col-md-6 componentereporte">
                                <label for="reportrange">Fecha: </label>
                                <input id="reportrange" name="fechas" class="" style="background: #fff; cursor: pointer; padding: 0px 10px; border: 1px solid #ccc; width: 50%">
                            </div>

                            <div class="col-lg-8 col-md-12 componentereporte">
                                <strong>Nombre Base: </strong>
                                <g:select name="nombrebase" from="${utilitarios.Util.getNombresBaseNovedades()}" noSelection="${['': 'TODOS']}"></g:select>
                            </div>

                            <div class="col-lg-4 col-md-6 componentereporte">
                                <input type="submit" id="btnreportegrencial" name="btnreportecontactabilidad" class="botonreporte" style="background-color: white; border-style:solid" value="Generar Reporte">
                            </div>

                        </g:form>

                        <div class="col-lg-12">
                            <g:if test="${visualizarReporte}">
                                <table style="border: 1px solid black">
                                    <tr class="tr-blue-strong">
                                        <td></td><td>No Clientes</td><td>TOTAL</td><td>% Total</td><td>% Subtotal</td>
                                    </tr>
                                    <tr class="tr-blue-strong">
                                        <td>TOTAL CLIENTES LOCALIZADOS</td><td></td><td>${estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']}</td><td>${Math.round(((estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8'])*100/baseRecibida)*100)/100}%</td><td>100%</td>
                                    </tr>
                                    <tr class="tr-red">
                                        <td>UBICADOS EXITOSOS</td><td></td><td>${estados['CU1']+estados['CU2']}</td><td></td><td>${(estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']) > 0?Math.round(((estados['CU1']+estados['CU2'])*100/(estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']))*100)/100: 0}%</td>
                                    </tr>
                                    <tr>
                                        <td>CU1 /Ubicado/Exitoso/Cumplió con el objetivo</td><td>${estados['CU1']}</td><td></td><td>${Math.round((estados['CU1']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>CU2 /Ubicado/Exitoso/Cumplió con el objetivo/Venta cruzada</td><td>${estados['CU2']}</td><td></td><td>${Math.round((estados['CU2']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr class="tr-red">
                                        <td>UBICADOS OTROS</td><td></td><td>${estados['CU3']+estados['CU4']+estados['CU5']}</td><td></td><td>${(estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']) > 0?Math.round(((estados['CU3']+estados['CU4']+estados['CU5'])*100/(estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']))*100)/100: 0}%</td>
                                    </tr>
                                    <tr>
                                        <td>CU3 /Ubicado/No exitoso/Ya tiene la oferta</td><td>${estados['CU3']}</td><td></td><td>${estados['CU3']*100/baseRecibida}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>CU4 /Ubicado/No exitoso/Rellamada (Tomará una decisión)</td><td>${estados['CU4']}</td><td></td><td>${estados['CU4']*100/baseRecibida}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>CU5 /Ubicado/No exitoso/Venta cruzada</td><td>${estados['CU5']}</td><td></td><td>${estados['CU5']*100/baseRecibida}%</td><td></td>
                                    </tr>
                                    <tr class="tr-red">
                                        <td>UBICADOS NO EXITOSOS</td><td></td><td>${estados['CU6']+estados['CU7']+estados['CU8']}</td><td></td><td>${(estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']) > 0?Math.round(((estados['CU6']+estados['CU7']+estados['CU8'])*100/(estados['CU1']+estados['CU2']+estados['CU3']+estados['CU4']+estados['CU5']+estados['CU6']+estados['CU7']+estados['CU8']))*100)/100: 0}%</td>
                                    </tr>
                                    <tr>
                                        <td>CU6 /Ubicado/No exitoso/No desea la oferta</td><td>${estados['CU6']}</td><td></td><td>${estados['CU6']*100/baseRecibida}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>CU7 /Ubicado/No exitoso/Interesado a futuro</td><td>${estados['CU7']}</td><td></td><td>${estados['CU7']*100/baseRecibida}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>CU8 /Ubicado/No exitoso/Cliente molesto</td><td>${estados['CU8']}</td><td></td><td>${estados['CU8']*100/baseRecibida}%</td><td></td>
                                    </tr>
                                    <tr class="tr-blue-strong">
                                        <td>TOTAL CLIENTES NO LOCALIZADOS</td><td></td><td>${estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']+estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7']}</td><td>${Math.round(((estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']+estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7'])*100/baseRecibida)*100)/100}%</td><td>100%</td>
                                    </tr>
                                    <tr class="tr-blue">
                                        <td>REGESTIONABLES</td><td></td><td>${estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']}</td><td></td><td>${(estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']+estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7']) > 0?Math.round(((estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308'])*100/(estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']+estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7']))*100)/100: 0}%</td>
                                    </tr>
                                    <tr>
                                        <td>NU3-301 /No ubicado/Regestionable/No contesta</td><td>${estados['NU3-301']}</td><td></td><td>${Math.round((estados['NU3-301']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU3-303 /No ubicado/Regestionable/Grabadora</td><td>${estados['NU3-303']}</td><td></td><td>${Math.round((estados['NU3-303']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU3-304 /No ubicado/Regestionable/Tono ocupado</td><td>${estados['NU3-304']}</td><td></td><td>${Math.round((estados['NU3-304']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU3-305 /No ubicado/Regestionable/No se encuentra</td><td>${estados['NU3-305']}</td><td></td><td>${Math.round((estados['NU3-305']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU3-307 /No ubicado/Regestionable/Tono de fax</td><td>${estados['NU3-307']}</td><td></td><td>${Math.round((estados['NU3-307']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU3-308 /No ubicado/Regestionable/Error de conexión CIC</td><td>${estados['NU3-308']}</td><td></td><td>${Math.round((estados['NU3-308']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr class="tr-blue">
                                        <td>INUBICABLES</td><td></td><td>${estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7']}</td><td></td><td>${(estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']+estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7']) > 0?Math.round(((estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7'])*100/(estados['NU3-301']+estados['NU3-303']+estados['NU3-304']+estados['NU3-305']+estados['NU3-307']+estados['NU3-308']+estados['NU1']+estados['NU2']+estados['NU4']+estados['NU5']+estados['NU6']+estados['NU7']))*100)/100: 0}%</td>
                                    </tr>
                                    <tr>
                                        <td>NU1 /No ubicado/Inubicable/Teléfono equivocado - No asignado</td><td>${estados['NU1']}</td><td></td><td>${Math.round((estados['NU1']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU2 /No ubicado/Inubicable/Teléfono averiado</td><td>${estados['NU2']}</td><td></td><td>${Math.round((estados['NU2']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU4 /No ubicado/Inubicable/Fallecido</td><td>${estados['NU4']}</td><td></td><td>${Math.round((estados['NU4']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU5 /No ubicado/Inubicable/Cliente de viaje</td><td>${estados['NU5']}</td><td></td><td>${Math.round((estados['NU5']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU6 /No ubicado/Inubicable/Teléfono de referencia</td><td>${estados['NU6']}</td><td></td><td>${Math.round((estados['NU6']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>NU7 /No ubicado/Inubicable/Vive fuera del país</td><td>${estados['NU7']}</td><td></td><td>${Math.round((estados['NU7']*100/baseRecibida)*100)/100}%</td><td></td>
                                    </tr>
                                    <tr>
                                        <td>TOTAL BASE GESTIONADA</td><td>${regsGestionados}</td><td>${regsGestionados}</td><td></td><td>${Math.round((regsGestionados*100/baseRecibida)*100)/100}%</td>
                                    </tr>
                                    <tr>
                                        <td>NU8 Otros no gestionables (Jurídicos, sin teléfono...)</td><td>${estados['NU8']}</td><td>${estados['NU8']}</td><td></td><td>${Math.round((estados['NU8']*100/baseRecibida)*100)/100}%</td>
                                    </tr>
                                    <tr>
                                        <td>S/G Sin gestión</td><td>${sinGestion}</td><td>${sinGestion}</td><td></td><td>${Math.round((sinGestion*100/baseRecibida)*100)/100}%</td>
                                    </tr>
                                    <tr class="tr-yellow-strong">
                                        <td>TOTAL BASE RECIBIDA</td><td>${baseRecibida}</td><td>${baseRecibida}</td><td></td><td>100%</td>
                                    </tr>
                                </table>
                            </g:if>
                        </div>


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