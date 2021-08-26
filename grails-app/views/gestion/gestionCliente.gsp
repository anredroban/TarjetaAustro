<meta name="layout" content="main" />
<title>Adicionales Austro - Gestionar Cliente</title>

<asset:stylesheet src="gestion/gestionCliente.css" />
<asset:stylesheet src="usogeneral/bootstrap-datepicker.min.css"></asset:stylesheet>

<script type="text/javascript">
    $(function () {
        $('#recall').datetimepicker();
    });
</script>

<script type="text/javascript">
    $(function () {
        $('#fechaNacimiento').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: "false",
            language: "es",
            autoclose: true,
            orientation: "top",
            defaultViewDate: {
                month: '04',
                day:'15',
                year: '1990'
            },
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $('#fechaNacimientoConyugue').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: "false",
            language: "es",
            autoclose: true,
            orientation: "top",
            defaultViewDate: {
                month: '04',
                day:'15',
                year: '1990'
            },
        });
    });
</script>
<%-- fechaNacimientoConyugue --%>
<style>
.input-group-addon {
	padding: .375rem .75rem;
	margin-bottom: 0;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	text-align: center;
	background-color: #e9ecef;
	border: 1px solid #ced4da;
}
</style>

<script>
    window.setInterval (BlinkIt, 500);
    var color = "red";
    function BlinkIt () {
        var blink = document.getElementById ("blink");
        color = (color == "#e4e4e4")? "red" : "#e4e4e4";
        blink.style.color = color;
        blink.style.fontSize='36px';}
</script>

<div class="container-fluid">
	<g:if test="${cliente.registroExitoso == 'SI'}">
		<div class="col-lg-12 col-md-12 col-xs-12">
			<label id="blink" style="font-size: 28px; font-weight: bold; color: red" >¡AVISO! </label><span id="priodidadTc" style="font-size: 28px; font-weight: bold; color: red">CLIENTE EXITOSO NO GESTIONAR</span>
		</div>
	</g:if>
	<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-xs-12 group-title">Datos del cliente</div>
		<div class="col-lg-12 col-md-12 col-xs-12 line"></div>

		<g:if test="${cliente.nombres != null && cliente.nombres != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 col-xs-12 form-group">
				<label>Nombres:</label>
				${cliente.nombres}
			</div>
		</g:if>

		<g:if test="${cliente.apellidos != null && cliente.apellidos != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Apellidos:</label>
				${cliente.apellidos}
			</div>
		</g:if>

		<g:if test="${cliente.identificacion != null && cliente.identificacion != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<g:hiddenField name="identificacion" id="identificacion" value="${cliente.identificacion}"></g:hiddenField>
				<label>Identificación:</label>
				${cliente.identificacion}
			</div>
		</g:if>

		<g:if test="${cliente.cuenta != null && cliente.cuenta != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Cuenta:</label>
				${utilitarios.Util.hideCardNumber(cliente.cuenta)}
			</div>
		</g:if>

		<g:if test="${cliente.direccion != null && cliente.direccion != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Dirección:</label>
				${cliente.direccion}
			</div>
		</g:if>

		<g:if test="${cliente.ciudadDomic != null && cliente.ciudadDomic != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Ciudad:</label>
				${cliente.ciudadDomic}
			</div>
		</g:if>

		<g:if test="${cliente.genero != null && cliente.genero != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Género:</label>
				${cliente.genero}
			</div>
		</g:if>

		<g:if test="${cliente.estadoCivil != null && cliente.estadoCivil != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Estado Civil:</label>
				${cliente.estadoCivil}
			</div>
		</g:if>

		<g:if test="${cliente.producto != null && cliente.producto != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Producto:</label>
				${cliente.producto}
			</div>
		</g:if>

		<g:if test="${cliente.familia != null && cliente.familia != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Familia:</label>
				${cliente.familia}
			</div>
		</g:if>

		<g:if test="${cliente.cupo != null && cliente.cupo != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Cupo:</label>
				${cliente.cupo}
			</div>
		</g:if>

		<g:if test="${cliente.cupoDisponible != null && cliente.cupoDisponible != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Cupo Disponible:</label>
				${cliente.cupoDisponible}
			</div>
		</g:if>

		<g:if test="${cliente.asist247 != null && cliente.asist247 != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Asistencia 24/7:</label>
				${cliente.asist247}
			</div>
		</g:if>

		<g:if test="${cliente.numeroCuenta != null && cliente.numeroCuenta != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Número Cuenta:</label>
				${cliente.numeroCuenta}
			</div>
		</g:if>

		<g:if test="${cliente.codigoAsignacion != null && cliente.codigoAsignacion != ''}">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Easy Code:</label>
				${cliente.codigoAsignacion}
			</div>
		</g:if>

	</div>

	<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-xs-12 group-title">Datos de Producto</div>
		<div class="col-lg-12 col-md-12 col-xs-12 line"></div>

		<g:if test="${cliente.marcaBin != null && cliente.marcaBin != ''}">
			<div class="col-lg-4 col-md-4 col-xs-12 form-group">
				<label>Producto:</label>
				${cliente.marcaBin}
			</div>
		</g:if>
		<g:if test="${cliente.cupo != null && cliente.cupo != ''}">
			<div class="col-lg-4 col-md-4 col-xs-12 form-group">
				<label>Cupo:</label>
				<strong> $ </strong> ${cliente.cupo}
			</div>
		</g:if>
		<div class="col-lg-12 col-md-12 col-xs-12 form-group">
			<label>Cupo mínimo a ofertar (25%):</label>
			<strong style="color: red; font-size: 20px"> $ ${valorLiquido}</strong>
		</div>
	</div>

	<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-xs-12 group-title">Datos de contacto</div>
		<div class="col-lg-12 col-md-12 col-xs-12 line"></div>

		<g:if test="${cliente.telefono1}">
			<div id="number1" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-mobile-phone"></span> Teléfono 1: </label>
				${cliente.telefono1}
				<g:select class="form-control" id="estadoTel1" name="estadoTel1" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" optionKey="value" />

			</div>
		</g:if>
		<g:if test="${cliente.telefono2 && cliente.telefono2.trim() != ''}">
			<div id="number2" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 2: </label>
				${cliente.telefono2}
				<g:select  class="form-control" id="estadoTel2" name="estadoTel2" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono3 && cliente.telefono3.trim() != ''}">
			<div id="number3" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 3: </label>
				${cliente.telefono3}
				<g:select  class="form-control" id="estadoTel3" name="estadoTel3" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono4 && cliente.telefono4.trim() != ''}">
			<div id="number4" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 4: </label>
				${cliente.telefono4}
				<g:select  class="form-control" id="estadoTel4" name="estadoTel4" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono5 && cliente.telefono5.trim() != ''}">
			<div id="number5" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 5: </label>
				${cliente.telefono5}
				<g:select  class="form-control" id="estadoTel5" name="estadoTel5" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono6 && cliente.telefono6.trim() != ''}">
			<div id="number6" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 6: </label>
				${cliente.telefono6}
				<g:select  class="form-control" id="estadoTel6" name="estadoTel6" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono7 && cliente.telefono7.trim() != ''}">
			<div id="number7" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 7: </label>
				${cliente.telefono7}
				<g:select  class="form-control" id="estadoTel7" name="estadoTel7" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono8 && cliente.telefono8.trim() != ''}">
			<div id="number8" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 8: </label>
				${cliente.telefono8}
				<g:select  class="form-control" id="estadoTel8" name="estadoTel8" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono9 && cliente.telefono9.trim() != ''}">
			<div id="number9" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 9: </label>
				${cliente.telefono9}
				<g:select  class="form-control" id="estadoTel9" name="estadoTel9" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.telefono10 && cliente.telefono10.trim() != ''}">
			<div id="number10" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label><span class="fa fa-phone"></span> Teléfono 10: </label>
				${cliente.telefono10}
				<g:select  class="form-control" id="estadoTel10" name="estadoTel10" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
			</div>
		</g:if>
		<g:if test="${cliente.email != null && cliente.email != ''}">
			<div class="col-lg-8 col-md-12 col-xs-12 form-group">
				<label>Email:</label>
				${cliente.email}
			</div>
		</g:if>

	</div>

	<g:form action="guardarCliente">
		<input type="hidden" value="${cliente.id}" id="idCliente" name="idCliente">
		<input type="hidden" value="${cliente.identificacion}" id="identificacionTitular" name="identificacionTitular">
		<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Estado Gestion</label>
				<span class="required-indicator">*</span>
				<g:select class="form-control" name="status" id="status"
						  from="${callcenter.Estado.list()}" optionKey="id"
						  optionValue="${{it.nombre	}}"
						  noSelection="${['': '-- Seleccione --']}" />
			</div>

			<div id="subStatusDiv" class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Subestado Gestion</label>
				<span class="required-indicator">*</span>
				<g:select class="form-control" name="substatus" id="substatus" from="" noSelection="${['': '-- Seleccione --']}" />
			</div>

			<div id="subSubStatusDiv" class="form-group col-lg-4 col-md-6 col-xs-12">
				<label>Sub subestado</label>
				<span class="required-indicator">*</span>
				<g:select id="subSubStatus" class="form-control" name="subSubStatus" from=""></g:select>
			</div>

			<div id="motivosSubSubEstadosDiv" class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Motivos SubEstados</label>
				<span class="required-indicator">*</span>
				<g:select id="motivosSubSubEstados" class="form-control" name="motivosSubSubEstados" from="" optionKey="id" noSelection="${['': '-- Seleccione --']}" ></g:select>
			</div>

			<div id="recallDiv">
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Agendamiento</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="agendamiento" id="agendamiento" from="${['AGENDAR PARA MI':'AGENDAR PARA MI', 'AGENDAR PARA CUALQUIERA':'AGENDAR PARA CUALQUIERA']}" optionKey="key"
							  optionValue="value"
							  noSelection="${['': '-- Seleccione --']}" />
				</div>
				<div class="col-lg-4 col-md-6 form-group">
					<label>Fecha Rellamada</label>
					<span class="required-indicator">*</span>
					<g:textField id="recall" name="recall" class="recall form-control"/>
				</div>
			</div>


			<div id="telefonoContactadoDiv" class="col-lg-4 col-md-6 form-group">
				<label style="color: red">Teléfono Contactado</label>
				<span class="required-indicator">*</span>
				<g:textField maxlength="10" minlength="9" id="telefonoContactado" name="telefonoContactado" class="form-control"/>
			</div>

		</div>


		<div id="productoPrincipalDiv">
			<div id="Script" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 group-title">Introducción y motivo de la llamada</div>
				<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<p>Buenos días/tardes/noches; converso con el Sr./Sra./Srta./ <strong>${cliente.apellidos} ${cliente.nombres}</strong></p>
					<p>Un gusto saludarle, de quienes conformamos Banco del Austro, le saluda <strong>${session.user.nombre}</strong>, espero sea un buen momento para conversar.</p>
					<p>
						<strong><i>Le comento, para garantizar nuestros niveles de calidad, esta conversación está siendo grabada.</i>(MANDATORIO)</strong>
					</p>
					<p>
						Estimado Sr./Sra. <strong>${cliente.apellidos} ${cliente.nombres}</strong> tenemos una excelente noticia para usted como cliente nuestro, nos comunicamos para presentarle una
					solución financiera que le permitirá respaldar a sus personas más cercanas, incluso en la situación como la que hoy enfrentamos,
					es por ello que tenemos listo la entrega de tarjetas adicionales categoría <strong>${cliente.producto}</strong> sin ningún costo de mantenimiento y tampoco anclados a ningún tipo de seguros, el único valor que asumirá es por el del chip de seguridad de $4.89 centavos.
					</p>
					<p>
						Estimado (a) <strong>${cliente.apellidos} ${cliente.nombres}</strong> coménteme, ¿A qué familiares o amigos le gustaría otorgar este beneficio? (esposa, hijos, padres, tíos, abuelos).
					</p>
				</div>
			</div>
			<div id="Script2" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 group-title">Confirmación</div>
				<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<p>
						Sr./Sra. <strong>${cliente.apellidos} ${cliente.nombres}</strong> ayúdeme con la confirmación de su número de cédula por favor; <strong>${cliente.identificacion}</strong>, le informo que usted personaliza el cupo de las TC adicionales desde $200 o el 25% de la totalidad de su cupo en la TCP.
					</p>
					<p>
						Las tarjetas de créditos adicionales <strong>(MENCIONAR CUENTAS TARJETAS SE GENERARON)</strong> llegaran en un lapso de 8 a 10 días laborables y recuerde que el costo es de $4,89 centavos por cada tarjeta.
					</p>
				</div>
			</div>
			<div id="Script3" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 group-title">Información de Producto</div>
				<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<p>
						<strong>¿Otros Beneficios?</strong>
					<blockquote style="font-size: 15px">
						<li><i>No tiene costos de mantenimiento mensual ni anual, solo el cargo de emisión de la TDCA $4,89.</i></li>
						<li><i>No viene atado a ningún seguro.</i></li>
						<li><i>Para emergencias hospitalarias.</i></li>
						<li><i>Respaldo económico, ya que puede activar cuando sea necesario en case de emergencia y ayuda para la acumulación de puntos
						que se otorgarán a la tarjeta de crédito principales.</i></li>
					</blockquote>
				</p>
					<p>El cupo de la tarjeta adicional será equivalente al cupo total del titular
					En caso que el titular indique el cupo a otorgar a la tarjetahabiente adicional   indicar que el cupo va desde los 200$ o el 25% del cupo del titular.
					</p>
				</div>
			</div>
			<div id="Script4" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 group-title">Información de Producto</div>
				<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<p>
						Estimado (a) <strong>${cliente.apellidos} ${cliente.nombres}</strong> Bienvenido a disfrutar de los beneficios exclusivos que le ofrece Banco del Austro, esperamos que tenga un(a) excelente(a) día/tarde/noche.
					</p>
				</div>
			</div>

			<div id="datosClientePanel" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 group-title">Datos del domicilio</div>
				<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Provincia Domicilio</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="provinciaDomic" id="provinciaDomic" from="${callcenter.Provincia.list()}" optionKey="id"
							  optionValue="${{it.nombre	}}"
							  noSelection="${['': '-- Seleccione --']}" />
				</div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Ciudad Domicilio</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="ciudadDomic" id="ciudadDomic" from="" optionKey="id"
							  optionValue="${{it.nombre	}}"
							  noSelection="${['': '-- Seleccione --']}" />
				</div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Parroquia Domicilio</label>
					<span class="required-indicator"> *</span>
					<g:select class="form-control" id="sectorDomic" name="sectorDomic" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
				</div>

				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Calle Principal</label>
					<span class="required-indicator">*</span>
					<g:textField class="form-control" id="callePrincipalDomic" name="callePrincipalDomic"/>
				</div>

				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Nomenclatura</label>
					<span class="required-indicator">*</span>
					<g:textField class="form-control" id="numeracionDomic" name="numeracionDomic"/>
				</div>

				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Calle Transversal</label>
					<span class="required-indicator">*</span>
					<g:textField class="form-control" id="calleTransversalDomic" name="calleTransversalDomic"/>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<label>Referencia Domicilio</label>
					<span class="required-indicator">*</span>
					<g:textArea class="form-control" name="referenciaDomic" id="referenciaDomic" />
				</div>
			</div>
		<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-xs-12 group-title">Datos de Contacto</div>
			<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
			<div class="col-lg-4 col-md-4 form-group">
				<label>Tlf Contacto Domicilio</label>
				<span class="required-indicator">*</span>
				<g:textField class="form-control" id="telefonoDomContacto" name="telefonoDomContacto" minlength="9" maxlength="9"/>
			</div>
			<div class="col-lg-4 col-md-4 form-group">
				<label>Tlf Contacto Oficina</label>
				<span class="required-indicator">*</span>
				<g:textField class="form-control" id="telefonoTrabContacto" name="telefonoTrabContacto" minlength="9" maxlength="9"/>
			</div>
			<div class="col-lg-4 col-md-4 form-group">
				<label>Celular Titular</label>
				<span class="required-indicator">*</span>
				<g:textField class="form-control" id="celularContacto" name="celularContacto" minlength="10" maxlength="10"/>
			</div>
		</div>


			<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 group-title">Datos de Entrega</div>
				<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Lugar de Entrega</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" id="entrega" name="entrega" from="${['DOMICILIO', 'TRABAJO']}" noSelection="${['':'-- Seleccione --']}"/>
				</div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Provincia Entrega</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="provinciaTrab" id="provinciaTrab" from="${callcenter.Provincia.list()}" optionKey="id"
							  optionValue="${{it.nombre	}}"
							  noSelection="${['': '-- Seleccione --']}" />
				</div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Ciudad Entrega</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="ciudadTrab" id="ciudadTrab" from="" optionKey="id"
							  optionValue="${{it.nombre	}}"
							  noSelection="${['': '-- Seleccione --']}" />
				</div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Parroquia Entrega</label>
					<span class="required-indicator"> *</span>
					<g:select class="form-control" id="sectorTrab" name="sectorTrab" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
				</div>

				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Calle Principal</label>
					<span class="required-indicator">*</span>
					<g:textField class="form-control" id="callePrincipalTrab" name="callePrincipalTrab"/>
				</div>

				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Nomenclatura</label>
					<span class="required-indicator">*</span>
					<g:textField class="form-control" id="numeracionTrab" name="numeracionTrab"/>
				</div>

				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>Calle Transversal</label>
					<span class="required-indicator">*</span>
					<g:textField class="form-control" id="calleTransversalTrab" name="calleTransversalTrab"/>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<label>Referencia Domicilio</label>
					<span class="required-indicator">*</span>
					<g:textArea class="form-control" name="referenciaTrab" id="referenciaTrab" />
				</div>
				<div class="col-lg-4 col-md-6 col-xs-12 form-group">
					<label>
						Nombre Empresa
						<span class="required-indicator">*</span>
					</label>
					<g:textField class="form-control" id="nombreEmpresaTrabajo" name="nombreEmpresaTrabajo"/>
				</div>
			</div>

			<div id="adicionalesPanel" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12 form-group">
					<button id="btnAdicional" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Agregar Adicional <span class="fa fa-fw fa-plus"></span> </button>
				</div>
				<div id="contenedorAdicionales">
					<input type="hidden" value="" id="hidenAdicionales" name="hidenAdicionales">
					<table id="tablaAdicionales" class="table-bordered table-responsive col-lg-12 col-md-12 col-xs-12">
						<thead>
						<tr>
							<th>Cédula</th>
							<th>Tipo Id</th>
							<th>Nombres</th>
							<th>Nombre Tarjeta</th>
							<th>Cupo</th>
							<th>Sexo</th>
							<th>E Civil</th>
							<th>Parentesco</th>
							<th>Fecha Nacimiento</th>
							<th>Nacionalidad</th>
							<%--<th>Prov. / Ciud.</th>--%>
							<th>Acciones</th>
						</tr>
						</thead>
						<tbody id="bodyTablaAdicionales"></tbody>
					</table>
				</div>
			</div>
		</div>

		<div id="productoSecundarioDiv" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="col-lg-4 col-md-6 col-xs-12">
				<div id="asistenciasDiv"class="col-lg-12 col-md-12 col-xs-12 form-group">
					<label>Asistencias</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="asistencia"
							  id="asistencia"
							  multiple="multiple"
							  optionKey="key"
							  optionValue="value"
							  from="${['AE':'Asistencia Exequial', 'A247':'Asistencia 24-7', 'AT':'Asistencia Extra Total']}"/>
				</div>
			</div>
			<div class="col-lg-8 col-md-6 col-xs-12">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<label>Débito a:</label>
					<span class="required-indicator">*</span>
					<g:select class="form-control" name="debitoAsistencia" from="${['TARJETA DE CREDITO', 'CUENTA BANCARIA']}" noSelection="${['':'-- Seleccione --']}"/>
				</div>
				%{--<div id="datosExequial" class="col-lg-12 col-md-12 col-xs-12">--}%
				%{--<div class="col-lg-12 col-md-12 col-xs-12 group-title">Asistencia Exequial</div>--}%
				%{--<div class="col-lg-12 col-md-12 col-xs-12 line"></div>--}%
				%{--<div class="col-lg-4 col-md-6 col-xs-12 form-group">--}%
				%{--<label>Nombres</label>--}%
				%{--<span class="required-indicator">*</span>--}%
				%{--<g:textField class="form-control" name="nombresExequial"></g:textField>--}%
				%{--</div>--}%
				%{--<div class="col-lg-4 col-md-6 col-xs-12 form-group">--}%
				%{--<label>Edad</label>--}%
				%{--<span class="required-indicator">*</span>--}%
				%{--<g:textField class="form-control" name="edadExequial"></g:textField>--}%
				%{--</div>--}%
				%{--<div class="col-lg-4 col-md-6 col-xs-12 form-group">--}%
				%{--<strong>Parentesco</strong>--}%
				%{--<span class="required-indicator">*</span>--}%
				%{--<g:select class="form-control" name="parentescoExequial" from="${['CONYUGE','PADRES','HERMANOS','HIJOS','HIJASTROS']}" noSelection="${["": "-- Seleccione --"]}"></g:select>--}%
				%{--</div>--}%
				%{--</div>--}%
				<div id="datosExtraTotal" class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-xs-12 group-title">Asistencia Extratotal</div>
					<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
					<div class="form-group col-lg-4 col-md-6 col-xs-12">
						<label>Tipo Cobro</label>
						<span class="required-indicator">*</span>
						<g:select class="form-control" name="tipoCobro" from="${['ANUAL', 'MENSUAL']}" noSelection="${['': '-- Seleccione --']}"></g:select>
					</div>
				</div>
				<div id="datos247" class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-xs-12 group-title">Asistencia 24/7</div>
					<div class="col-lg-12 col-md-12 col-xs-12 line"></div>
					<div class="form-group col-lg-4 col-md-6 col-xs-12">
						<label>Cobertura</label>
						<span class="required-indicator">*</span>
						<g:select class="form-control" name="cobertura247" from="${['1 TITULAR', '1 TITULAR + 2 MIEMBROS DE LA FAMILIA', '1 TITULAR + 4 MIEMBROS DE LA FAMILIA']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="form-group col-lg-4 col-md-6 col-xs-12">
						<label>Tipo Cobro</label>
						<span class="required-indicator">*</span>
						<g:select class="form-control" name="tipoCobro247" from="${['ANUAL', 'MENSUAL']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="col-lg-12 col-md-12 form-group">
				<label>Observaciones</label>
				<g:textArea class="form-control" id="observacionesGestion" name="observacionesGestion" value="${cliente.observaciones}"/>
			</div>
		</div>

		<div id="btnGuardarGestionDiv" class="col-lg-12 col-md-12 col-xs-12">
			<input type="submit" id="btnGuardarGestion" name="btnGuardarGestion" type="button" class="btn btn-primary" value="Guardar Gestión" />
		</div>

	</g:form>

</div>




<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Agregar Adicional</h4>
			</div>
			<div class="modal-body">
				<form id="formAdicional" action="/Adicionales/adicional/save" method="post">
					<fieldset class="form">
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="cedula">
								Cedula
								<span class="required-indicator">*</span>
							</label>
							<input class="form-control" type="text" name="cedula" required="" value="" id="cedula" maxlength="10">
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="tipoIdentificacion">
								Tipo Identificación
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" id="tipoIdentificacion" name="tipoIdentificacion" from="${['CEDULA', 'CODIGO EXTRANJERIA', 'PASAPORTE', 'RUC']}" noSelection="${['': '-- Seleccione --']}" />
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="primerNombre">
								Primer Nombre
								<span class="required-indicator">*</span>
							</label>
							<input class="form-control" type="text" name="primerNombre" required="" value="" id="primerNombre">
						</div>

						<div class="fieldcontain form-group col-lg-6">
							<label for="segundoNombre">
								Segundo Nombre
							</label>
							<input class="form-control" type="text" name="segundoNombre" value="" id="segundoNombre">
						</div>

						<div class="fieldcontain  required form-group col-lg-6">
							<label for="primerApellido">
								Primer Apellido
								<span class="required-indicator">*</span>
							</label>
							<input class="form-control" type="text" name="primerApellido" required="" value="" id="primerApellido">
						</div>

						<div class="fieldcontain form-group col-lg-6">
							<label for="segundoApellido">
								Segundo Apellido
							</label>
							<span class="required-indicator">*</span>
							<input class="form-control" type="text" name="segundoApellido" value="" id="segundoApellido">
						</div>

						<div class="fieldcontain form-group col-lg-6">
							<label for="nombreTarjeta">
								Nombre Tarjeta
							</label>
							<span class="required-indicator">*</span>
							<input class="form-control" type="text" name="nombreTarjeta" value="" id="nombreTarjeta" maxlength="20">
						</div>

						<div class="fieldcontain form-group col-lg-6" >
							<label for="cupoOtorgado">
								Cupo Otorgado
							</label>
							<span class="required-indicator">*</span>
							<div class='input-group text'>
							<input class="form-control" type="text" name="cupoOtorgado" value="" id="cupoOtorgado" />
							<span class="input-group-addon">
								<span style="font-size: 16px; font-weight: bold; text-shadow: -1px 0   #abb2b9, 1px 0   #abb2b9, 0 1px   #abb2b9, 0 -1px   #abb2b9;">$</span>
							</span>
							</div>

						</div>

						<div class="fieldcontain  required form-group col-lg-6">
							<label for="sexo">
								Sexo
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" name="sexo" from="${['MASCULINO', 'FEMENINO']}" noSelection="${['': '-- Seleccione --']}" />
						</div>

						<div class="fieldcontain  required form-group col-lg-6">
							<label for="estadoCivil">
								Estado Civil
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" name="estadoCivil" from="${['SOLTERO', 'CASADO', 'VIUDO', 'DIVORCIADO', 'UNION LIBRE']}" noSelection="${['': '-- Seleccione --']}" />
						</div>
						<div id="datosConyugue">
							<div class="fieldcontain  required form-group col-lg-6">
								<label for="cedulaConyugue">
									Cédula Conyugue
									<span class="required-indicator">*</span>
								</label>
								<input class="form-control" type="text" name="cedulaConyugue" required="" value="" id="cedulaConyugue" maxlength="10">
							</div>
							<div class="fieldcontain  required form-group col-lg-6">
								<label for="fechaNacimientoConyugue">
									Fe. Nac. Conyugue
									<span class="required-indicator">*</span>
								</label>
								<input type="text" name="fechaNacimientoConyugue" required="" value="" id="fechaNacimientoConyugue" class="datepicker form-control pointer">
							</div>
						</div>


						<div class="fieldcontain  required form-group col-lg-6">
							<label for="parentesco">
								Parentesco
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" name="parentesco" from="${['NINGUNO':'NINGUNO', 'PADRE':'PADRE', 'MADRE':'MADRE', 'HERMANO(A)':'HERMANO(A)', 'PRIMO(A)':'PRIMO(A)', 'TIO(A)': 'TIO(A)', 'SOBRINO(A)': 'SOBRINO(A)',
																					  'ESPOSO(A)': 'ESPOSO(A)', 'CUÑADO(A)': 'CUÑADO(A)', 'YERNO/NUERA': 'YERNO/NUERA', 'SUEGRO(A)': 'SUEGRO(A)', 'HIJO(A)': 'HIJO(A)', 'AMIGO(A)': 'AMIGO(A)', 'ABUELO(A)': 'ABUELO(A)', 'NOVIO(A)': 'NOVIO(A)',
																					  'NIETO(A)': 'NIETO(A)', 'COMPAÑERO DE TRABAJO': 'COMPAÑERO DE TRABAJO','REPRESENTANTE LEGAL': 'REPRESENTANTE LEGAL', 'RELACION COMERCIAL': 'RELACION COMERCIAL', 'RELACION LABORAL': 'RELACION LABORAL',
																					  'PRESIDENTE': 'PRESIDENTE', 'VICE-PRESIDENTE': 'VICE-PRESIDENTE', 'FUNCIONARIO': 'FUNCIONARIO', 'EJECUTIVO': 'EJECUTIVO']}" noSelection="${['': '-- Seleccione --']}" optionValue="key" optionKey="value" />
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="relacionLaboral">
								Relación Laboral
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" id="relacionLaboral" name="relacionLaboral" from="${['DEPENDIENTE', 'INDEPENDIENTE', 'ESTUDIANTE', 'JUBILADO']}" noSelection="${['': '-- Seleccione --']}" />
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="fechaNacimiento">
								Fecha Nacimiento
								<span class="required-indicator">*</span>
							</label>
							<input type="text" name="fechaNacimiento" required="" value="" id="fechaNacimiento" class="datepicker form-control pointer">
						</div>

						<div class="fieldcontain  required form-group col-lg-6">
							<label for="nacionalidadSelect">
								Nacionalidad
								<span class="required-indicator">*</span>
							</label>
							<%--<g:select class="form-control" id="nacionalidadSelect" name="nacionalidadSelect" optionKey="key" optionValue="value" from="${['ECUATORIANA':'ECUATORIANA','VENEZOLANA':'VENEZOLANA','CUBANA':'CUBANA', 'COLOMBIANA':'COLOMBIANA', '': 'OTRA']}" />--%>
							<g:select class="form-control" id="nacionalidadSelect" name="nacionalidadSelect" from="${callcenter.Nacionalidad.list()}" optionKey="nombre"
									  optionValue="${{it.nombre	}}"
									  noSelection="${['': '-- Seleccione --']}" />
						</div>
                        <div id="nacionalidadDiv">
						<div class="fieldcontain  required form-group col-lg-6">
								<label for="provinciaAdic" >Provincia Nacimiento</label>
								<span class="required-indicator">*</span>
								<g:select class="form-control" name="provinciaAdic" id="provinciaAdic" from="${callcenter.Provincia.list()}" optionKey="id"
										  optionValue="${{it.nombre	}}"
										  noSelection="${['': '-- Seleccione --']}" />

						</div>
							<div class="fieldcontain  required form-group col-lg-6">
								<label for="ciudadAdic" >Ciudad Entrega</label>
								<span class="required-indicator">*</span>
								<g:select class="form-control" name="ciudadAdic" id="ciudadAdic" from="" optionKey="id"
										  optionValue="${{it.nombre	}}"
										  noSelection="${['': '-- Seleccione --']}" />

							</div>
						</div>

						<div class="fieldcontain form-group col-lg-6">
							<label for="telefonoCelular">
								Teléfono Celular
							</label>
							<span class="required-indicator">*</span>
							<input class="form-control" type="text" name="telefonoCelular" value="" id="telefonoCelular" maxlength="10">
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="operadoraAdicional">
								Operadora Celular
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" id="operadoraAdicional" name="operadoraAdicional" from="${['MOVISTAR', 'CLARO', 'CNT', 'TUENTI']}" noSelection="${['': '-- Seleccione --']}" />
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="correo">
								Correo Electrónico
								<span class="required-indicator">*</span>
							</label>
							<input class="form-control" type="text" name="correo" value="" id="correo">
						</div>
						<div class="fieldcontain  required form-group col-lg-6">
							<label for="viveFamiliares">
								Vive Familiares
								<span class="required-indicator">*</span>
							</label>
							<g:select class="form-control" id="viveFamiliares" name="viveFamiliares" from="${['SI', 'NO']}" noSelection="${['': '-- Seleccione --']}" />
						</div>
						<div id="valDireccionAdicional">
						<div class="fieldcontain  required col-lg-12 form-group">
							<label for="direccionAdicional">
								Dirección Domicilio
								<span class="required-indicator">*</span>
							</label>
							<input class="form-control" type="text" name="direccionAdicional" required="" value="" id="direccionAdicional">
						</div>
						</div>
						<div class="fieldcontain  required col-lg-12 form-group">
							<label for="observaciones">
								Observaciones
							</label>
							<input class="form-control" type="text" name="observaciones" required="" value="" id="observaciones">
						</div>
					</fieldset>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary" id="btnGuardarAdicional">Agregar</button>
			</div>
		</div>
	</div>

</div><asset:javascript src="usogeneral/objetos.js" />
<asset:javascript src="gestion/gestionCliente1.js" />
<asset:stylesheet src="usogeneral/datetimepicker.css" />
<asset:javascript src="usogeneral/datetimepicker.js" />
<asset:javascript src="usogeneral/customdatetimepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.min.js" />
<asset:javascript src="usogeneral/customdatepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.es.min.js" />